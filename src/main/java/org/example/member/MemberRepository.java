package org.example.member;

import org.example.global.Container;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    int userPrimaryId = 1;
    String now = Container.nowDateTime();
    List<Member> memberList = new ArrayList<>();
    public void joinMembership(String userId, String password) {
        Member member = new Member(userPrimaryId, userId, password, this.now);
        memberList.add(member);

        userPrimaryId++;
    }
    public Member logIn(String userId, String password) {
        Member checkedMember = null;

        for (Member member : memberList) {
            if (userId.equals(member.getUserId())) {
                checkedMember = member;
                return checkedMember;
            }
        }
        return checkedMember;
    }
    public void logOut() {}
    public boolean checkId(String userId) {
        boolean isDuplicated = true;
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getUserId().equals(userId)) {
                System.out.println("<알림> 중복 아이디가 존재합니다.");
                isDuplicated = false;
                return isDuplicated;
            }
        } return isDuplicated;
    }
    public boolean checkPassword(String password, String checkPassword) {
        boolean isPassword = true;
        if (!password.equals(checkPassword)) {
            System.out.println("<알림> 비밀번호를 잘못입력했습니다.");
            isPassword = false;
            return isPassword;
        }
        return isPassword;
    }
    public void memberFindById() {

    }
}
