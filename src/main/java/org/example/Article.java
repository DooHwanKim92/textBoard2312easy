package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor

public class Article {
    private int id;
    private String title;
    private String content;
    private String userId;
    private LocalDate localDate;
}