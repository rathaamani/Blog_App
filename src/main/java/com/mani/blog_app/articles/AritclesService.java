package com.mani.blog_app.articles;

import com.mani.blog_app.articles.dtos.CreateArticleRequest;
import com.mani.blog_app.users.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class AritclesService {
    private ArticleRepository articleRepository;
    private UsersRepository usersRepository;

    public AritclesService(ArticleRepository articleRepository, UsersRepository usersRepository) {
        this.articleRepository = articleRepository;
        this.usersRepository = usersRepository;
    }

    public AritclesService(ArticleRepository articleRepository){
         this.articleRepository = articleRepository;
    }
    public Iterable<ArticleEntity> getAllArticles(){
         return articleRepository.findAll();
    }

    public ArticleEntity getArticleBySlug(String slug){
      var article = articleRepository.findBySlug(slug);
      if(article == null){
          throw new ArticleNotFoundException(slug);
      }
        return articleRepository.findBySlug(slug);
    }
    public ArticleEntity createArticle(CreateArticleRequest a,Long authorId){
         return articleRepository.save(ArticleEntity.builder()
                 .title(a.getTitle())
                 .body(a.getBody())
                 .build()
         );
    }
    public ArticleEntity updateArticle(Long articleId, CreateArticleRequest a){
        var article = articleRepository.findById(articleId).orElseThrow(()-> new ArticleNotFoundException(articleId));

        if(a.getTitle() != null){
            article.setTitle(a.getTitle());
            article.setSlug(a.getTitle().toLowerCase().replaceAll("\\s+", "-"));
        }
        if(a.getBody() != null){
            article.setBody(a.getBody());
        }
        if(a.getSubtitle() != null){
            article.setSubtitle(a.getSubtitle());
        }

        return articleRepository.save(article);
    }
    static class ArticleNotFoundException extends IllegalArgumentException{
         public ArticleNotFoundException(String slug){
              super("Article"+ slug +"Not Found");
         }
        public ArticleNotFoundException(Long id){
            super("Article"+ id +"Not Found");
        }
    }
}
