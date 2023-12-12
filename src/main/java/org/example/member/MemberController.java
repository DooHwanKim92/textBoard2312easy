package org.example.member;

import lombok.RequiredArgsConstructor;
import org.example.Container;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MemberController {
    Scanner sc;
    List<Member> memberList = new ArrayList<>();
    int userPrimaryId = 1;
    public MemberController() {}
    public void joinMemberShip() {
        String userId;
        String password;
        String checkPassword;
        LocalDate now = LocalDate.now();
        while (true) {
            System.out.print("(회원가입)ID 입력 : ");
            userId = Container.getSc().nextLine().trim();
            boolean isDuplicated = true;
            for (int i = 0; i < memberList.size(); i++) {
                if (memberList.get(i).getUserId().equals(userId)) {
                    System.out.println("중복 아이디가 존재합니다.");
                    isDuplicated = false;
                }
            }
            if (isDuplicated) break;
        }
        while (true) {
            System.out.print("(회원가입)PW 입력 : ");
            password = Container.getSc().nextLine().trim();
            System.out.print("(회원가입)PW 확인 : ");
            checkPassword = Container.getSc().nextLine().trim();
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

    public void logIn() {
        if (Container.getLoginedMember() != null) {
            System.out.println("로그아웃을 먼저 해야합니다.");
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
            System.out.println("존재하지 않는 ID 입니다.");
            return;
        } else if (!password.equals(checkedMember.getPassword())) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }

        Container.setLoginedMember(checkedMember);

        System.out.println("[" + checkedMember.getUserId() + "] 님 환영합니다.");
    }

    public void logOut() {
        if (Container.getLoginedMember() != null) {
            System.out.println("[" + Container.getLoginedMember().getUserId() + "] 님 로그아웃");
            Container.setLoginedMember(null);
        } else {
            System.out.println("로그인을 먼저 해야합니다.");
        }
    }
}
