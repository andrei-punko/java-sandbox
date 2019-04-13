package by.andd3dfx.pravtor;

import java.util.List;

public class SearchResult {

    private List<TorrentData> dataItems;
    private String nextPageUrl;
    private String prevPageUrl;

    public SearchResult(List<TorrentData> dataItems, String prevPageUrl, String nextPageUrl) {
        this.dataItems = dataItems;
        this.prevPageUrl = prevPageUrl;
        this.nextPageUrl = nextPageUrl;
    }

    public List<TorrentData> getDataItems() {
        return dataItems;
    }

    public void setDataItems(List<TorrentData> dataItems) {
        this.dataItems = dataItems;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setPrevPageUrl(String prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }
}
