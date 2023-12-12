package org.example;


import org.example.global.Container;

public class Main {
    public static void main(String[] args) {
        Container.initScanner();

        new App().run();

        Container.closeScanner();
    }
}