package by.andd3dfx.pravtor.model;

import java.util.List;

public class SingleSearchResult {

    private final List<TorrentData> dataItems;
    private final String prevPageUrl;
    private final String nextPageUrl;

    public SingleSearchResult(List<TorrentData> dataItems, String prevPageUrl, String nextPageUrl) {
        this.dataItems = dataItems;
        this.prevPageUrl = prevPageUrl;
        this.nextPageUrl = nextPageUrl;
    }

    public List<TorrentData> getDataItems() {
        return dataItems;
    }

    public String getPrevPageUrl() {
        return prevPageUrl;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }
}
