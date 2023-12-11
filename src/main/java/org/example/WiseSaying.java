package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor

public class WiseSaying {
    private int id;
    private String content;
    private String author;
    private String userId;
    private LocalDate localDate;
}
