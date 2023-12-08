package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Member {
    int id;
    String passWord;
    String memberId;
    String nickName;
}
