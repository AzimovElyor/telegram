package uz.pdp.telegram.repository.chat;

public interface ChatSqlQueries {
    String CREATE = """
            insert into chat(id,member_first_id, member_second_id)
            values(?,?,?);
            """;

    String IS_NEW_CHAT = """
    select * from chat where (member_first_id = ?  &&  member_second_id = ?)
    or (member_first_id = ? && member_second_id = ?);
    """;

    String USER_CHATS ="select * from chat where member_first_id = ? or member_second_id = ? order by updated_date desc;";
}
