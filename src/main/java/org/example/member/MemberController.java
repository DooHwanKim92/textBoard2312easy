package org.example.member;

import org.example.global.Container;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {
    Scanner sc;
    List<Member> memberList = new ArrayList<>();
    int userPrimaryId = 1;
    MemberService memberService;
    public MemberController() {}
    public void joinMembership() {
        String userId;
        String password;
        String checkPassword;
        String now = Container.nowDateTime();
        while (true) {
            System.out.print("(회원가입)ID 입력 : ");
            userId = Container.getSc().nextLine().trim();

            boolean isDuplicated = memberService.checkId(userId);

            if (isDuplicated) break;
        }
        while (true) {
            System.out.print("(회원가입)PW 입력 : ");
            password = Container.getSc().nextLine().trim();
            System.out.print("(회원가입)PW 확인 : ");
            checkPassword = Container.getSc().nextLine().trim();
            if (memberService.checkPassword(password,checkPassword)) {
                continue;
            }
            break;
        }
        Member member = new Member(userPrimaryId, userId, password, now);
        memberList.add(member);
        System.out.println("<알림> 회원가입 완료!!");
        userPrimaryId++;
    }

    public void logIn() {
        if (Container.getLoginedMember() != null) {
            System.out.println("<알림> 로그아웃을 먼저 해야합니다.");
            return;
        }
        Member checkedMember = null;

        System.out.print("(로그인)ID 입력 : ");
        String userId = Container.getSc().nextLine().trim();
        System.out.print("(로그인)PW 입력 : ");
        String password = Container.getSc().nextLine().trim();

        for (Member member : memberList) {
            if (userId.equals(member.getUserId())) {
                checkedMember = member;
                break;
            }
        }
        if (checkedMember == null) {
            System.out.println("<알림> 존재하지 않는 ID 입니다.");
            return;
        } else if (!password.equals(checkedMember.getPassword())) {
            System.out.println("<알림> 비밀번호가 일치하지 않습니다.");
            return;
        }

        Container.setLoginedMember(checkedMember);

        System.out.println("[" + checkedMember.getUserId() + "]님 환영합니다.");
    }

    public void logOut() {
        if (Container.getLoginedMember() != null) {
            System.out.println("[" + Container.getLoginedMember().getUserId() + "]님 로그아웃");
            Container.setLoginedMember(null);
        } else {
            System.out.println("<알림> 로그인을 먼저 해야합니다.");
        }
    }
}
