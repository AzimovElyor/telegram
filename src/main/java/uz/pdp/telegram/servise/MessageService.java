package uz.pdp.telegram.servise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.telegram.repository.MessageRepositoryImpl;
@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepositoryImpl messageRepository;

}
