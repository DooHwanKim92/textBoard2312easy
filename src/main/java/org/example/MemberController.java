package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {
    Scanner sc;
    int userPrimaryId = 1;
    List<Member> memberList = new ArrayList<>();
    Member loginedMember = null;
    public void logIn() {
        sc = new Scanner(System.in);
        if (loginedMember != null) {
            System.out.println("로그아웃을 먼저 해야합니다.");
            return;
        }
        Member checkedMember = null;

        System.out.print("(로그인)ID 입력 : ");
        String userId = sc.nextLine().trim();
        System.out.print("(로그인)PW 입력 : ");
        String password = sc.nextLine().trim();

        for (Member member : memberList) {
            if (userId.equals(member.userId)) {
                checkedMember = member;
                break;
            }
        }
        if (checkedMember == null) {
            System.out.println("존재하지 않는 ID 입니다.");
            return;
        } else if (!password.equals(checkedMember.password)) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }

        loginedMember = checkedMember;

        System.out.println("[" + checkedMember.getUserId() + "]님 환영합니다.");
    }
    public void logOut() {
        if (loginedMember != null) {
            System.out.println("[" + loginedMember.getUserId() + "]님 로그아웃");
            loginedMember = null;
        } else {
            System.out.println("로그인을 먼저 해야합니다.");
        }
    }
    public void memberShip() {
        String userId;
        String password;
        String checkPassword;
        sc = new Scanner(System.in);
        LocalDate now = LocalDate.now();
        while (true) {
            System.out.print("(회원가입)ID 입력 : ");
            userId = sc.nextLine().trim();
            boolean isDuplicated = true;
            for (int i = 0; i < memberList.size(); i++) {
                if (memberList.get(i).getUserId().equals(userId)) {
                    System.out.println("중복 아이디가 존재합니다.");
                    isDuplicated = false;
                }
            }
            // 중복 아이디가 없는 경우
            if (isDuplicated) break;
        }
        while (true) {
            System.out.print("(회원가입)PW 입력 : ");
            password = sc.nextLine().trim();
            System.out.print("(회원가입)PW 확인 : ");
            checkPassword = sc.nextLine().trim();
            if (!password.equals(checkPassword)) {
                System.out.println("비밀번호를 잘못입력했습니다.");
                continue;
            }
            break;
        }

        Member member = new Member(userPrimaryId, userId, password, now.toString());
        memberList.add(member);
        System.out.println("회원가입 완료!!");
        userPrimaryId++;
    }
}
