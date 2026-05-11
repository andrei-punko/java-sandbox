package by.andd3dfx.capturesound.fft;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

import by.andd3dfx.capturesound.dto.FrequencyInfoContainer;
import org.junit.Test;

public class FrequencyScannerTest {

    private static final int SAMPLE_RATE = 44_100;

    @Test
    public void emptyInputYieldsZeroDominantFrequency() {
        FrequencyScanner scanner = new FrequencyScanner();
        FrequencyInfoContainer r = scanner.extractFrequencyInfo(new short[0], SAMPLE_RATE);
        assertThat(r.maxFrequency(), closeTo(0.0, 1e-9));
    }

    @Test
    public void peakNearPureSineFrequency() {
        double targetHz = 1_000.0;
        int samples = 8_192;
        short[] pcm = sine(samples, SAMPLE_RATE, targetHz, 12_000.0);

        FrequencyScanner scanner = new FrequencyScanner();
        FrequencyInfoContainer r = scanner.extractFrequencyInfo(pcm, SAMPLE_RATE);

        double binWidth = (double) SAMPLE_RATE / (25.0 * samples);
        double toleranceHz = Math.max(8.0, 4.0 * binWidth);

        assertThat(r.maxFrequency(), closeTo(targetHz, toleranceHz));
    }

    private static short[] sine(int samples, int sampleRate, double freqHz, double amplitude) {
        short[] s = new short[samples];
        for (int i = 0; i < samples; i++) {
            double v = amplitude * Math.sin(2.0 * Math.PI * freqHz * i / sampleRate);
            s[i] = (short) Math.max(Short.MIN_VALUE, Math.min(Short.MAX_VALUE, Math.round(v)));
        }
        return s;
    }
}
