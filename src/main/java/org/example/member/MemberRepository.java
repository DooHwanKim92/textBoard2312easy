package org.example.member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    List<Member> memberList = new ArrayList<>();
    public void joinMembership() {

    }
    public void logIn() {}
    public void logOut() {}
    public boolean checkId(String userId) {
        boolean isDuplicated = true;
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getUserId().equals(userId)) {
                System.out.println("<알림> 중복 아이디가 존재합니다.");
                isDuplicated = false;
                break;
            }
        } return isDuplicated;
    }
    public boolean checkPassword(String password, String checkPassword) {
        boolean isPassword = true;
        if (!password.equals(checkPassword)) {
            System.out.println("<알림> 비밀번호를 잘못입력했습니다.");
            isPassword = false;
        }
        return isPassword;
    }
    public void memberFindById() {

    }
}
