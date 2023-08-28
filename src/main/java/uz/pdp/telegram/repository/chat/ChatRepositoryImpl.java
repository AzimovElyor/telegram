package uz.pdp.telegram.repository.chat;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import uz.pdp.telegram.model.Chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;

@Repository
@Log
public class ChatRepositoryImpl implements ChatRepository{
    private DriverManagerDataSource dataSource;
    private Connection connection;
    @Override
    public Optional<Chat> save(Chat chat) {
      try {
          PreparedStatement preparedStatement = connection.prepareStatement(ChatSqlQueries.CREATE);
          preparedStatement.setObject(1,chat.getId());
          preparedStatement.setObject(2,chat.getMemberFirstId());
          preparedStatement.setObject(3,chat.getMemberFirstId());
          int res = preparedStatement.executeUpdate();
          if(res == 1){
              return Optional.of(chat);
          }

      }catch (SQLException e){
          e.printStackTrace();
          log.log(Level.SEVERE,e.getMessage());
          throw new RuntimeException("Chatni save qilishda muammo roy berdi");
      }
        return Optional.empty();
    }

    @Override
    public List<Chat> getAll() {
        return null;
    }

    @Override
    public Optional<Chat> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public int update(Chat model) {
        return 0;
    }

    @Override
    public int delete(UUID id) {
        return 0;
    }

    @Override
    public List<Chat> getUserChats(UUID userId) {
        List<Chat> userChats = new ArrayList<>();
      try {
          PreparedStatement preparedStatement = connection.prepareStatement(ChatSqlQueries.USER_CHATS);
          preparedStatement.setObject(1,userId);
          preparedStatement.setObject(2,userId);
          ResultSet resultSet = preparedStatement.executeQuery();
          while (resultSet.next()){
              userChats.add(new Chat(resultSet));
          }
   return userChats;
      }catch (SQLException e){
          e.printStackTrace();
          log.log(Level.SEVERE,e.getMessage());
          throw new RuntimeException("Userni chatlarini  topishda nuammo roy berdi");
      }
    }
    public boolean isNewChat(UUID firstMember, UUID secondMember){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ChatSqlQueries.IS_NEW_CHAT);
            preparedStatement.setObject(1,firstMember);
            preparedStatement.setObject(2,secondMember);
            preparedStatement.setObject(3,secondMember);
            preparedStatement.setObject(4,firstMember);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Chatni yangilikka tekshirishda muammo roy berdi");
        }
    }

    @Autowired
    public void setDataSource(DriverManagerDataSource dataSource){
        try {
            connection = dataSource.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Connectionni ulashda xatolik roy berdi");
        }
    }
}
