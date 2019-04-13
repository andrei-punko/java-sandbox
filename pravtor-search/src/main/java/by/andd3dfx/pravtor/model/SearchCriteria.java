package by.andd3dfx.pravtor.model;

public class SearchCriteria {

    private final String topic;
    private final String url;

    public SearchCriteria(String topic, String url) {
        this.topic = topic;
        this.url = url;
    }

    public String getTopic() {
        return topic;
    }

    public String getUrl() {
        return url;
    }
}
