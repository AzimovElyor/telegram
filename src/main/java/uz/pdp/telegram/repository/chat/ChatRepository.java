package uz.pdp.telegram.repository.chat;

import uz.pdp.telegram.model.Chat;
import uz.pdp.telegram.repository.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface ChatRepository extends BaseRepository<Chat> {
    List<Chat> getUserChats(UUID userId);
    int delete(UUID chatId,UUID memberId);

}
