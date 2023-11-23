package com.ryan9025.ch01.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    private int age;
    private String name;
    private String tel;
    private String address;
}
