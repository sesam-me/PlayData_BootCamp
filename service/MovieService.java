package service;

import domain.dto.MovieDto;
import domain.dto.UserDto;
import repository.MovieRepository;

public class MovieService {
    private static MovieService service;

    public static MovieService getService() {
        if(service == null) service = new MovieService();
        return service;
    }

    public UserDto login(String userId, String userPwd) {
        return MovieRepository.getRepository().login(userId, userPwd);
    }

    public int insertMovieInfo(MovieDto movieDto) {
        return MovieRepository.getRepository().insertMovieInfo(movieDto);
    }

}
