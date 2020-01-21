package by.andd3dfx.pravtor.model;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SingleSearchResult {

    private final List<TorrentData> dataItems;
    private final String prevPageUrl;
    private final String nextPageUrl;
}
