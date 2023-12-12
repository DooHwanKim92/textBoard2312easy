package org.example.article;

import lombok.RequiredArgsConstructor;
import org.example.Container;
import org.example.member.Member;

import java.time.LocalDate;
import java.util.*;

public class ArticleController {
    int id = 1;
    List<Article> articleList = new ArrayList<>();
    LocalDate localDate = LocalDate.now();
    Member loginedMember;
    public ArticleController() {}
    public void write() {
        loginedMember = Container.getLoginedMember();
        boolean isLogin = _checkedLogin(loginedMember);
        if (!isLogin) return;

        System.out.print("제목(등록) : ");
        String title = Container.getSc().nextLine().trim();
        System.out.print("내용(등록) : ");
        String content = Container.getSc().nextLine().trim();
        String userId = loginedMember.getUserId();

        Article ws = new Article(id, title, content, userId, localDate);
        articleList.add(ws);

        System.out.println(id + "번 게시글 등록 완료!!");
        id++;
    }
    public void list() {
        loginedMember = Container.getLoginedMember();
        boolean isLogin = _checkedLogin(loginedMember);
        if (!isLogin) return;

        System.out.println("  번호  /  제목  /  내용  /  작성자  /  작성일자  ");
        System.out.println("----------------------------------------------");
        for (Article ws : articleList) {
            System.out.println(ws.getId() + " / " + ws.getTitle() + " / " + ws.getContent() + " / " + ws.getUserId() + " / " + ws.getLocalDate());
        }
    }
    public void remove() {
        loginedMember = Container.getLoginedMember();
        boolean isLogin = _checkedLogin(loginedMember);
        if (!isLogin) return;

        System.out.print("삭제할 게시글 번호 입력 > ");
        int removeId = Integer.parseInt(Container.getSc().nextLine().trim());

        Article article = _articleFindById(removeId);

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
        loginedMember = Container.getLoginedMember();
        boolean isLogin = _checkedLogin(loginedMember);
        if (!isLogin) return;

        System.out.print("수정할 게시글 번호 입력 > ");
        int modifyId = Integer.parseInt(Container.getSc().nextLine().trim());

        Article article = _articleFindById(modifyId);

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
        String modifyTitle = Container.getSc().nextLine().trim();

        System.out.println("기존 내용 : " + article.getContent());
        System.out.print("내용(수정) : ");
        String modifyContent = Container.getSc().nextLine().trim();

        article.setTitle(modifyTitle);
        article.setContent(modifyContent);

        System.out.println(modifyId + "번 게시글 수정 완료!!");
    }
    private Article _articleFindById(int id) {
        for (int i =0; i<articleList.size();i++) {
            if (id == articleList.get(i).getId()) {
                return articleList.get(i);
            }
        }
        return null;
    }
    private boolean _checkedLogin(Member loginedMember) {
        if (loginedMember == null) {
            System.out.println("로그인이 필요합니다.");
            return false;
        }
        return true;
    }
}
