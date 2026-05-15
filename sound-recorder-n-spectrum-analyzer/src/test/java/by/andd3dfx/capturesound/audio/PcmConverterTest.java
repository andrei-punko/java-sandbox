package by.andd3dfx.capturesound.audio;

import com.sun.jna.Memory;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class PcmConverterTest {

    @Test
    public void stereoFloatDownmixesToMono() {
        WaveFormatEx format = new WaveFormatEx();
        format.wFormatTag = (short) WaveFormatEx.WAVE_FORMAT_IEEE_FLOAT;
        format.nChannels = 2;
        format.nSamplesPerSec = 48_000;
        format.nBlockAlign = 8;
        format.wBitsPerSample = 32;
        format.cbSize = 0;

        Memory memory = new Memory(8);
        memory.setFloat(0, 1.0f);
        memory.setFloat(4, -1.0f);

        PcmConverter converter = new PcmConverter(format);
        byte[] pcm = converter.toMonoPcm16(new WasapiCom.CaptureBuffer(memory, 1, 0));

        short sample = ByteBuffer.wrap(pcm).order(ByteOrder.LITTLE_ENDIAN).getShort(0);
        assertThat(sample, is((short) 0));
    }

    @Test
    public void monoFloatPreservesAmplitude() {
        WaveFormatEx format = new WaveFormatEx();
        format.wFormatTag = (short) WaveFormatEx.WAVE_FORMAT_IEEE_FLOAT;
        format.nChannels = 1;
        format.nSamplesPerSec = 44_100;
        format.nBlockAlign = 4;
        format.wBitsPerSample = 32;
        format.cbSize = 0;

        Memory memory = new Memory(4);
        memory.setFloat(0, 0.5f);

        PcmConverter converter = new PcmConverter(format);
        byte[] pcm = converter.toMonoPcm16(new WasapiCom.CaptureBuffer(memory, 1, 0));

        short sample = ByteBuffer.wrap(pcm).order(ByteOrder.LITTLE_ENDIAN).getShort(0);
        assertThat(sample / 32767.0, closeTo(0.5, 0.02));
    }

    @Test
    public void extensibleFloatSubFormatIsResolved() {
        Memory memory = new Memory(48);
        memory.setShort(0, (short) WaveFormatEx.WAVE_FORMAT_EXTENSIBLE);
        memory.setShort(2, (short) 2);
        memory.setInt(4, 48_000);
        memory.setInt(8, 48_000 * 2 * 4);
        memory.setShort(12, (short) 8);
        memory.setShort(14, (short) 32);
        memory.setShort(16, (short) 22);
        memory.setShort(18, (short) 32); // wValidBitsPerSample
        memory.setInt(20, 0x3); // dwChannelMask (stereo)
        // KSDATAFORMAT_SUBTYPE_IEEE_FLOAT
        byte[] floatGuid = {0x03, 0x00, 0x00, 0x00, 0x00, 0x00, 0x10, 0x00,
                (byte) 0x80, 0x00, 0x00, (byte) 0xAA, 0x00, 0x38, (byte) 0x9B, 0x71};
        memory.write(24, floatGuid, 0, floatGuid.length);

        WaveFormatEx format = new WaveFormatEx(memory);
        assertThat(format.effectiveFormatTag(), is(WaveFormatEx.WAVE_FORMAT_IEEE_FLOAT));
        assertThat(format.effectiveBitsPerSample(), is(32));
    }

    @Test
    public void silentBufferReturnsZeroes() {
        WaveFormatEx format = new WaveFormatEx();
        format.wFormatTag = (short) WaveFormatEx.WAVE_FORMAT_IEEE_FLOAT;
        format.nChannels = 2;
        format.nSamplesPerSec = 48_000;
        format.nBlockAlign = 8;
        format.wBitsPerSample = 32;
        format.cbSize = 0;

        PcmConverter converter = new PcmConverter(format);
        byte[] pcm = converter.toMonoPcm16(new WasapiCom.CaptureBuffer(null, 4, 0x2));

        assertThat(pcm.length, is(8));
        assertThat(pcm[0], is((byte) 0));
        assertThat(pcm[1], is((byte) 0));
    }
}
