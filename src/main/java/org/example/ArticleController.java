package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ArticleController {
    Scanner sc;
    int id = 1;
    LocalDate localDate = LocalDate.now();
    List<Article> articleList = new ArrayList<>();
    Member loginedMember = null;
    // 로그인을 했을 때 발생하는 Member의 정보를
    // ArticleController에도 넘겨줘야 한다.
    public void write() {
        sc = new Scanner(System.in);
        if (loginedMember == null) {
            System.out.println("로그인이 필요합니다.");
            return;
        }
        System.out.print("제목(등록) : ");
        String title = sc.nextLine().trim();
        System.out.print("내용(등록) : ");
        String content = sc.nextLine().trim();
        String userId = loginedMember.userId;

        Article ws = new Article(id, title, content, userId, localDate);
        articleList.add(ws);

        System.out.println(id + "번 명언 등록 완료!!");
        id++;
    }
    public void list() {
        if (loginedMember == null) {
            System.out.println("로그인이 필요합니다.");
            return;
        }
        System.out.println("  번호  /  제목  /  내용  /  작성자  /  작성일자  ");
        System.out.println("----------------------------------------------");
        for (Article ws : articleList) {
            System.out.println(ws.getId() + " / " + ws.getTitle() + " / " + ws.getContent() + " / " + ws.getUserId() + " / " + ws.getLocalDate());
        }
    }
    public void remove() {
        if (loginedMember == null) {
            System.out.println("로그인이 필요합니다.");
            return;
        }
        System.out.print("삭제할 게시글 번호 입력 > ");
        int removeId = Integer.parseInt(sc.nextLine().trim());

        Article article = null;
        for (int i = 0; i < articleList.size(); i++) {
            if (articleList.get(i).getId() == removeId) {
                article = articleList.get(i);
            }
        }
        if (article == null) {
            System.out.println("해당 게시글은 존재하지 않습니다.");
            return;
        }
        if (!Objects.equals(article.getUserId(), loginedMember.getUserId())) {
            System.out.println("다른 사람이 작성한 게시글은 삭제할 수 없습니다.");
            return;
        }
        articleList.remove(article);
        System.out.println(removeId + "번 게시글 삭제 완료!!");
    }
    public void modify() {
        if (loginedMember == null) {
            System.out.println("로그인이 필요합니다.");
            return;
        }
        System.out.print("수정할 게시글 번호 입력 > ");
        int modifyId = Integer.parseInt(sc.nextLine().trim());

        Article article = null;
        for (int i = 0; i < articleList.size(); i++) {
            if (articleList.get(i).getId() == modifyId) {
                article = articleList.get(i);
            }
        }
        if (article == null) {
            System.out.println("해당 게시글은 존재하지 않습니다.");
            return;
        }
        if (!Objects.equals(article.getUserId(), loginedMember.getUserId())) {
            System.out.println("다른 사람이 작성한 게시글은 수정할 수 없습니다.");
            return;
        }
        System.out.println("기존 제목 : " + article.getTitle());
        System.out.print("제목(수정) : ");
        String modifyTitle = sc.nextLine().trim();

        System.out.println("기존 내용 : " + article.getContent());
        System.out.print("내용(수정) : ");
        String modifyContent = sc.nextLine().trim();

        article.setTitle(modifyTitle);
        article.setContent(modifyContent);

        System.out.println(modifyId + "번 게시글 수정 완료!!");
    }
}
