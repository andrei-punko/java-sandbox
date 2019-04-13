package by.andd3dfx.pravtor.model;

import java.util.List;

public class BatchSearchResult {

    private final String topic;
    private final List<TorrentData> dataItems;

    public BatchSearchResult(String topic, List<TorrentData> dataItems) {
        this.topic = topic;
        this.dataItems = dataItems;
    }

    public String getTopic() {
        return topic;
    }

    public List<TorrentData> getDataItems() {
        return dataItems;
    }
}
