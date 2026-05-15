package by.andd3dfx.capturesound.audio;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Guid.CLSID;
import com.sun.jna.platform.win32.Guid.GUID;
import com.sun.jna.platform.win32.Ole32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.WTypes;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * Minimal JNA bindings for WASAPI loopback capture on Windows.
 */
final class WasapiCom {

    static final CLSID CLSID_MM_DEVICE_ENUMERATOR =
            new CLSID("BCDE0395-E52F-467C-8E3D-C4579291692E");
    static final GUID IID_IMM_DEVICE_ENUMERATOR =
            new GUID("A95664D2-9614-4F35-A746-DE8DB63617E6");
    static final GUID IID_I_AUDIO_CLIENT =
            new GUID("1CB9AD4C-DBFA-4C32-B178-C2F568A703B2");
    static final GUID IID_I_AUDIO_CAPTURE_CLIENT =
            new GUID("C8ADBD64-E71E-48A0-A4DE-185C395CD317");

    static final int E_RENDER = 0;
    static final int E_CONSOLE = 0;

    static final int AUDCLNT_SHAREMODE_SHARED = 0;
    static final int AUDCLNT_STREAMFLAGS_LOOPBACK = 0x0002_0000;

    /** 100-nanosecond units; 1 second buffer. */
    static final long BUFFER_DURATION_HNS = 10_000_000L;

    private WasapiCom() {
    }

    static void checkPlatform() throws CaptureException {
        String os = System.getProperty("os.name", "").toLowerCase();
        if (!os.contains("win")) {
            throw new CaptureException("WASAPI loopback is only supported on Windows");
        }
    }

    static void coInitialize() {
        WinNT.HRESULT hr = Ole32.INSTANCE.CoInitializeEx(Pointer.NULL, Ole32.COINIT_MULTITHREADED);
        if (COMUtils.FAILED(hr) && hr.intValue() != 1 /* S_FALSE already initialized */) {
            COMUtils.checkRC(hr);
        }
    }

    static void coUninitialize() {
        Ole32.INSTANCE.CoUninitialize();
    }

    static void coTaskMemFree(Pointer p) {
        if (p != null) {
            Ole32.INSTANCE.CoTaskMemFree(p);
        }
    }

    static final class MMDeviceEnumerator extends Unknown {

        MMDeviceEnumerator(Pointer p) {
            super(p);
        }

        static MMDeviceEnumerator create() {
            PointerByReference ref = new PointerByReference();
            WinNT.HRESULT hr = Ole32.INSTANCE.CoCreateInstance(
                    CLSID_MM_DEVICE_ENUMERATOR,
                    null,
                    WTypes.CLSCTX_ALL,
                    IID_IMM_DEVICE_ENUMERATOR,
                    ref);
            COMUtils.checkRC(hr);
            return new MMDeviceEnumerator(ref.getValue());
        }

        MMDevice getDefaultRenderEndpoint() {
            PointerByReference deviceRef = new PointerByReference();
            WinNT.HRESULT hr = (WinNT.HRESULT) _invokeNativeObject(
                    4,
                    new Object[]{getPointer(), E_RENDER, E_CONSOLE, deviceRef},
                    WinNT.HRESULT.class);
            COMUtils.checkRC(hr);
            return new MMDevice(deviceRef.getValue());
        }
    }

    static final class MMDevice extends Unknown {

        MMDevice(Pointer p) {
            super(p);
        }

        String getId() {
            PointerByReference idRef = new PointerByReference();
            WinNT.HRESULT hr = (WinNT.HRESULT) _invokeNativeObject(
                    5,
                    new Object[]{getPointer(), idRef},
                    WinNT.HRESULT.class);
            COMUtils.checkRC(hr);
            try {
                return idRef.getValue().getWideString(0);
            } finally {
                coTaskMemFree(idRef.getValue());
            }
        }

        AudioClient activateAudioClient() {
            PointerByReference clientRef = new PointerByReference();
            WinNT.HRESULT hr = (WinNT.HRESULT) _invokeNativeObject(
                    3,
                    new Object[]{getPointer(), IID_I_AUDIO_CLIENT, WTypes.CLSCTX_ALL, null, clientRef},
                    WinNT.HRESULT.class);
            COMUtils.checkRC(hr);
            return new AudioClient(clientRef.getValue());
        }
    }

    static final class AudioClient extends Unknown {

        AudioClient(Pointer p) {
            super(p);
        }

        WaveFormatEx getMixFormat() {
            PointerByReference formatRef = new PointerByReference();
            WinNT.HRESULT hr = (WinNT.HRESULT) _invokeNativeObject(
                    8,
                    new Object[]{getPointer(), formatRef},
                    WinNT.HRESULT.class);
            COMUtils.checkRC(hr);
            return new WaveFormatEx(formatRef.getValue());
        }

        void initializeLoopback(WaveFormatEx format) {
            WinNT.HRESULT hr = (WinNT.HRESULT) _invokeNativeObject(
                    3,
                    new Object[]{
                            getPointer(),
                            AUDCLNT_SHAREMODE_SHARED,
                            AUDCLNT_STREAMFLAGS_LOOPBACK,
                            BUFFER_DURATION_HNS,
                            0L,
                            format.getPointer(),
                            null
                    },
                    WinNT.HRESULT.class);
            COMUtils.checkRC(hr);
        }

        int getBufferSize() {
            IntByReference bufferFrames = new IntByReference();
            WinNT.HRESULT hr = (WinNT.HRESULT) _invokeNativeObject(
                    4,
                    new Object[]{getPointer(), bufferFrames},
                    WinNT.HRESULT.class);
            COMUtils.checkRC(hr);
            return bufferFrames.getValue();
        }

        void start() {
            WinNT.HRESULT hr = (WinNT.HRESULT) _invokeNativeObject(
                    10,
                    new Object[]{getPointer()},
                    WinNT.HRESULT.class);
            COMUtils.checkRC(hr);
        }

        void stop() {
            WinNT.HRESULT hr = (WinNT.HRESULT) _invokeNativeObject(
                    11,
                    new Object[]{getPointer()},
                    WinNT.HRESULT.class);
            COMUtils.checkRC(hr);
        }

        AudioCaptureClient getCaptureClient() {
            PointerByReference captureRef = new PointerByReference();
            WinNT.HRESULT hr = (WinNT.HRESULT) _invokeNativeObject(
                    14,
                    new Object[]{getPointer(), IID_I_AUDIO_CAPTURE_CLIENT, captureRef},
                    WinNT.HRESULT.class);
            COMUtils.checkRC(hr);
            return new AudioCaptureClient(captureRef.getValue());
        }
    }

    static final class AudioCaptureClient extends Unknown {

        AudioCaptureClient(Pointer p) {
            super(p);
        }

        int getNextPacketSize() {
            IntByReference packetSize = new IntByReference();
            WinNT.HRESULT hr = (WinNT.HRESULT) _invokeNativeObject(
                    5,
                    new Object[]{getPointer(), packetSize},
                    WinNT.HRESULT.class);
            COMUtils.checkRC(hr);
            return packetSize.getValue();
        }

        CaptureBuffer getBuffer() {
            PointerByReference dataRef = new PointerByReference();
            IntByReference framesRef = new IntByReference();
            WinDef.DWORDByReference flagsRef = new WinDef.DWORDByReference();
            WinNT.HRESULT hr = (WinNT.HRESULT) _invokeNativeObject(
                    3,
                    new Object[]{getPointer(), dataRef, framesRef, flagsRef, null, null},
                    WinNT.HRESULT.class);
            COMUtils.checkRC(hr);
            return new CaptureBuffer(
                    dataRef.getValue(),
                    framesRef.getValue(),
                    flagsRef.getValue().intValue());
        }

        void releaseBuffer(int frames) {
            WinNT.HRESULT hr = (WinNT.HRESULT) _invokeNativeObject(
                    4,
                    new Object[]{getPointer(), frames},
                    WinNT.HRESULT.class);
            COMUtils.checkRC(hr);
        }
    }

    record CaptureBuffer(Pointer data, int frames, int flags) {
        boolean isSilent() {
            return (flags & 0x2) != 0; // AUDCLNT_BUFFERFLAGS_SILENT
        }
    }
}
