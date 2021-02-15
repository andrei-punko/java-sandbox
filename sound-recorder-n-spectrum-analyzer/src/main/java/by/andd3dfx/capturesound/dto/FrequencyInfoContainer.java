package by.andd3dfx.capturesound.dto;

public class FrequencyInfoContainer {

    private final double[] frequencies;
    private final double[] magnitudes;
    private final double maxFrequency;

    public FrequencyInfoContainer(double[] frequencies, double[] magnitudes, double maxFrequency) {
        this.frequencies = frequencies;
        this.magnitudes = magnitudes;
        this.maxFrequency = maxFrequency;
    }

    public double[] getFrequencies() {
        return frequencies;
    }

    public double[] getMagnitudes() {
        return magnitudes;
    }

    public double getMaxFrequency() {
        return maxFrequency;
    }
}
