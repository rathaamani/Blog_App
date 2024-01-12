package com.mani.blog_app.users.dtos;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@Getter

public class CreateUserRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String email;
}
