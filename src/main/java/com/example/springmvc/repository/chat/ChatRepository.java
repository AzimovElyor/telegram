package com.example.springmvc.repository.chat;

import com.example.springmvc.model.Chat;
import com.example.springmvc.repository.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface ChatRepository extends BaseRepository<Chat> {
    List<Chat> getUserChats(UUID userId);

}
