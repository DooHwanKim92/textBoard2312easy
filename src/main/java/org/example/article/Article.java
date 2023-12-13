package org.example.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor

public class Article {
    private int id;
    private String title;
    private String content;
    int memberId;
    private String localDate;
    public Article(Map<String, Object> row) {
        this.id = (int)row.get("id");
        this.title = (String)row.get("title");
        this.content = (String)row.get("content");
        this.memberId = (int)row.get("memberId");
        this.localDate = row.get("localDate").toString();
    }
}
