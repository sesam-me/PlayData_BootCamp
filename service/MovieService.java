package service;

import domain.dto.UserDto;
import repository.MovieRepository;

public class MovieService {
    private static MovieService service;

    public static MovieService getRepository() {
        if(service == null) service = new MovieService();
        return service;
    }

    public UserDto login(String userId, String userPwd) {
        return new MovieRepository().login(userId, userPwd);
    }


}
