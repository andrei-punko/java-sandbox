package by.andd3dfx.sitesparsing.onlinerby.dto;

public class CpuItem {

    private String name;
    private String url;
    private double price;

    private int coresAmount;
    private int threadsAmount;
    private double frequency;

    public CpuItem(String name, String url, double price) {
        this.name = name;
        this.url = url;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public double getPrice() {
        return price;
    }

    public int getCoresAmount() {
        return coresAmount;
    }

    public int getThreadsAmount() {
        return threadsAmount;
    }

    public double getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return "CpuItem{" +
            "name='" + name + '\'' +
            ", url='" + url + '\'' +
            ", price=" + price +
            ", coresAmount=" + coresAmount +
            ", threadsAmount=" + threadsAmount +
            ", frequency=" + frequency +
            '}';
    }
}
