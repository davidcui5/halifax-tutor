package group12.search;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {
    private boolean success;
    private int numOfResults;
    private List<TutorPublicInfo> results;

    public SearchResponse() {
        this.success = false;
        this.numOfResults = 0;
        this.results = new ArrayList<>();
    }

    public SearchResponse(boolean success, int numOfResults, List<TutorPublicInfo> results) {
        this.success = success;
        this.numOfResults = numOfResults;
        this.results = results;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setNumOfResults(int numOfResults) {
        this.numOfResults = numOfResults;
    }

    public void setResults(List<TutorPublicInfo> results) {
        this.results = results;
    }

    public int getNumOfResults() {
        return numOfResults;
    }

    public boolean getSuccess() {
        return success;
    }

    public List<TutorPublicInfo> getResults() {
        return results;
    }
}
