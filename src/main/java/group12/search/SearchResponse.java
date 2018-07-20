package group12.search;

public class SearchResponse {
    private boolean success;
    private int numOfResults;

    public SearchResponse() {
        this.success = false;
        this.numOfResults = 0;
    }

    public SearchResponse(boolean success, int numOfResults) {
        this.success = success;
        this.numOfResults = numOfResults;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setNumOfResults(int numOfResults) {
        this.numOfResults = numOfResults;
    }

    public int getNumOfResults() {
        return numOfResults;
    }

    public boolean getSuccess() {
        return success;
    }
}
