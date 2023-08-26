package com.example.springmvc.model;

import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Chat extends BaseModel{
    private UUID memberFirstId;
    private UUID memberSecondId;
    private UUID memberFirstIsDelete;
    private UUID memberSecondIsDelete;
    private boolean isActive;

}
