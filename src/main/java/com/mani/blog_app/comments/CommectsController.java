package com.mani.blog_app.comments;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles/{article-slug}/commects")
public class CommectsController {
    @GetMapping("")
    public String getComments(){
        return "comments";
    }
}
