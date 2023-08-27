package com.example.springmvc.model;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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

    public Message(UUID id, LocalDateTime created, LocalDateTime updated, String text, UUID chatId, UUID senderId) {
        super(id, created, updated);
        this.text = text;
        this.chatId = chatId;
        this.senderId = senderId;
    }

    public static Message map(ResultSet resultSet) throws SQLException {
        return new Message(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getTimestamp("created").toLocalDateTime(),
                resultSet.getTimestamp("updated").toLocalDateTime(),
                resultSet.getString("txt"),
                UUID.fromString(resultSet.getString("chatId")),
                UUID.fromString(resultSet.getString("senderId"))
        );
    }
}
