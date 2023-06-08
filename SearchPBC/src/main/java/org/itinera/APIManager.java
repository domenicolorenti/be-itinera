package org.itinera;


public class APIManager {

    private static APIManager instance = null;

    private APIManager() {}

    public static APIManager getInstance() {
        if(instance == null)
            instance = new APIManager();
        return instance;
    }

    public List<String> getCities() {
        
    }
}
