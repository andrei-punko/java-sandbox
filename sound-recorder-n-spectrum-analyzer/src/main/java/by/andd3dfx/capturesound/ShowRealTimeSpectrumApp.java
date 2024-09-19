package by.andd3dfx.capturesound;

import by.andd3dfx.capturesound.dto.FrequencyInfoContainer;
import by.andd3dfx.capturesound.fft.FrequencyScanner;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/**
 * Application that shows real-time spectrum
 */
public class ShowRealTimeSpectrumApp {

    private final static int BUCKETS_AMOUNT = 1_000;

    public static void main(String[] args) throws LineUnavailableException {
        FrequencyScanner frequencyScanner = new FrequencyScanner();
        AudioFormat audioFormat = buildAudioFormat();
        DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
        targetDataLine.open(audioFormat);

        ChartContainer chartContainer = new ChartContainer();
        chartContainer.show();

        byte tempBuffer[] = new byte[10_000];
        double[] xData = new double[BUCKETS_AMOUNT];
        double[] yData = new double[BUCKETS_AMOUNT];
        while (true) {
            targetDataLine.start();
            int count = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
            if (count > 0) {
                byteArrayOutputStream.write(tempBuffer, 0, count);
            }
            byte audioData[] = byteArrayOutputStream.toByteArray();
            targetDataLine.stop();
            byteArrayOutputStream.reset();

            FrequencyInfoContainer frequencyInfoContainer = frequencyScanner.detectFrequency(audioData, (int) audioFormat.getSampleRate());
            double[] frequencies = frequencyInfoContainer.frequencies();
            double[] magnitudes = frequencyInfoContainer.magnitudes();

            Arrays.fill(xData, 0);
            Arrays.fill(yData, 0);
            final int magnitudesPerBucket = magnitudes.length / BUCKETS_AMOUNT;
            for (int bucket = 0; bucket < BUCKETS_AMOUNT; bucket++) {
                xData[bucket] = frequencies[(int) ((bucket + 0.5) * magnitudesPerBucket)];

                for (int i = 0; i < magnitudesPerBucket; i++) {
                    yData[bucket] += magnitudes[bucket * magnitudesPerBucket + i];
                }
            }

            var maxFrequency = frequencyInfoContainer.maxFrequency();
            String title = String.format("%1$.0f Hz", maxFrequency);

            chartContainer.update(xData, yData, title);
        }
    }

    private static AudioFormat buildAudioFormat() {
        return new AudioFormat(44_100.0F, 16, 1, true, false);
    }

    static class ChartContainer {
        private XYChart chart;
        private SwingWrapper<XYChart> chartSwingWrapper;
        private final String SERIES_NAME = "M(f)";

        public ChartContainer() {
            chart = QuickChart.getChart("XChart Real-time Demo", "frequency", "magnitude",
                    SERIES_NAME, new double[]{1}, new double[]{1});
            chart.getStyler().setXAxisLogarithmic(true);
        }

        public void show() {
            chartSwingWrapper = new SwingWrapper<>(chart);
            chartSwingWrapper.displayChart();
        }

        public void update(double[] xData, double[] yData, String title) {
            chart.updateXYSeries(SERIES_NAME, xData, yData, null);
            chart.setTitle(title);
            chartSwingWrapper.repaintChart();
        }
    }
}
