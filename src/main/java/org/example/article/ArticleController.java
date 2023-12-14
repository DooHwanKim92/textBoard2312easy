package org.example.article;

import org.example.global.Container;
import org.example.member.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ArticleController {
    ArticleService articleService;
    public ArticleController() {
        articleService = new ArticleService();
    }
    public void write() {
        if (Container.getLoginedMember() == null) {
            System.out.println("<알림> 로그인이 필요합니다.");
            return;
        }
        System.out.print("제목(등록) : ");
        String title = Container.getSc().nextLine().trim();
        System.out.print("내용(등록) : ");
        String content = Container.getSc().nextLine().trim();

        int writeId = articleService.write(title,content);

        System.out.println(writeId + "번 게시글 등록 완료!!");

    }
    public void list() {
        if (Container.getLoginedMember() == null) {
            System.out.println("<알림> 로그인이 필요합니다.");
            return;
        }

        List<Article> articleList = this.articleService.findByAllIntoMemberId();
        System.out.println("【  번호  /  제목  /  내용  /  작성자  /  작성일자  】");
        System.out.println(" ---------------------------------------------- ");
        for (Article article : articleList) {
            System.out.println("【 "+article.getId() + " / " + article.getTitle() + " / " + article.getContent() + " / " + article.getMemberUserId() + " / " + article.getRegDate()+" 】");
        }
    }
    public void remove() {
        if (Container.getLoginedMember() == null) {
            System.out.println("<알림> 로그인이 필요합니다.");
            return;
        }
        System.out.print("삭제할 게시글 번호 입력 ▶ ");
        int removeId = Integer.parseInt(Container.getSc().nextLine().trim());

        Article article = this.articleService.articleFindById(removeId);

        if (article == null) {
            System.out.println("<알림> 해당 게시글은 존재하지 않습니다.");
            return;
        }
        if (article.getMemberId() != Container.getLoginedMember().getId()) {
            System.out.println("<알림> 다른 사람이 작성한 게시글은 삭제할 수 없습니다.");
            return;
        }

        this.articleService.remove(article);

        System.out.println(removeId + "번 게시글 삭제 완료!!");
    }
    public void modify() {
        if (Container.getLoginedMember() == null) {
            System.out.println("<알림> 로그인이 필요합니다.");
            return;
        }
        System.out.print("수정할 게시글 번호 입력 ▶ ");
        int modifyId = Integer.parseInt(Container.getSc().nextLine().trim());

        Article article = this.articleService.articleFindById(modifyId);

        if (article == null) {
            System.out.println("<알림> 해당 게시글은 존재하지 않습니다.");
            return;
        }
        if (article.getMemberId() != Container.getLoginedMember().getId()) {
            System.out.println("<알림> 다른 사람이 작성한 게시글은 수정할 수 없습니다.");
            return;
        }

        System.out.println("기존 제목 : " + article.getTitle());
        System.out.print("제목(수정) : ");
        String modifyTitle = Container.getSc().nextLine().trim();

        System.out.println("기존 내용 : " + article.getContent());
        System.out.print("내용(수정) : ");
        String modifyContent = Container.getSc().nextLine().trim();

        this.articleService.modify(article,modifyTitle,modifyContent);

        System.out.println(modifyId + "번 게시글 수정 완료!!");
    }

}