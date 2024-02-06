package com.mani.blog_app.users;

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

    public UsersController(UsersService usersService, ModelMapper modelMapper) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }
// ModelMapping it's help to mapping Entity to DTOS and DTOs to Entity
    @PostMapping("")
    ResponseEntity<UserResponse> signupUser(@RequestBody CreateUserRequest request){
        UserEntity savedUser = usersService.createUser(request);
        URI savedUserUri = URI.create(" /users/ "+ savedUser.getId());

        return ResponseEntity.created(savedUserUri)
                .body(modelMapper.map(savedUser, UserResponse.class));
}

    @PostMapping("/login")
        ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserRequest request){
            UserEntity savedUser = usersService.loginUser(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(modelMapper.map(savedUser, UserResponse.class));
        }
        @ExceptionHandler(UsersService.UserNotFoundException.class)
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


}