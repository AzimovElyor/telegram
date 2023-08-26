package com.example.springmvc.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseModel{
    {
        this.id =UUID.randomUUID();
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime updated;
}
