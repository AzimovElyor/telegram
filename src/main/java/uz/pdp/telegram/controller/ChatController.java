package uz.pdp.telegram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.telegram.dto.chat.ChatRequestDto;
import uz.pdp.telegram.dto.chat.ChatResponseDto;
import uz.pdp.telegram.servise.ChatService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    @Autowired
private final ChatService chatService;
    @GetMapping("/my-chats/{userId}")
    public String myChats(
            @PathVariable UUID userId,
            Model model){
        System.out.println("Chat id ga keldi");
        List<ChatResponseDto> usersChat = chatService.getUsersChat(userId);
        System.out.println(usersChat);// chat user
        model.addAttribute("myChats",usersChat);
        return "chat/chatMenu";

    }
    @PostMapping("/create")
    public String createChat(@ModelAttribute ChatRequestDto chatRequestDto){
        System.out.println(chatRequestDto);
        chatService.saveChat(chatRequestDto);
        return "";
    }
    @GetMapping("/chatting/{chatId}")
    public String  chatting(
            @PathVariable UUID chatId
    ){
        System.out.println("chatId = " + chatId);
        return "";
    }
}
