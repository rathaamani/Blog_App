package com.mani.blog_app.articles.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Data
@Setter(AccessLevel.NONE)
public class UpdateArticleRequest {
    @NonNull
    private String title;
    @NonNull
    private String body;
    @Nullable
    private String subtitle;

}
