package com.mani.blog_app.users;

import com.mani.blog_app.users.dtos.CreateUserRequest;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    public UserEntity createUser(CreateUserRequest req){
        var newUser = UserEntity.builder()
                .username(req.getUsername())
//                .password(password) // TODO: encrypt password
                .email(req.getEmail())
                .build();

        return usersRepository.save(newUser);
    }
    public UserEntity getUser(String username){
        return usersRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }
    public UserEntity getUser(Long userId){
        return usersRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
    public UserEntity loginUser(String username, String password){
        var user = usersRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        //TODO: match password
        return user;
    }
    public static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(String username) {
            super("User with username: " + username + "not found");
        }
        public UserNotFoundException(Long authorId) {
            super("User with id: " + authorId + "not found");
        }
    }
}
