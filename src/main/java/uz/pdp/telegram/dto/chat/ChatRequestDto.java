package uz.pdp.telegram.dto.chat;

import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ChatRequestDto {
    private UUID memberFirstId;
    private UUID memberSecondId;

}
