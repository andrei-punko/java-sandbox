package by.andd3dfx.search.pravtor;

public class SearchCriteria {

    private String startingPageUrl;
    private int minStartingIndex = 0;
    private int maxStartingIndex = 0;
    private int step = 50;

    public SearchCriteria() {
    }

    public SearchCriteria(String startingPageUrl, int maxStartingIndex) {
        this.startingPageUrl = startingPageUrl;
        this.maxStartingIndex = maxStartingIndex;
    }

    public String getStartingPageUrl() {
        return startingPageUrl;
    }

    public void setStartingPageUrl(String startingPageUrl) {
        this.startingPageUrl = startingPageUrl;
    }

    public int getMinStartingIndex() {
        return minStartingIndex;
    }

    public void setMinStartingIndex(int minStartingIndex) {
        this.minStartingIndex = minStartingIndex;
    }

    public int getMaxStartingIndex() {
        return maxStartingIndex;
    }

    public void setMaxStartingIndex(int maxStartingIndex) {
        this.maxStartingIndex = maxStartingIndex;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
