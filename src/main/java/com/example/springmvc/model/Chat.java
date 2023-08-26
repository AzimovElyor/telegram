package com.example.springmvc.model;

import java.util.UUID;

public class Chat extends BaseModel{
    private UUID senderId;
    private UUID receiverId;
    private boolean isDeleted;
    private boolean isNew;

}
