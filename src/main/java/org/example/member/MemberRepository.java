package org.example.member;

import org.example.article.Article;
import org.example.global.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    int memberId = 1;

    public MemberRepository() {

    }

    public void join(String userId, String password) {
        String sql = String.format("insert into member set userId = '%s', password = '%s', regDate = now()", userId, password);

        Container.getDBConnection().insert(sql);
    }

    public void exit(String userId) {
        String sql = String.format("delete from member where userId = '%s'", userId);

        Container.getDBConnection().delete(sql);
    }

    public List<Member> findByAll() {
        List<Member> memberList = new ArrayList<>();
        List<Map<String, Object>> rows = Container.getDBConnection().selectRows("select * from member");

        for (Map<String, Object> row : rows) {
            Member member = new Member(row);
            memberList.add(member);
        }
        return memberList;
    }

    public Member memberFindByUserId(String userId) {
        List<Member> memberList = this.findByAll();
        for (Member member : memberList) {
            if (userId.equals(member.getUserId())) {
                return member;
            }
        }
        return null;
    }

}
