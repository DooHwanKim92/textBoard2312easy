package org.example;

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
                int userId = 1;
                System.out.println("=== 회원가입을 환영합니다 ===");
                System.out.println("   회원 정보를 입력해주세요   ");
                System.out.print("ID 입력 > ");
                String memberId = sc.nextLine().trim();

                String passWord;
                while (true) {
                    System.out.print("PW 입력 > ");
                    passWord = sc.nextLine().trim();

                    System.out.print("PW 입력 확인 > ");
                    String passWordCheck = sc.nextLine().trim();
                    if (passWord.equals(passWordCheck)) {
                        break;
                    }
                    System.out.println("비밀번호를 다시 한 번 입력해주세요.");
                }
                System.out.print("닉네임 입력 > ");
                String nickName = sc.nextLine().trim();

                Member memberInfo = new Member(userId, passWord, memberId, nickName);

                memberList.add(memberInfo);

                System.out.println("회원가입에 성공했습니다!!");
                userId++;
            } else if (command.equals("로그인")) {
                int identifyNumber = 0;
                System.out.print("ID 입력 > ");
                String logInId = sc.nextLine().trim();
                for(int i = 0; i<memberList.size();i++) {
                    if(!logInId.equals(memberList.get(i).getMemberId())) {
                        System.out.println("존재하지 않는 ID 입니다.");
                        break;
                    } else {
                        identifyNumber = i;
                    }
                }
                System.out.print("PW 입력 > ");
                String logInPassword = sc.nextLine().trim();
                if(logInPassword.equals(memberList.get(identifyNumber).getPassWord())) {
                    System.out.println("로그인 성공!!");
                    System.out.println("\"" + memberList.get(identifyNumber).getNickName() + "\" 님 환영합니다.");
                } else {
                    System.out.println("비밀번호가 맞지 않습니다.");
                }
            }
        }
    }
}
