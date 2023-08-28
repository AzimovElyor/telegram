package uz.pdp.telegram.repository.user;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import uz.pdp.telegram.model.User;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;

@Repository
@Log
public class UserRepositoryImpl implements UserRepository{

    private Connection connection;
    @Override
    public Optional<User> save(User user) {
      try{
          PreparedStatement preparedStatement = connection.prepareStatement(UserSqlQueries.CREATE);
          preparedStatement.setObject(1,user.getId());
          preparedStatement.setString(2,user.getFirstName());
          preparedStatement.setString(3,user.getLastName());
          preparedStatement.setString(4,user.getUsername());
          preparedStatement.setString(5,user.getPhoneNumber());
          preparedStatement.setString(6,user.getEmail());
          preparedStatement.setString(7,user.getPassword());
          preparedStatement.setBoolean(8,user.isDelete());
          preparedStatement.setTimestamp(9, Timestamp.valueOf(user.getCreated()));
          preparedStatement.setTimestamp(10, Timestamp.valueOf(user.getUpdated()));
          int execute = preparedStatement.executeUpdate();
          if(execute ==1){
              return Optional.of(user);
          }

return Optional.empty();

      }catch (SQLException e){
          e.printStackTrace();
          log.log(Level.SEVERE,e.getMessage());
          throw new RuntimeException("Userni save qilishda muaammo roy berdi");
      }
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Optional<User> findById(UUID id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserSqlQueries.FIND_BY_ID);
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(new User(resultSet));
            }
            return Optional.empty();
        }catch (SQLException e){
            e.printStackTrace();
            log.log(Level.SEVERE,e.getMessage());
            throw new RuntimeException("Userni id si   bilan topishda nuammo roy berdi");
        }
    }

    @Override
    public int update(User model) {
        return 0;
    }

    @Override
    public int delete(UUID id) {
        return 0;
    }

    @Override
    public boolean uniqueTelephoneNumber(String phoneNumber) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserSqlQueries.FIND_BY_PHONE_NUMBER);
            preparedStatement.setString(1,phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }catch (SQLException e){
            e.printStackTrace();
            log.log(Level.SEVERE,e.getMessage());
            throw new RuntimeException("Userni phoneNumberi bilan topishda nuammo roy berdi");
        }

    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserSqlQueries.FIND_BY_USERNAME);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
           return Optional.of(new User(resultSet));
            }
            return Optional.empty();
        }catch (SQLException e){
            e.printStackTrace();
            log.log(Level.SEVERE,e.getMessage());
            throw new RuntimeException("Userni emaili bilan topishda nuammo roy berdi");
        }
    }

    @Override
    public Optional<User>  findByEmail(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserSqlQueries.FIND_BY_EMAIL);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(new User(resultSet));
            }
            return Optional.empty();
        }catch (SQLException e){
            e.printStackTrace();
            log.log(Level.SEVERE,e.getMessage());
            throw new RuntimeException("Userni emaili bilan topishda nuammo roy berdi");
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
