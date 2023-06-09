package service;

import domain.dto.UserDto;
import repository.UserRepository;

public class UserService {
    private static UserService service;

    public static UserService getService() {
        if(service == null) service = new UserService();
        return service;
    }

    public UserDto login(String userId, String userPwd) {
        return UserRepository.getRepository().login(userId, userPwd);
    }

    public int signUp(UserDto dto) {
        return UserRepository.getRepository().signUp(dto);
    }


}
