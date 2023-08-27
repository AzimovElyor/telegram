package com.example.springmvc.repository;

import com.example.springmvc.model.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository extends BaseRepository<Message>{
    List<Message> findByUserId(UUID sederId);
}
