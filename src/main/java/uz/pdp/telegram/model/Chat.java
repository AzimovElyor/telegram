package uz.pdp.telegram.model;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    public Chat(ResultSet resultSet){
        try {
            this.memberFirstId = (UUID) resultSet.getObject("member_first_id");
            this.memberSecondId = (UUID) resultSet.getObject("member_second_id");
            this.memberFirstIsDelete = (UUID) resultSet.getObject("member_first_is_delete");
            this.memberSecondIsDelete = (UUID) resultSet.getObject("member_second_is_delete");
            this.isActive = resultSet.getBoolean("is_active");
            setId((UUID) resultSet.getObject(1));
            setCreated( resultSet.getTimestamp(7).toLocalDateTime());
            setUpdated( resultSet.getTimestamp(8).toLocalDateTime());
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Resualt setdan chat yashash da muammo bor");
        }
    }

}
