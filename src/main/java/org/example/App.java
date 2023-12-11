package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public void run() {
        System.out.println("===== 명언 앱 =====");

        Scanner sc = new Scanner(System.in);
        List<WiseSaying> wiseSayingList = new ArrayList<>();
        List<Member> memberList = new ArrayList<>();
        int id = 1;
        int userPrimaryId = 1;

        while (true) {
            System.out.print("명령 > ");
            String command = sc.nextLine().trim();
            if (command.equals("종료")) {
                System.out.println("===== 시스템 종료 =====");
                break;
            } else if (command.equals("등록")) {
                System.out.print("명언(등록) : ");
                String content = sc.nextLine().trim();
                System.out.print("작가(등록) : ");
                String author = sc.nextLine().trim();

                WiseSaying ws = new WiseSaying(id, content, author);
                wiseSayingList.add(ws);

                System.out.println(id + "번 명언 등록 완료!!");
                id++;
            } else if (command.equals("목록")) {
                System.out.println("  번호  /  작가  /  명언  ");
                System.out.println("------------------------");
                for (WiseSaying ws : wiseSayingList) {
                    System.out.println(ws.getId() + " / " + ws.getAuthor() + " / " + ws.getContent());
                }
            } else if (command.equals("삭제")) {
                System.out.print("삭제할 명언 번호 입력 > ");
                int removeId = Integer.parseInt(sc.nextLine().trim());
                for (int i = 0; i < wiseSayingList.size(); i++) {
                    if (wiseSayingList.get(i).getId() == removeId)
                        wiseSayingList.remove(wiseSayingList.get(i));
                }
                System.out.println(removeId + "번 명언 삭제 완료!!");
            } else if (command.equals("수정")) {
                System.out.print("수정할 명언 번호 입력 > ");
                int modifyId = Integer.parseInt(sc.nextLine().trim());

                for (int i = 0; i < wiseSayingList.size(); i++) {
                    if (wiseSayingList.get(i).getId() == modifyId) {
                        System.out.println("기존 명언 : " + wiseSayingList.get(i).getContent());
                        System.out.print("명언(수정) : ");
                        String modifyContent = sc.nextLine().trim();

                        System.out.println("기존 작가 : " + wiseSayingList.get(i).getAuthor());
                        System.out.print("작가(수정) : ");
                        String modifyAuthor = sc.nextLine().trim();

                        wiseSayingList.get(i).setContent(modifyContent);
                        wiseSayingList.get(i).setAuthor(modifyAuthor);
                    }
                }
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
            } else if (command.equals("회원목록")) {
                System.out.println("회원번호  /  회원ID  /  회원PW  /  가입날짜");
                for (int i = 0; i < memberList.size(); i++) {
                    System.out.println(memberList.get(i).getId() + " / " + memberList.get(i).getUserId() + " / " + memberList.get(i).getPassword() + " / " + memberList.get(i).getRegDate());
                }
            }
        }
    }
}
