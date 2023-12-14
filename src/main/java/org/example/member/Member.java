package org.example.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Setter
@Getter
public class Member {
    private int id;
    private String userId;
    private String password;
    private String regDate;
    Member(Map<String, Object> row) {
        this.id = (int)row.get("id");
        this.userId = (String)row.get("userId");
        this.password = (String)row.get("password");
        this.regDate = row.get("regDate").toString();
    }
}