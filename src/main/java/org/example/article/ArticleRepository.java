package org.example.article;

import org.example.global.Container;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleRepository {
    int articleId = 1;
    List<Article> articleList = new ArrayList<>();
    String localDate = Container.nowDateTime();
    public ArticleRepository() {

    }
    public int write(String title, String content) {

        String sql = String.format("insert into article set title = '%s', content = '%s', memberId = %d, localDate = now()",title,content,Container.getLoginedMember().getId());

        Container.getDBConnection().insert(sql);

        return articleId;
    }
    public void remove(int removeId) {
        Article article = articleFindById(removeId);

        String sql = String.format("DELETE FROM article WHERE id = %d",removeId);

        Container.getDBConnection().insert(sql);
    }
    public void modify(int modifyId, String title, String content) {
        Article article = articleFindById(modifyId);

        String sql = String.format("DELETE FROM article WHERE id = %d",modifyId);

        article.setTitle(title);
        article.setContent(content);
    }
    public List<Article> findByAll() {
        List<Map<String, Object>> rows = Container.getDBConnection().selectRows("select * from article");
        for (Map<String ,Object> row :rows) {
            Article article = new Article(row);

            articleList.add(article);
        }

        return articleList;
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
