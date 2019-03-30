package by.andd3dfx.search.pravtor;

public class TorrentData {

    private String label;
    private String relativeLink;
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

    public String getRelativeLink() {
        return relativeLink;
    }

    public void setRelativeLink(String relativeLink) {
        this.relativeLink = relativeLink;
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
            ", relativeLink='" + relativeLink + '\'' +
            ", seedsCount=" + seedsCount +
            ", peersCount=" + peersCount +
            ", downloadedCount=" + downloadedCount +
            ", size='" + size + '\'' +
            '}';
    }
}
