package uz.pdp.telegram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.telegram.dto.user.UserRequestDto;
import uz.pdp.telegram.dto.user.UserResponseDto;
import uz.pdp.telegram.servise.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @GetMapping("/sign-up/email")
    public String signEmail(){
        return "auth/signUpEmail";
    }
    @PostMapping("/sign-up/email")
    public String sendEmail(
            @RequestParam String email,
            Model model
    ){
        System.out.println(email);
        if(! userService.checkEmailUnique(email)){
            int password = userService.sendEmail(email);
            System.out.println(password);
            if(password != -1){
                model.addAttribute("password", password);
                model.addAttribute("email", email);
                return "auth/signUpEmailPassword";
            }
        }
         return "auth/signUpEmail";
    }

    @PostMapping("/sign-up/email/password")
    public String validatePassword(@RequestParam String password,
                                   @RequestParam String email,
                                   @RequestParam Character num1,
                                   @RequestParam Character num2,
                                   @RequestParam Character num3,
                                   @RequestParam Character num4,
                                   @RequestParam Character num5,
                                   Model model

    ){
        StringBuilder userInputPassword = new StringBuilder();
        userInputPassword.append(num1);
        userInputPassword.append(num2);
        userInputPassword.append(num3);
        userInputPassword.append(num4);
        userInputPassword.append(num5);

        System.out.println(userInputPassword);
        System.out.println(password);

        if(password.equals(userInputPassword.toString())){
            model.addAttribute("email",email);
            return "auth/signUp";
        }else {
         return "auth/signUpEmail";
        }
    }

    @PostMapping("/sign-up")
    public String signIn(@ModelAttribute UserRequestDto userRequestDto,
                         @RequestParam String email,
                         Model model
    ){
        userRequestDto.setEmail(email);
        System.out.println(userRequestDto);
        Optional<UserResponseDto> save = userService.save(userRequestDto);
        if(save.isPresent()){
            model.addAttribute("userId",save.get().getId());
            return "mainMenu";
        }
        return "auth/signUp" ;
    }

    @GetMapping("/sign-in")
    public String singIn(){
            return "auth/signIn";
    }
    @PostMapping("/sing-in")
    public String signInPost(@RequestParam String usernameOrEmail,
                             @RequestParam String password,
                             Model model
    ){
        Optional<UserResponseDto> login = userService.login(usernameOrEmail, password);
        if(login.isPresent()){
            model.addAttribute("userId",login.get().getId());
            return "mainMenu";
        }
        return "auth/signIn";
    }



}
