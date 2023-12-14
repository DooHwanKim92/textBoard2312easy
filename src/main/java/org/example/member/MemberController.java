package org.example.member;

import org.example.global.Container;
import org.example.member.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {
    MemberService memberService;

    public MemberController() {
        memberService = new MemberService();
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

        Member member = this.memberService.memberFindByUserId(userId);
        checkedMember = member;

        if (checkedMember == null) {
            System.out.println("<알림> 존재하지 않는 ID 입니다.");
            return;
        } else if (!password.equals(checkedMember.getPassword())) {
            System.out.println("<알림> 비밀번호가 일치하지 않습니다.");
            return;
        }
        this.memberService.login(checkedMember);
        Container.setLoginedMember(checkedMember);

        System.out.println("[" + checkedMember.getUserId() + "]님 환영합니다.");
    }

    public void logOut() {
        if (Container.getLoginedMember() == null) {
            System.out.println("<알림> 로그인을 먼저 해야합니다.");
            return;
        }

        System.out.println("<알림> 로그아웃 되었습니다.");

        this.memberService.logout();
    }

    public void joinMembership() {
        String userId;
        String password;
        String checkPassword;
        while (true) {
            System.out.print("(회원가입)ID 입력 : ");
            userId = Container.getSc().nextLine().trim();
            boolean isDuplicated = true;

            Member member = this.memberService.memberFindByUserId(userId);

            if (member != null) {
                System.out.println("<알림> 중복 아이디가 존재합니다.");
                isDuplicated = false;
            }

            // 중복 아이디가 없는 경우
            if (isDuplicated) break;
        }
        while (true) {
            System.out.print("(회원가입)PW 입력 : ");
            password = Container.getSc().nextLine().trim();

            System.out.print("(회원가입)PW 확인 : ");
            checkPassword = Container.getSc().nextLine().trim();

            if (password.equals(checkPassword)) {
                break;
            }
            System.out.println("<알림> 비밀번호를 잘못입력했습니다.");
        }

        this.memberService.join(userId, password);

        System.out.println("["+userId + "]님 회원가입 완료!!");
    }
}
