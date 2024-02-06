package com.mani.blog_app.comments;

import org.springframework.stereotype.Service;

@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;
    public CommentsService(CommentsRepository commentsRepository){
        this.commentsRepository = commentsRepository;
    }
}
