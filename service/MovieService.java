package service;

import repository.MovieRepository;

public class MovieService {
    private static MovieService service;
    public static MovieService getRepository() {
        if(service == null) service = new MovieService();
        return service;
    }


}
