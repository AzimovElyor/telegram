package uz.pdp.telegram.model;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel{
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private boolean isDelete;

    public User(ResultSet resultSet){
        try{
            setId((UUID) resultSet.getObject(1));
            this.firstName = resultSet.getString(2);
            this.lastName = resultSet.getString(3);
            this.username = resultSet.getString(4);
            this.phoneNumber = resultSet.getString(5);
            this.email = resultSet.getString(6);
            this.password = resultSet.getString(7);
            this.isDelete = resultSet.getBoolean(8);
            setCreated(resultSet.getTimestamp(9).toLocalDateTime());
            setUpdated(resultSet.getTimestamp(10).toLocalDateTime());

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Userni resulaset bilan muammo ");
        }
    }
}
