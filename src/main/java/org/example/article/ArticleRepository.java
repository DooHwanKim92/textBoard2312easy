package org.example.article;

import org.example.global.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleRepository {
    List<Article> articleList = new ArrayList<>();
    int articleId = 1;
    public int write(String title, String content) {
        String sql = String.format("insert into article set title = '%s', content = '%s', memberId = %d, regDate = now()",title,content,Container.getLoginedMember().getId());

        Container.getDBConnection().insert(sql);

        return articleId;
    }

    public int remove(Article article) {
        String sql = String.format("delete from article where id = %d",article.getId());

        Container.getDBConnection().insert(sql);

        return article.getId();
    }
    public int modify(Article article, String title, String content) {
        String sql = String.format("update article set title = '%s', content = '%s'",title,content);

        Container.getDBConnection().insert(sql);

        return article.getId();
    }
    public List<Article> findByAll() {
        List<Map<String, Object>> rows =  Container.getDBConnection().selectRows("select * from article");

        for (Map<String, Object> row : rows) {
            Article article = new Article(row);

            articleList.add(article);
        }

        return articleList;
    }
    public Article articleFindById(int id) {
        for (int i = 0; i < articleList.size(); i++) {
            if (articleList.get(i).getId() == id) {
                return articleList.get(i);
            }
        }
        return null;
    }
}
