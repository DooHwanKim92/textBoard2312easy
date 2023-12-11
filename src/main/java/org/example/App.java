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

        List<WiseSaying> wiseSayingList = new ArrayList<>();
        int id = 1;
        List<Member> memberList = new ArrayList<>();
        int userPrimaryId = 1;

        Member loginedMember = null;

        LocalDate localDate = LocalDate.now();

        while (true) {
            System.out.print("명령 > ");
            String command = sc.nextLine().trim();
            if (command.equals("종료")) {
                System.out.println("========== 시스템 종료 ==========");
                break;
            } else if (command.equals("등록")) {
                if (loginedMember == null) {
                    System.out.println("로그인이 필요합니다.");
                    continue;
                }
                System.out.print("명언(등록) : ");
                String content = sc.nextLine().trim();
                System.out.print("작가(등록) : ");
                String author = sc.nextLine().trim();
                String userId = loginedMember.userId;

                WiseSaying ws = new WiseSaying(id, content, author, userId, localDate);
                wiseSayingList.add(ws);

                System.out.println(id + "번 명언 등록 완료!!");
                id++;
            } else if (command.equals("목록")) {
                if (loginedMember == null) {
                    System.out.println("로그인이 필요합니다.");
                    continue;
                }
                System.out.println("  번호  /  작가  /  명언  /  작성자  /  작성일자  ");
                System.out.println("----------------------------------------------");
                for (WiseSaying ws : wiseSayingList) {
                    System.out.println(ws.getId() + " / " + ws.getAuthor() + " / " + ws.getContent() + " / " + ws.getUserId() + " / " + ws.getLocalDate());
                }
            } else if (command.equals("삭제")) {
                if (loginedMember == null) {
                    System.out.println("로그인이 필요합니다.");
                    continue;
                }
                System.out.print("삭제할 명언 번호 입력 > ");
                int removeId = Integer.parseInt(sc.nextLine().trim());
                try {
                    if (wiseSayingList.get(removeId) == null) continue;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("해당 게시글은 존재하지 않습니다.");
                }

                for (int i = 0; i < wiseSayingList.size(); i++) {
                    if (wiseSayingList.get(i).getId() == removeId)
                        if (wiseSayingList.get(i).getUserId().equals(loginedMember.getUserId())) {
                            wiseSayingList.remove(wiseSayingList.get(i));
                            System.out.println(removeId + "번 명언 삭제 완료!!");
                        } else {
                            System.out.println("다른 사람이 작성한 게시물은 삭제할 수 없습니다.");
                        }
                }
            } else if (command.equals("수정")) {
                if (loginedMember == null) {
                    System.out.println("로그인이 필요합니다.");
                    continue;
                }
                System.out.print("수정할 명언 번호 입력 > ");
                int modifyId = Integer.parseInt(sc.nextLine().trim());

                WiseSaying wiseSaying = null;
                for (int i = 0; i < wiseSayingList.size(); i++) {
                    if (wiseSayingList.get(i).getId() == modifyId) {
                        wiseSaying = wiseSayingList.get(i);
                    }
                }
                if (wiseSaying == null) {
                    System.out.println("해당 게시글은 존재하지 않습니다.");
                    continue;
                }
                if (!Objects.equals(wiseSaying.getUserId(), loginedMember.getUserId())) {
                    System.out.println("다른 사람이 작성한 게시물은 수정할 수 없습니다.");
                    continue;
                }
                System.out.println("기존 명언 : " + wiseSaying.getContent());
                System.out.print("명언(수정) : ");
                String modifyContent = sc.nextLine().trim();

                System.out.println("기존 작가 : " + wiseSaying.getAuthor());
                System.out.print("작가(수정) : ");
                String modifyAuthor = sc.nextLine().trim();

                wiseSaying.setContent(modifyContent);
                wiseSaying.setAuthor(modifyAuthor);

                System.out.println(modifyId + "번 명언 수정 완료!!");
            } else if (command.equals("회원가입")) {
                // 중복 아이디 검증
                String userId;
                String password;
                String checkPassword;
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
                // 비밀번호 확인 검증
                // 1. 비번 입력
                // 2. 비밀번호 확인
                // 3. 비번 != 비번확인 → 틀렸어 다시 입력해
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
            } else if (command.equals("로그인")) {
                if (loginedMember != null) {
                    System.out.println("로그아웃을 먼저 해야합니다.");
                    continue;
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
                    continue;
                } else if (!password.equals(checkedMember.password)) {
                    System.out.println("비밀번호가 일치하지 않습니다.");
                    continue;
                }

                loginedMember = checkedMember;

                System.out.println("[" + checkedMember.getUserId() + "]님 환영합니다.");
            } else if (command.equals("로그아웃")) {
                if (loginedMember != null) {
                    System.out.println("[" + loginedMember.getUserId() + "]님 로그아웃");
                    loginedMember = null;
                } else {
                    System.out.println("로그인을 먼저 해야합니다.");
                }
            }
        }
    }
}