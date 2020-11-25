package by.andd3dfx.capturesound;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class FrequencyDetector {

    private static FrequencyScanner frequencyScanner = new FrequencyScanner();

    public static double detectFrequency(byte[] audioData, int sampleRate) {
        short[] sampleData = new short[audioData.length / 2];
        ByteBuffer.wrap(audioData).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(sampleData);
        return frequencyScanner.extractFrequency(sampleData, sampleRate);
    }
}
