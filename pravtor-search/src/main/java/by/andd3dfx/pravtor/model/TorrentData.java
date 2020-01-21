package by.andd3dfx.pravtor.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TorrentData {

    private String label;
    private String linkUrl;
    private Integer seedsCount;
    private Integer peersCount;
    private Integer downloadedCount;
    private String size;
}
