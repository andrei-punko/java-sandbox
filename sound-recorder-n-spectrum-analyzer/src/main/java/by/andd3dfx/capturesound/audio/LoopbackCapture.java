package by.andd3dfx.capturesound.audio;

/**
 * Minimal capture contract similar to {@link javax.sound.sampled.TargetDataLine}
 * for system audio loopback.
 */
public interface LoopbackCapture extends AutoCloseable {

    void open() throws CaptureException;

    void start();

    /**
     * Reads PCM bytes (16-bit little-endian mono) into the buffer.
     *
     * @return number of bytes read, or -1 if capture is stopped
     */
    int read(byte[] buffer, int offset, int length) throws CaptureException;

    void stop();

    @Override
    void close();

    int getSampleRate();

    String getDeviceName();
}
