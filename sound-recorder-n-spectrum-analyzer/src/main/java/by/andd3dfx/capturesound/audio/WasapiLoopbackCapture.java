package by.andd3dfx.capturesound.audio;

import com.sun.jna.Pointer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Captures the default Windows render endpoint mix via WASAPI loopback.
 */
public class WasapiLoopbackCapture implements LoopbackCapture {

    private static final int READ_POLL_TIMEOUT_MS = 100;

    private WasapiCom.MMDeviceEnumerator enumerator;
    private WasapiCom.MMDevice device;
    private WasapiCom.AudioClient audioClient;
    private WasapiCom.AudioCaptureClient captureClient;
    private Pointer mixFormatPointer;

    private PcmConverter pcmConverter;
    private int sampleRate;
    private String deviceName = "";

    private final Deque<Byte> pending = new ArrayDeque<>();
    private volatile boolean running;
    private boolean comInitialized;

    @Override
    public void open() throws CaptureException {
        WasapiCom.checkPlatform();
        WasapiCom.coInitialize();
        comInitialized = true;

        try {
            enumerator = WasapiCom.MMDeviceEnumerator.create();
            device = enumerator.getDefaultRenderEndpoint();
            deviceName = device.getId();

            audioClient = device.activateAudioClient();
            WaveFormatEx mixFormat = audioClient.getMixFormat();
            mixFormatPointer = mixFormat.getPointer();
            sampleRate = mixFormat.nSamplesPerSec;
            pcmConverter = new PcmConverter(mixFormat);

            audioClient.initializeLoopback(mixFormat);
            audioClient.getBufferSize();
            captureClient = audioClient.getCaptureClient();
        } catch (RuntimeException e) {
            close();
            throw new CaptureException("Failed to open WASAPI loopback capture", e);
        }
    }

    @Override
    public void start() {
        audioClient.start();
        running = true;
        System.out.println("WASAPI loopback: " + deviceName + " @ " + sampleRate + " Hz");
    }

    @Override
    public int read(byte[] buffer, int offset, int length) throws CaptureException {
        if (!running) {
            return -1;
        }

        try {
            int waitedMs = 0;
            while (pending.isEmpty() && running) {
                if (pollPackets()) {
                    waitedMs = 0;
                    continue;
                }
                if (waitedMs >= READ_POLL_TIMEOUT_MS) {
                    return 0;
                }
                Thread.sleep(5);
                waitedMs += 5;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return -1;
        } catch (RuntimeException e) {
            throw new CaptureException("WASAPI read failed", e);
        }

        if (pending.isEmpty()) {
            return -1;
        }

        int toRead = Math.min(length, pending.size());
        for (int i = 0; i < toRead; i++) {
            buffer[offset + i] = pending.pollFirst();
        }
        return toRead;
    }

    private boolean pollPackets() {
        int packetFrames = captureClient.getNextPacketSize();
        if (packetFrames == 0) {
            return false;
        }

        while (captureClient.getNextPacketSize() > 0) {
            WasapiCom.CaptureBuffer captureBuffer = captureClient.getBuffer();
            try {
                byte[] pcm = pcmConverter.toMonoPcm16(captureBuffer);
                for (byte b : pcm) {
                    pending.addLast(b);
                }
            } finally {
                captureClient.releaseBuffer(captureBuffer.frames());
            }
        }
        return true;
    }

    @Override
    public void stop() {
        running = false;
        if (audioClient != null) {
            audioClient.stop();
        }
        pending.clear();
    }

    @Override
    public void close() {
        stop();
        release(captureClient);
        captureClient = null;
        release(audioClient);
        audioClient = null;
        release(device);
        device = null;
        release(enumerator);
        enumerator = null;
        WasapiCom.coTaskMemFree(mixFormatPointer);
        mixFormatPointer = null;
        if (comInitialized) {
            WasapiCom.coUninitialize();
            comInitialized = false;
        }
    }

    private static void release(com.sun.jna.platform.win32.COM.Unknown comObject) {
        if (comObject != null) {
            comObject.Release();
        }
    }

    @Override
    public int getSampleRate() {
        return sampleRate;
    }

    @Override
    public String getDeviceName() {
        return deviceName;
    }
}
