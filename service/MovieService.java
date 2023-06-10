package service;

import controller.MovieController;
import domain.dto.MovieDto;
import repository.MovieRepository;

import java.util.List;

public class MovieService {
    private static MovieService service;

    public static MovieService getService() {
        if(service == null) service = new MovieService();
        return service;
    }

    public List<MovieDto> shownMovies() {
        return MovieRepository.getRepository().shownMovies();
    }

    public int watchMovie(int movie_seq) {
        return MovieRepository.getRepository().watchMovie(movie_seq);
    }
}
