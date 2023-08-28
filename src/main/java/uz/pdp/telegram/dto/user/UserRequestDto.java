package uz.pdp.telegram.dto.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String username;

    private String telephoneNumber;

    private String password;
    private String confirmPassword;
}
