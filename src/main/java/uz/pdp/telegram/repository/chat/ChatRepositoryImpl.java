package uz.pdp.telegram.repository.chat;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import uz.pdp.telegram.model.Chat;

import java.sql.*;
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
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ChatSqlQueries.GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Chat> chats = new ArrayList<>();
            while (resultSet.next()){
                chats.add(new Chat(resultSet));
            }
            return chats;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Chat> findById(UUID id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ChatSqlQueries.FIND_BY_ID);
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return Optional.of(new Chat(resultSet));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Chat model) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ChatSqlQueries.UPDATE);
            preparedStatement.setTimestamp(1,Timestamp.valueOf(model.getUpdated()));
            preparedStatement.setObject(2,model.getId());
            preparedStatement.execute();
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(UUID id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ChatSqlQueries.DELETE_CHAT);
            preparedStatement.setObject(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Chatni delete qiganda muammo boldi");
        }
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

    @Override
    public int delete(UUID chatId, UUID memberId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ChatSqlQueries.DELETE_CHAT_FROM_ONE_MEMBER);
            preparedStatement.setObject(1,memberId);
            preparedStatement.setObject(2,memberId);
            preparedStatement.setObject(3,chatId);
            preparedStatement.execute();
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException("Chatni delete qiganda muammo boldi");
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
