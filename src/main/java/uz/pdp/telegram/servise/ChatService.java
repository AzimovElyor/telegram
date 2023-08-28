package uz.pdp.telegram.servise;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.telegram.dto.chat.ChatRequestDto;
import uz.pdp.telegram.dto.chat.ChatResponseDto;
import uz.pdp.telegram.dto.user.UserResponseDto;
import uz.pdp.telegram.model.Chat;
import uz.pdp.telegram.model.User;
import uz.pdp.telegram.repository.chat.ChatRepositoryImpl;
import uz.pdp.telegram.repository.user.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {
    @Autowired
    private final ChatRepositoryImpl chatRepository;
    @Autowired
    private final UserRepositoryImpl userRepository;

    public List<ChatResponseDto> getUsersChat(UUID userId){
        List<Chat> userChats = chatRepository.getUserChats(userId);
        System.out.println("userChats = " + userChats);
         List<ChatResponseDto> chatResponseDto = new ArrayList<>();
        for (Chat userChat : userChats) {
            if(!userChat.getMemberFirstId().equals(userId)){
                Optional<User> byId = userRepository.findById(userChat.getMemberFirstId());
                byId.ifPresent(user -> chatResponseDto.add(new ChatResponseDto(userChat.getId(), userId, new UserResponseDto(user))));
            }else{
                Optional<User> byId = userRepository.findById(userChat.getMemberSecondId());
                byId.ifPresent(user -> chatResponseDto.add(new ChatResponseDto(userChat.getId(), userId, new UserResponseDto(user))));
            }
        }

        return chatResponseDto;
    }
    public int saveChat(ChatRequestDto chatRequestDto){
        if (isNewChat(chatRequestDto.getMemberFirstId(),chatRequestDto.getMemberSecondId())) {
            throw new RuntimeException("Mavjud bolgan chat qayta yaratilmasligi kerak");
        }
        Chat chat = new Chat();
        chat.setMemberFirstId(chatRequestDto.getMemberFirstId());
        chat.setMemberSecondId(chatRequestDto.getMemberSecondId());
        Optional<Chat> save = chatRepository.save(chat);
        if(save.isPresent()) return 1;
        return 0;
    }
    private boolean isNewChat(UUID firstMember, UUID secondMember){
        return chatRepository.isNewChat(firstMember,secondMember);
    }
}
