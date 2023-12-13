package org.example;


import org.example.global.Container;

public class Main {
    public static void main(String[] args) {

        Container.initScanner();

        new App().run();

    }
}

// 자 오늘 할 일은 무엇이냐....

// 리팩토링 이어서...

// 일단 Article 이랑 Member package 따로 나누어서 리팩토링 하고
// Global package 만들어서 Container class 생성
// 전역적으로 사용하면 Scanner라던가 이런거 집어 넣기,