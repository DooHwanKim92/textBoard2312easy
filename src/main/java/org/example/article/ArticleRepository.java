package org.example.article;

import org.example.global.Container;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    int id = 1;
    List<Article> articleList = new ArrayList<>();
    String localDate = Container.nowDateTime();
    public void write(String title, String content, String userId) {
        Article article = new Article(id, title, content, userId, localDate);
        articleList.add(article);
        System.out.println("<알림> " + id + "번 게시글 등록 완료!!");
        id ++;
    }
    public List<Article> list() {
        return this.articleList;
    }
    public void remove(int removeId) {
        Article article = articleFindById(removeId);

        articleList.remove(article);
    }
    public void modify(int modifyId, String title, String content) {
        Article article = articleFindById(modifyId);

        article.setTitle(title);
        article.setContent(content);
    }
    public Article articleFindById(int id) {
        for (int i = 0; i < articleList.size(); i++) {
            if (id == articleList.get(i).getId()) {
                return articleList.get(i);
            }
        }
        return null;
    }
}
