package by.andd3dfx.capturesound.dto;

public record FrequencyInfoContainer(
        double[] frequencies,
        double[] magnitudes,
        double maxFrequency) {
}
