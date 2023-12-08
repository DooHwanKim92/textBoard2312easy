package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public void run() {
        System.out.println("===== 명언 앱 =====");
        Scanner sc = new Scanner(System.in);
        List<WiseSaying> wiseSayingList = new ArrayList<>();
        int id = 1;
        while (true) {
            System.out.print("명령 > ");
            String command = sc.nextLine().trim();
            if (command.equals("종료")) {
                System.out.println("===== 시스템 종료 =====");
                break;
            } else if (command.equals("등록")) {
                System.out.print("명언 : ");
                String content = sc.nextLine().trim();
                System.out.print("작가 : ");
                String author = sc.nextLine().trim();

                WiseSaying ws = new WiseSaying(id,content,author);

                wiseSayingList.add(ws);

                System.out.println(id + "번 명언 등록 완료!!");
                id++;
            } else if (command.equals("목록")) {
                System.out.println("  번호  /  작가  /  명언  ");
                System.out.println("------------------------");
                for(int i = 0; i<wiseSayingList.size();i++){
                    System.out.println(wiseSayingList.get(i).getId() + " / " + wiseSayingList.get(i).getAuthor() + " / " + wiseSayingList.get(i).getContent());
                }
            } else if (command.equals("삭제")) {
                System.out.print("삭제할 명언 번호 입력 > ");
                int removeId = Integer.parseInt(sc.nextLine().trim());
                for(int i = 0; i <wiseSayingList.size();i++) {
                    if(wiseSayingList.get(i).getId() == removeId)
                        wiseSayingList.remove(wiseSayingList.get(i));
                }
                System.out.println(removeId + "번 명언 삭제 완료!!");
            } else if (command.equals("수정")) {
                System.out.print("수정할 명언 번호 입력 > ");
                int modifyId = Integer.parseInt(sc.nextLine().trim());
                for (int i = 0; i<wiseSayingList.size();i++) {
                    if(wiseSayingList.get(i).getId() == modifyId) {
                        System.out.println("기존 명언 : "+wiseSayingList.get(i).getContent());
                        System.out.print("명언 수정 : ");
                        String modifyContent = sc.nextLine().trim();

                        System.out.println("기존 작가 : "+wiseSayingList.get(i).getAuthor());
                        System.out.print("작가 수정 : ");
                        String modifyAuthor = sc.nextLine().trim();

                        wiseSayingList.get(i).setContent(modifyContent);
                        wiseSayingList.get(i).setAuthor(modifyAuthor);

                        System.out.println(modifyId + "번 명언 수정 완료!!");
                    }
                }
            }
        }
    }
}
