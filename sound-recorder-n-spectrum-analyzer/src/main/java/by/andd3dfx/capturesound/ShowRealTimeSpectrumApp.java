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

/**
 * Application that shows real-time spectrum
 */
public class ShowRealTimeSpectrumApp {

    public static void main(String[] args) throws LineUnavailableException {
        FrequencyScanner frequencyScanner = new FrequencyScanner();
        AudioFormat audioFormat = getAudioFormat();
        DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
        targetDataLine.open(audioFormat);

        ChartContainer chartContainer = new ChartContainer();
        chartContainer.show();

        byte tempBuffer[] = new byte[10_000];
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
            double[] frequencies = frequencyInfoContainer.getFrequencies();
            double[] magnitudes = frequencyInfoContainer.getMagnitudes();

            int N = 1_000;
            double[] xData = new double[N];
            double[] yData = new double[N];
            for (int bucketIndex = 0; bucketIndex < N; bucketIndex++) {
                xData[bucketIndex] = frequencies[(int) ((bucketIndex + 0.5) * magnitudes.length / N)];

                for (int i = 0; i < magnitudes.length / N; i++) {
                    yData[bucketIndex] += magnitudes[bucketIndex * magnitudes.length / N + i];
                }
            }
            int maxFrequency = (int) frequencyInfoContainer.getMaxFrequency();

            String title = String.format("%d Hz", maxFrequency);
            chartContainer.update(xData, yData, title);
        }
    }

    private static AudioFormat getAudioFormat() {
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
