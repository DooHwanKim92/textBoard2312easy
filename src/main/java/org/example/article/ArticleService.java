package org.example.article;

import org.example.global.Container;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArticleService {
    int id = 1;
    List<Article> articleList = new ArrayList<>();
    ArticleRepository articleRepository;
    public ArticleService() {
        articleRepository = new ArticleRepository();
    }
    public int write(String title, String content) {
        return this.articleRepository.write(title, content);
    }
    public int remove(Article article) {
        return this.articleRepository.remove(article);
    }
    public int modify(Article article, String title, String content) {
        return this.articleRepository.modify(article,title,content);
    }
    public List<Article> findByAll() {
        return this.articleRepository.findByAll();
    }
    public Article articleFindById(int id) {
        return articleRepository.articleFindById(id);
    }
}
