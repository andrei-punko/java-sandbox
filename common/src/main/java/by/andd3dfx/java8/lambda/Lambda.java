package by.andd3dfx.java8.lambda;

import java.util.Comparator;
import java.util.List;

public class Lambda {

    public void usualApproach(List<String> names) {
        names.sort(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
    }

    public void lambda1(List<String> names) {
        names.sort((String a, String b) -> {
            return a.compareTo(b);
        });
    }

    public void lambda2(List<String> names) {
        names.sort((String a, String b) -> a.compareTo(b));
    }

    public void lambda3(List<String> names) {
        names.sort((a, b) -> a.compareTo(b));
    }
}
