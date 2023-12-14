package org.example;

import org.example.article.ArticleController;
import org.example.db.DBConnection;
import org.example.global.Container;
import org.example.member.MemberController;


public class App {
    ArticleController articleController;
    MemberController memberController;
    SystemController systemController;
    public App() {
        DBConnection.DB_NAME = "proj1";
        DBConnection.DB_PORT = 3306;
        DBConnection.DB_USER = "root";
        DBConnection.DB_PASSWORD = "";

        Container.getDBConnection().connect();

        articleController = new ArticleController();
        memberController = new MemberController();
        systemController = new SystemController();
    }
    public void run() {
        System.out.println("┌==================== 텍스트 게시판 ====================┐");

        while (true) {
            System.out.print("명령어 입력 ▶ ");
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
                    memberController.joinMembership();
                    break;
                case "로그인":
                    memberController.logIn();
                    break;
                case "로그아웃":
                    memberController.logOut();
                    break;
                case "회원탈퇴":
                    memberController.exitMembership();
                    break;
            }
        }
    }
}


// 자 오늘 할 일은 무어냐 하면
// 어제 DB 연동하던거 이어서 하고
// Member도 DB 연동해서 DB 저장시키고,
// 아 목록 출력할 때, memberId를 작성자 이름으로 나오게 하는 것도
// ㅇㅋ