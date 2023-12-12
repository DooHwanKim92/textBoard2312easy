package org.example.article;

import java.util.List;

public class ArticleService {
    ArticleRepository articleRepository;
    public ArticleService() {
        articleRepository = new ArticleRepository();
    }
    public void write(String title, String content, String userId) {
        this.articleRepository.write(title, content, userId);
    }
    public List<Article> list() {
        return this.articleRepository.list();
    }
    public void remove(int removeId) {
        this.articleRepository.remove(removeId);
    }
    public void modify(int modifyId, String title, String content) {
        this.articleRepository.modify(modifyId,title,content);
    }
    public Article articleFindById(int id) {
        return articleRepository.articleFindById(id);
    }
}
