package org.example.article;

import java.util.List;

public class ArticleService {
    ArticleRepository articleRepository;
    public ArticleService() {
        articleRepository = new ArticleRepository();
    }
    public int write(String title, String content) {
        return this.articleRepository.write(title, content);
    }
    public void remove(int removeId) {
        this.articleRepository.remove(removeId);
    }
    public void modify(int modifyId, String title, String content) {
        this.articleRepository.modify(modifyId,title,content);
    }
    public List<Article> findByAll() {
        return this.articleRepository.findByAll();
    }
    public Article articleFindById(int id) {
        return articleRepository.articleFindById(id);
    }
}
