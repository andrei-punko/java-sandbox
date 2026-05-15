package by.andd3dfx.capturesound.audio;

import com.sun.jna.Structure;

import java.util.List;

/**
 * Win32 {@code WAVEFORMATEX} structure.
 */
public class WaveFormatEx extends Structure {

    public static final int WAVE_FORMAT_PCM = 0x0001;
    public static final int WAVE_FORMAT_IEEE_FLOAT = 0x0003;
    public static final int WAVE_FORMAT_EXTENSIBLE = 0xFFFE;

    public short wFormatTag;
    public short nChannels;
    public int nSamplesPerSec;
    public int nAvgBytesPerSec;
    public short nBlockAlign;
    public short wBitsPerSample;
    public short cbSize;

    public WaveFormatEx() {
        super();
    }

    public WaveFormatEx(com.sun.jna.Pointer p) {
        super(p);
        read();
    }

    @Override
    protected List<String> getFieldOrder() {
        return List.of(
                "wFormatTag",
                "nChannels",
                "nSamplesPerSec",
                "nAvgBytesPerSec",
                "nBlockAlign",
                "wBitsPerSample",
                "cbSize"
        );
    }

    /** Size of base {@code WAVEFORMATEX} structure in bytes. */
    private static final int WAVEFORMATEX_SIZE = 18;
    /** Offset of {@code wValidBitsPerSample} in {@code WAVEFORMATEXTENSIBLE}. */
    private static final int VALID_BITS_OFFSET = 18;
    /** Offset of SubFormat GUID inside {@code WAVEFORMATEXTENSIBLE}. */
    private static final int SUBFORMAT_GUID_OFFSET = 24;

    public int effectiveFormatTag() {
        if (Short.toUnsignedInt(wFormatTag) != WAVE_FORMAT_EXTENSIBLE || cbSize < 22) {
            return Short.toUnsignedInt(wFormatTag);
        }
        byte[] guidBytes = getPointer().getByteArray(SUBFORMAT_GUID_OFFSET, 16);
        // KSDATAFORMAT_SUBTYPE_IEEE_FLOAT: 00000003-0000-0010-8000-00AA00389B71
        if (guidBytes[0] == 0x03 && guidBytes[1] == 0x00 && guidBytes[2] == 0x00 && guidBytes[3] == 0x00) {
            return WAVE_FORMAT_IEEE_FLOAT;
        }
        // KSDATAFORMAT_SUBTYPE_PCM: 00000001-0000-0010-8000-00AA00389B71
        if (guidBytes[0] == 0x01 && guidBytes[1] == 0x00 && guidBytes[2] == 0x00 && guidBytes[3] == 0x00) {
            return WAVE_FORMAT_PCM;
        }
        return Short.toUnsignedInt(wFormatTag);
    }

    public int effectiveBitsPerSample() {
        if (Short.toUnsignedInt(wFormatTag) == WAVE_FORMAT_EXTENSIBLE && cbSize >= 22) {
            return Short.toUnsignedInt(getPointer().getShort(VALID_BITS_OFFSET));
        }
        return Short.toUnsignedInt(wBitsPerSample);
    }
}
