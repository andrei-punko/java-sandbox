package by.andd3dfx.pravtor.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class SearchCriteria {

    private final String topic;
    private final String url;
}
