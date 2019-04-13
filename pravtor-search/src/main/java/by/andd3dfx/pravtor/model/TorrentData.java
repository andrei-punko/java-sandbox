package by.andd3dfx.pravtor.model;

public class TorrentData {

    private String label;
    private String linkUrl;
    private Integer seedsCount;
    private Integer peersCount;
    private Integer downloadedCount;
    private String size;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getSeedsCount() {
        return seedsCount;
    }

    public void setSeedsCount(Integer seedsCount) {
        this.seedsCount = seedsCount;
    }

    public Integer getPeersCount() {
        return peersCount;
    }

    public void setPeersCount(Integer peersCount) {
        this.peersCount = peersCount;
    }

    public Integer getDownloadedCount() {
        return downloadedCount;
    }

    public void setDownloadedCount(Integer downloadedCount) {
        this.downloadedCount = downloadedCount;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "TorrentData{" +
            "label='" + label + '\'' +
            ", linkUrl='" + linkUrl + '\'' +
            ", seedsCount=" + seedsCount +
            ", peersCount=" + peersCount +
            ", downloadedCount=" + downloadedCount +
            ", size='" + size + '\'' +
            '}';
    }
}
