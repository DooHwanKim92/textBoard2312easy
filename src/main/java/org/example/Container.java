package org.example;

import org.example.member.Member;

import java.util.Scanner;

public class Container {
    static Scanner sc;
    static Member loginedMember;
    static void initScanner() {
        sc = new Scanner(System.in);
    }
    public static Scanner getSc() {
        return sc;
    }
    public static void setLoginedMember(Member loginedMember) {
        Container.loginedMember = loginedMember;
    }
    public static Member getLoginedMember() {
        return Container.loginedMember;
    }
}
