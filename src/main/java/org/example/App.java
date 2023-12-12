package org.example;

import lombok.RequiredArgsConstructor;
import org.example.article.Article;
import org.example.article.ArticleController;
import org.example.member.Member;
import org.example.member.MemberController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
@RequiredArgsConstructor
public class App {
    private final ArticleController articleController;
    private final MemberController memberController;
    SystemController systemController = new SystemController();
    App() {
        articleController = new ArticleController();
        memberController = new MemberController();
    }
    public void run() {
        System.out.println("========== 텍스트 게시판 ==========");


        while (true) {
            System.out.print("명령 > ");
            String command = Container.getSc().nextLine().trim();
            switch (command) {
                case "종료":
                    systemController.exit();
                    return;
                case "등록":
                    articleController.write();
                    break;
                case "목록":
                    articleController.list();
                    break;
                case "삭제":
                    articleController.remove();
                    break;
                case "수정":
                    articleController.modify();
                    break;
                case "회원가입":
                    memberController.joinMemberShip();
                    break;
                case "로그인":
                    memberController.logIn();
                    break;
                case "로그아웃":
                    memberController.logOut();
                    break;
            }
        }
    }
}