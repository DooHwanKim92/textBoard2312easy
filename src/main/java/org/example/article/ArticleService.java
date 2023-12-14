package org.example.article;

import org.example.global.Container;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArticleService {
    ArticleRepository articleRepository;
    public ArticleService() {
        articleRepository = new ArticleRepository();
    }
    public int write(String title, String content) {
        return this.articleRepository.write(title, content);
    }

    public void remove(Article article) {
        this.articleRepository.remove(article);
    }
    public void modify(Article article, String title, String content) {
        this.articleRepository.modify(article,title,content);
    }
    public List<Article> findByAll() {
        return this.articleRepository.findByAll();
    }
    public List<Article> findByAllIntoMemberId() {
        return this.articleRepository.findByAllIntoMemberId();
    }
    public Article articleFindById(int id) {
        return articleRepository.articleFindById(id);
    }
}