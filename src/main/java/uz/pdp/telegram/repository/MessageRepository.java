package uz.pdp.telegram.repository;

import uz.pdp.telegram.model.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends BaseRepository<Message>{
    List<Message> findByUserId(UUID sederId);
}
