package com.example.springmvc.model;

import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Message extends BaseModel {

    private String text;
    private UUID chatId;
    private UUID senderId;
}
