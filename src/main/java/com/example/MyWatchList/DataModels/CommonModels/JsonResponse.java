package com.example.MyWatchList.DataModels.CommonModels;

public class JsonResponse<T> {
    private T[] results;
    private T[] posters;

    public T[] getResults() {
        return results;
    }
    public T[] getPosters() { return posters;}

    public void setResults(T[] resutls){
        this.results = resutls;
    }
    public void setPosters(T[] posters){
        this.posters = posters;
    }
}

