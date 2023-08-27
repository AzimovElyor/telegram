package com.example.springmvc.repository;

import com.example.springmvc.model.Message;
import lombok.Data;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
@Data

public class MessageRepositoryImpl implements MessageRepository {
    private DriverManagerDataSource dataSource;
    private Connection connection;
    private final String SAVE = "insert into message (id, txt, chat_id, sender_id, created_date, updated_date)\n" +
            "VALUES (?, ?, ?, ?, ?, ?);";
    private final String GET_ALL = "select * from message";
    private final String FIND_BY_ID = "select * from message where id = ?";
    private final String FIND_BY_SENDER_ID = "select * from message where sender_id = ?";
    private final String UPDATE = " update message\n" +
            "set txt = ?\n" +
            "where id= ?;";
    private final String DELETE = "delete from message where id?;";

    public void setDataSource(DriverManagerDataSource dataSource) {

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Message> save(Message model) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
            preparedStatement.setObject(1,model.getId());
            preparedStatement.setString(2,model.getText());
            preparedStatement.setObject(3,model.getChatId());
            preparedStatement.setObject(4,model.getSenderId());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(model.getCreated()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(model.getUpdated()));
            preparedStatement.execute();
            return Optional.of(model);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<Message>> getAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Message> messages = new ArrayList<>();

            while (resultSet.next()) {
                Message card = Message.map(resultSet);
                messages.add(card);
            }

            return Optional.of(messages);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Message> findById(UUID id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(Message.map(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int update(Message model) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1,model.getText());
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
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setObject(1,id);
            preparedStatement.execute();
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<Message>> findByUserId(UUID sederId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_SENDER_ID);
            preparedStatement.setObject(1, sederId);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Message> messages = new ArrayList<>();

            while (resultSet.next()) {
                Message card = Message.map(resultSet);
                messages.add(card);
            }
            return Optional.of(messages);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
