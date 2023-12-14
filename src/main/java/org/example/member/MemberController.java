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
        if (Container.getLoginedMember() != null) {
            System.out.println("<알림> 로그아웃을 먼저 해야합니다.");
            return;
        }

        String userId;
        String password;
        String checkPassword;
        LocalDate now = LocalDate.now();

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

        System.out.println("[" + userId + "]님 회원가입 완료!!");
    }

    public void exitMembership() {
        String password;
        String checkPassword;
        // 1. 로그인 상태여야함 > 로그인 상태인지 검증
        if (Container.getLoginedMember() == null) {
            System.out.println("<알림> 로그인을 먼저 해야합니다.");
            return;
        }
        // 2. 그리고,,,,음 받는 정보가 많이 없으니 그냥 password 검증만 해야 할 듯
        System.out.print("(회원탈퇴)PW 입력 : ");
        password = Container.getSc().nextLine().trim();
        System.out.print("(회원탈퇴)PW 확인 : ");
        checkPassword = Container.getSc().nextLine().trim();

        if (!password.equals(checkPassword)) {
            System.out.println("<알림> 비밀번호를 잘못입력했습니다.");
            return;
        }

        if (password.equals(Container.getLoginedMember().getPassword())) {
            System.out.print("<알림> 정말 탈퇴하시겠습니까? (y/n)입력 ▶ ");
            String yesOrNo = Container.getSc().nextLine().trim();
            if (yesOrNo.equals("y") || yesOrNo.equals("Y")) {

                memberService.exit(Container.getLoginedMember().getUserId());

                System.out.println("☞===== 회원 탈퇴가 정상처리 되었습니다 =====☜");
                System.out.println("☞===== 그동안 이용해주셔서 감사합니다 =====☜");

                System.out.println("<알림> 로그아웃 되었습니다.");

                this.memberService.logout();

            } else if (yesOrNo.equals("n") || yesOrNo.equals("N")) {
                System.out.println("<알림> 회원 탈퇴를 취소하셨습니다.");
            } else {
                System.out.println("<알림> y/Y 또는 n/N을 입력해주셔야 합니다.");
            }
        } else {
            System.out.println("<알림> 비밀번호를 잘못 입력했습니다.");
        }
    }
}
