package com.mani.blog_app.articles;

import com.mani.blog_app.users.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @GetMapping("")
    String getArticles(){
        return " get all Articles";
    }
    @GetMapping("/{id}")
    String getArticlesById(@PathVariable("id") String id){
        return "get article with id: " + id;
    }
    @PostMapping("")
    String createArticle(@AuthenticationPrincipal UserEntity user){
        return "Create Article called by " + user.getUsername();
    }
}
