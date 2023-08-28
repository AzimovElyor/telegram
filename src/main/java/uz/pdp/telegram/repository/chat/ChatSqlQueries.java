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

    String DELETE_CHAT="delete  from chat where id=?;";
    String GET_ALL="select * from chat;";
    String FIND_BY_ID="select * from chat where id=?";
    String UPDATE="update chat set updated_date=? where id=?;";
    String DELETE_CHAT_FROM_ONE_MEMBER ="update chat c\n" +
            "set member_first_is_delete=case when c.member_first_id=? then c.member_first_id end,\n" +
            "    member_second_is_delete=case when c.member_second_id=? then c.member_second_is_delete end\n" +
            "where id=?;";
}
