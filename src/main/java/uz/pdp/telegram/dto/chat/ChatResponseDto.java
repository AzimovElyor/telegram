package uz.pdp.telegram.dto.chat;

import lombok.*;
import uz.pdp.telegram.dto.user.UserResponseDto;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChatResponseDto {
    private UUID id;
    private UUID memberFirstId; //id name fullname
    private UserResponseDto memberSecond;



}
