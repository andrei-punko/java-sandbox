package by.andd3dfx.multithreading.storage.util;

import by.andd3dfx.multithreading.storage.model.Truck;
import java.util.List;

public class CustomUtil {

    private static final double TIME_SCALE_FACTOR = 10;

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException("Something going wrong in sleep()", e);
        }
    }

    public static long convertToMillisScaled(double value) {
        return (long) (value * TIME_SCALE_FACTOR);
    }

    public static double extractMaxTruckTime(List<Truck> trucks) {
        double maxValue = 0;
        for (Truck truck : trucks) {
            if (truck.getTimeSpent() > maxValue) {
                maxValue = truck.getTimeSpent();
            }
        }
        return maxValue;
    }
}
