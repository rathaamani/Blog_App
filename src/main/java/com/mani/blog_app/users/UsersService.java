package com.mani.blog_app.users;

import com.mani.blog_app.users.dtos.CreateUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    public UsersService(UsersRepository usersRepository, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
    }

    public UserEntity createUser(CreateUserRequest req){
        UserEntity newUser= modelMapper.map(req, UserEntity.class);
        // TODO: Encrypt and save Password as well

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
        public UserNotFoundException(String username) { super("Username : " + username + " not found");
        }
        public UserNotFoundException(Long authorId) { super("User Id: " + authorId + " not found");
        }
    }
}
