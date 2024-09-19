package by.andd3dfx.capturesound.fft;

import by.andd3dfx.capturesound.dto.FrequencyInfoContainer;
import org.jtransforms.fft.DoubleFFT_1D;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Frequency extraction taken from this page:
 * <p>
 * https://stackoverflow.com/questions/7649003/jtransforms-fft-in-android-from-pcm-data
 */
public class FrequencyScanner {

    public FrequencyInfoContainer detectFrequency(byte[] audioData, int sampleRate) {
        short[] sampleData = new short[audioData.length / 2];
        ByteBuffer.wrap(audioData).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(sampleData);
        return extractFrequencyInfo(sampleData, sampleRate);
    }

    /**
     * extract the dominant frequency from 16bit PCM data.
     *
     * @param sampleData an array containing the raw 16bit PCM data.
     * @param sampleRate the sample rate (in HZ) of sampleData
     * @return an approximation of the dominant frequency in sampleData
     */
    public FrequencyInfoContainer extractFrequencyInfo(short[] sampleData, int sampleRate) {
        /* sampleData + zero padding */
        final int sampleRateLen = sampleData.length;
        final int aLen_2 = sampleRateLen + 24 * sampleRateLen;
        double[] a = new double[aLen_2 * 2];
        var fft = new DoubleFFT_1D(aLen_2);

        System.arraycopy(applyWindow(sampleData), 0, a, 0, sampleRateLen);
        fft.realForward(a);

        double[] frequencies = new double[aLen_2];
        double[] magnitudes = new double[aLen_2];

        /* find the peak magnitude and it's index */
        double maxMag = Double.NEGATIVE_INFINITY;
        int maxInd = -1;

        for (int i = 0; i < aLen_2; ++i) {
            double re = a[2 * i];
            double im = a[2 * i + 1];
            double mag = Math.sqrt(re * re + im * im);
            frequencies[i] = (double) sampleRate * i / aLen_2;
            magnitudes[i] = mag;

            if (mag > maxMag) {
                maxMag = mag;
                maxInd = i;
            }
        }
        double maxFrequency = (double) sampleRate * maxInd / aLen_2;
        return new FrequencyInfoContainer(frequencies, magnitudes, maxFrequency);
    }

    /**
     * Apply a Hamming window filter to raw input data
     *
     * @param input an array containing unfiltered input data
     * @return a double array containing the filtered data
     */
    private double[] applyWindow(short[] input) {
        var len = input.length;
        double[] res = new double[len];

        double[] windowFilter = buildHammingWindow(len);
        for (int i = 0; i < len; ++i) {
            res[i] = (double) input[i] * windowFilter[i];
        }
        return res;
    }

    /**
     * Build a Hamming window filter for samples of a given size
     * <p>
     * See <a href="http://www.labbookpages.co.uk/audio/firWindowing.html#windows">link</a> for details
     *
     * @param size the sample size for which the filter will be created
     */
    private double[] buildHammingWindow(int size) {
        var res = new double[size];
        for (int i = 0; i < size; ++i) {
            res[i] = 0.54 - 0.46 * Math.cos(2 * Math.PI * i / (size - 1.0));
        }
        return res;
    }
}
