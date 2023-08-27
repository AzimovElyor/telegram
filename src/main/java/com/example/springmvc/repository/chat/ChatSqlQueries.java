package com.example.springmvc.repository.chat;

public interface ChatSqlQueries {
    String CREATE = """
             insert into chat
             (id,member_first_id, member_second_id , member_first_is_delete, member_second_is_delete, 
             is_active, created_date, updated_date
             )
             values(?,?,?,?,?,?,?,?);
""";
}
