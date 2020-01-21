package by.andd3dfx.pravtor.model;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BatchSearchResult {

    private final String topic;
    private final List<TorrentData> dataItems;
}
