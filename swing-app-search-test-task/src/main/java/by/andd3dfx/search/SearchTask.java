package by.andd3dfx.search;

import java.util.List;

public class SearchTask implements Runnable {

    private final String searchText;
    private final int maxResults;
    private final PostSearchActionRunner postSearchActionRunner;

    private final SearchHelper searchHelper = new SearchHelper();

    public SearchTask(String searchText, int maxResults, PostSearchActionRunner postSearchActionRunner) {
        this.searchText = searchText;
        this.maxResults = maxResults;
        this.postSearchActionRunner = postSearchActionRunner;
    }

    @Override
    public void run() {
        List<SearchResultItem> searchResultItems = searchHelper.search(searchText, maxResults);
        postSearchActionRunner.searchPerformed(searchResultItems);
    }
}
