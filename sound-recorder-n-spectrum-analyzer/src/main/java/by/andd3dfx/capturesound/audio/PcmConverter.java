package by.andd3dfx.capturesound.audio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Converts WASAPI mix-format PCM to 16-bit little-endian mono bytes for FFT.
 */
public final class PcmConverter {

    private final int formatTag;
    private final int channels;
    private final int bitsPerSample;
    private final int blockAlign;

    public PcmConverter(WaveFormatEx format) {
        this.formatTag = format.effectiveFormatTag();
        this.channels = Math.max(1, format.nChannels);
        this.bitsPerSample = format.effectiveBitsPerSample();
        this.blockAlign = format.nBlockAlign;
    }

    public byte[] toMonoPcm16(WasapiCom.CaptureBuffer buffer) {
        if (buffer.frames() <= 0) {
            return new byte[0];
        }
        if (buffer.isSilent()) {
            return new byte[buffer.frames() * 2];
        }

        return switch (formatTag) {
            case WaveFormatEx.WAVE_FORMAT_PCM -> pcmToMono16(buffer);
            case WaveFormatEx.WAVE_FORMAT_IEEE_FLOAT -> floatToMono16(buffer);
            default -> throw new IllegalArgumentException(
                    "Unsupported audio format tag: 0x" + Integer.toHexString(formatTag));
        };
    }

    private byte[] pcmToMono16(WasapiCom.CaptureBuffer buffer) {
        int frameBytes = blockAlign > 0 ? blockAlign : channels * (bitsPerSample / 8);
        int totalBytes = buffer.frames() * frameBytes;
        byte[] raw = buffer.data().getByteArray(0, totalBytes);
        short[] mono = new short[buffer.frames()];

        if (bitsPerSample == 16) {
            ByteBuffer bb = ByteBuffer.wrap(raw).order(ByteOrder.LITTLE_ENDIAN);
            for (int i = 0; i < buffer.frames(); i++) {
                if (channels == 1) {
                    mono[i] = bb.getShort();
                } else {
                    int sum = 0;
                    for (int ch = 0; ch < channels; ch++) {
                        sum += bb.getShort();
                    }
                    mono[i] = (short) (sum / channels);
                }
            }
        } else if (bitsPerSample == 8) {
            for (int i = 0; i < buffer.frames(); i++) {
                if (channels == 1) {
                    mono[i] = (short) (((raw[i] & 0xFF) - 128) << 8);
                } else {
                    int sum = 0;
                    for (int ch = 0; ch < channels; ch++) {
                        sum += (raw[i * channels + ch] & 0xFF) - 128;
                    }
                    mono[i] = (short) ((sum / channels) << 8);
                }
            }
        } else {
            throw new IllegalArgumentException("Unsupported PCM bit depth: " + bitsPerSample);
        }

        return shortsToBytes(mono);
    }

    private byte[] floatToMono16(WasapiCom.CaptureBuffer buffer) {
        int floatsPerFrame = channels;
        float[] samples = buffer.data().getFloatArray(0, buffer.frames() * floatsPerFrame);
        short[] mono = new short[buffer.frames()];

        for (int i = 0; i < buffer.frames(); i++) {
            float sample;
            if (channels == 1) {
                sample = samples[i];
            } else {
                float sum = 0;
                for (int ch = 0; ch < channels; ch++) {
                    sum += samples[i * channels + ch];
                }
                sample = sum / channels;
            }
            sample = Math.max(-1f, Math.min(1f, sample));
            mono[i] = (short) Math.round(sample * 32767f);
        }

        return shortsToBytes(mono);
    }

    private static byte[] shortsToBytes(short[] mono) {
        byte[] out = new byte[mono.length * 2];
        ByteBuffer.wrap(out).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().put(mono);
        return out;
    }
}
