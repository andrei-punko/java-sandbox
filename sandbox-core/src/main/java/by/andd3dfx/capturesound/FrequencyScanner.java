package by.andd3dfx.capturesound;

import org.jtransforms.fft.DoubleFFT_1D;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

/**
 * Frequency extraction taken from this page:
 * <p>
 * https://stackoverflow.com/questions/7649003/jtransforms-fft-in-android-from-pcm-data
 */
public class FrequencyScanner {

    private double[] window;

    public FrequencyScanner() {
        window = null;
    }

    /**
     * extract the dominant frequency from 16bit PCM data.
     *
     * @param sampleData an array containing the raw 16bit PCM data.
     * @param sampleRate the sample rate (in HZ) of sampleData
     * @return an approximation of the dominant frequency in sampleData
     */
    public double extractFrequency(short[] sampleData, int sampleRate) {
        /* sampleData + zero padding */
        DoubleFFT_1D fft = new DoubleFFT_1D(sampleData.length + 24 * sampleData.length);
        double[] a = new double[(sampleData.length + 24 * sampleData.length) * 2];

        System.arraycopy(applyWindow(sampleData), 0, a, 0, sampleData.length);
        fft.realForward(a);

        double frequencies[] = new double[a.length / 2];
        double magnitudes[] = new double[a.length / 2];

        /* find the peak magnitude and it's index */
        double maxMag = Double.NEGATIVE_INFINITY;
        int maxInd = -1;

        for (int i = 0; i < a.length / 2; ++i) {
            double re = a[2 * i];
            double im = a[2 * i + 1];
            double mag = Math.sqrt(re * re + im * im);
            frequencies[i] = (double) sampleRate * i / (a.length / 2);
            magnitudes[i] = mag;

            if (mag > maxMag) {
                maxMag = mag;
                maxInd = i;
            }
        }
        drawSpectrum(frequencies, magnitudes);

        /* calculate the frequency */
        return (double) sampleRate * maxInd / (a.length / 2);
    }

    private void drawSpectrum(double[] frequencies, double[] magnitudes) {
        int N = 1000;
        double[] xData = new double[N];
        double[] yData = new double[N];
        for (int bucketIndex = 0; bucketIndex < N; bucketIndex++) {
            xData[bucketIndex] = frequencies[(int) ((bucketIndex + 0.5) * frequencies.length / N)];

            for (int i = 0; i < magnitudes.length / N; i++) {
                yData[bucketIndex] += magnitudes[bucketIndex * N + i];
            }
        }

        Thread thread = new Thread(() -> {
            // Create Chart
            XYChart chart = QuickChart.getChart("Spectrum Chart", "Frequency", "Magnitude", "M(f)", xData, yData);
            chart.getStyler().setXAxisLogarithmic(true);
            // Show it
            new SwingWrapper(chart).displayChart();
        });
        thread.start();
    }

    /**
     * build a Hamming window filter for samples of a given size
     * See http://www.labbookpages.co.uk/audio/firWindowing.html#windows
     *
     * @param size the sample size for which the filter will be created
     */
    private void buildHammingWindow(int size) {
        if (window != null && window.length == size) {
            return;
        }
        window = new double[size];
        for (int i = 0; i < size; ++i) {
            window[i] = .54 - .46 * Math.cos(2 * Math.PI * i / (size - 1.0));
        }
    }

    /**
     * apply a Hamming window filter to raw input data
     *
     * @param input an array containing unfiltered input data
     * @return a double array containing the filtered data
     */
    private double[] applyWindow(short[] input) {
        double[] res = new double[input.length];

        buildHammingWindow(input.length);
        for (int i = 0; i < input.length; ++i) {
            res[i] = (double) input[i] * window[i];
        }
        return res;
    }
}
