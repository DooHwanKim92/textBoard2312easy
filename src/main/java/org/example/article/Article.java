package org.example.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.member.MemberRepository;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter

public class Article {
    private int id;
    private String title;
    private String content;
    private int memberId;
    private String memberUserId;
    private String regDate;

    Article(Map<String, Object> row) {
        this.id = (int)row.get("id");
        this.title = (String)row.get("title");
        this.content = (String)row.get("content");
        this.memberUserId = (String)row.get("userId");
        this.regDate = row.get("regDate").toString();
    }
}
