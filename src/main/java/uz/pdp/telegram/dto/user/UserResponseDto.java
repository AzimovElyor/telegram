package uz.pdp.telegram.dto.user;

import lombok.*;
import uz.pdp.telegram.model.User;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserResponseDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    public UserResponseDto(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.phoneNumber = user.getPhoneNumber();
    }
}
