package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class App {
    public void run() {
        System.out.println("========== 명언 앱 ==========");
        Scanner sc = new Scanner(System.in);
        SystemController systemController = new SystemController();
        ArticleController articleController = new ArticleController();
        MemberController memberController = new MemberController();

        while (true) {
            System.out.print("명령 > ");
            String command = sc.nextLine().trim();
            if (command.equals("종료")) {
                systemController.terminate();
                break;
            } else if (command.equals("등록")) {
                articleController.write();
            } else if (command.equals("목록")) {
                articleController.list();
            } else if (command.equals("삭제")) {
                articleController.remove();
            } else if (command.equals("수정")) {
                articleController.modify();
            } else if (command.equals("회원가입")) {
                memberController.memberShip();
            } else if (command.equals("로그인")) {
                memberController.logIn();
            } else if (command.equals("로그아웃")) {
                memberController.logOut();
            }
        }
    }
}