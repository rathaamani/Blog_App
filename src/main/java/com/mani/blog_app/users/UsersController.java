package com.mani.blog_app.users;

import com.mani.blog_app.security.JWTService;
import com.mani.blog_app.users.dtos.CreateUserRequest;
import com.mani.blog_app.users.dtos.LoginUserRequest;
import com.mani.blog_app.users.dtos.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;

    public UsersController(UsersService usersService, ModelMapper modelMapper, JWTService jwtService) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    // ModelMapping it's help to mapping Entity to DTOS and DTOs to Entity
    @PostMapping("")
    ResponseEntity<UserResponse> signupUser(@RequestBody CreateUserRequest request){
        UserEntity savedUser = usersService.createUser(request);
        URI savedUserUri = URI.create("/users/"+ savedUser.getId());
        var UserResponse = modelMapper.map(savedUser, UserResponse.class);
        UserResponse.setToken(
                jwtService.CreateJwt(savedUser.getId())
        );
        return ResponseEntity.created(savedUserUri)
                .body(UserResponse);
}

    @PostMapping("/login")
        ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserRequest request){
            UserEntity savedUser = usersService.loginUser(request.getUsername(), request.getPassword());
            var UserResponse = modelMapper.map(savedUser, UserResponse.class);
        UserResponse.setToken(
                jwtService.CreateJwt(savedUser.getId()));
        return ResponseEntity.ok(UserResponse);
        }
        @ExceptionHandler({
                UsersService.UserNotFoundException.class,
                UsersService.InvalidCredentialsException.class
        })
        ResponseEntity<String> handleUserNotFoundException(Exception ex) {
            String message;
            HttpStatus status;

            if (ex instanceof UsersService.UserNotFoundException) {
                message = ex.getMessage();
                status = HttpStatus.NOT_FOUND;
            } else {
                message = "Something went wrong";
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }

            return ResponseEntity.status(status).body(message);
        }

    ResponseEntity<String> InvalidCredentialsException(Exception ex) {
        String message;
        HttpStatus status;

        if (ex instanceof UsersService.UserNotFoundException) {
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        } else {
            message = "Something went wrong";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(status).body(message);
    }


}