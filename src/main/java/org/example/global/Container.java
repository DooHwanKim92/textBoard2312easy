package org.example.global;

import org.example.member.Member;

import java.time.LocalDate;
import java.util.Scanner;

public class Container {
    private static Scanner sc;
    private static Member loginedMember;
    public static void initScanner() {
        sc = new Scanner(System.in);
    }
    public static void closeScanner() {
        sc.close();
    }
    public static Scanner getSc() {
        return sc;
    }
    public static void setLoginedMember(Member member) {
        Container.loginedMember = member;
    }
    public static Member getLoginedMember() {
        return Container.loginedMember;
    }
    public static String nowDateTime() {
        return LocalDate.now().toString();
    }
}