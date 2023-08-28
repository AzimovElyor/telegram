package uz.pdp.telegram.repository.user;

public interface UserSqlQueries {
      String CREATE = """
                        insert into telegram_user2
                        (id,first_name, last_name,username, phone_number,
                        email, password, is_delete, created_date, updated_date
                        )
                        values(?,?,?,?,?,?,?,?,?,?);
                        """;

      String FIND_BY_EMAIL = "select * from telegram_user2 where email = ? and is_delete = false;";
      String FIND_BY_USERNAME = "select * from telegram_user2 where username = ? and is_delete = false;";
      String FIND_BY_PHONE_NUMBER = "select * from telegram_user2 where phone_number = ? and is_delete = false;";
      String FIND_BY_ID = "select * from telegram_user2 where id = ? and is_delete = false;";
}
