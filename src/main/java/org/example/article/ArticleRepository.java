package org.example.article;

import org.example.global.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleRepository {
    int articleId = 1;

    public int write(String title, String content) {
        String sql = String.format("insert into article set title = '%s', content = '%s', memberId = %d, regDate = now()", title, content, Container.getLoginedMember().getId());

        Container.getDBConnection().insert(sql);

        return articleId;
    }

    public void remove(Article article) {
        String sql = String.format("delete from article where id = %d", article.getId());

        Container.getDBConnection().delete(sql);
    }

    public void modify(Article article, String title, String content) {
        String sql = String.format("update article set title = '%s', content = '%s' where id = %d", title, content, article.getId());

        Container.getDBConnection().update(sql);
    }

    public List<Article> findByAll() {
        List<Article> articleList = new ArrayList<>();
        List<Map<String, Object>> rows = Container.getDBConnection().selectRows("SELECT\n" +
                "article.id,\n" +
                "article.title,\n" +
                "article.content,\n" +
                "`member`.userId,\n" +
                "article.regDate\n" +
                "FROM article\n" +
                "INNER JOIN `member`\n" +
                "ON article.memberId = `member`.id;");

        for (Map<String, Object> row : rows) {
            Article article = new Article(row);

            articleList.add(article);
        }

        return articleList;
    }

    public Article articleFindById(int id) {
        List<Article> articleList = this.findByAll();
        for (int i = 0; i < articleList.size(); i++) {
            if (articleList.get(i).getId() == id) {
                return articleList.get(i);
            }
        }
        return null;
    }
}
