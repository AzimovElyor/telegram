package uz.pdp.telegram.servise;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.telegram.dto.user.UserRequestDto;
import uz.pdp.telegram.dto.user.UserResponseDto;
import uz.pdp.telegram.model.User;
import uz.pdp.telegram.repository.user.UserRepositoryImpl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepositoryImpl userRepository;
    private  final String PHONE_NUMBER_VALIDATOR = "\\+998(99|33|71|95|90|88|93|94)[0-9]{7}$";
    public int sendEmail(String email){

       int randomPassword = 0;
       for(int i = 0; i < 5; i++){
           int random = new Random().nextInt(10);
           if(random == 0) {
               i--;
               continue;
           }
           randomPassword = randomPassword * 10 + random;
       }

        final String username = "elyorazimov098@gmail.com"; // Jo'natuvchi pochta manzili
        final String password = "kxcbclbgywufsglm"; // Jo'natuvchi pochta paroli

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Yuborish xizmati uchun server
        props.put("mail.smtp.port", "465"); // Yuborish porti
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");


        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email)); // Qabul qiluvchi pochta manzili
            message.setSubject("Your telegram password");
            message.setText("Your telegram verification password is " + randomPassword);

            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
           return -1;
        }
        return randomPassword;
    }
    public Optional<UserResponseDto> save(UserRequestDto userRequestDto){

        if (!checkUserData(userRequestDto)) {
            throw new RuntimeException("Incorrect user data");
        }
        User user = new User();
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        user.setPhoneNumber(userRequestDto.getTelephoneNumber());
        user.setPassword(userRequestDto.getPassword());
       Optional <User> save = userRepository.save(user);
        if(save.isPresent()){
           return Optional.of(new UserResponseDto(user)) ;
        }
    return Optional.empty();
    }
    public Optional<UserResponseDto> login(String usernameOrEmail , String password){
        Optional<User> byUsername = userRepository.findByUsername(usernameOrEmail);
        Optional<User> byEmail = userRepository.findByEmail(usernameOrEmail);
        if(byEmail.isPresent()){
            if(byEmail.get().getPassword().equals(password)){
                return Optional.of(new UserResponseDto(byEmail.get()));
            }
        } else if (byUsername.isPresent()) {
            if(byUsername.get().getPassword().equals(password)){
                return Optional.of(new UserResponseDto(byUsername.get()));
            }
        }
         return Optional.empty();
    }
    private boolean checkUserData(UserRequestDto userRequestDto){
        if(userRequestDto.getFirstName() != null && userRequestDto.getPassword() != null && userRequestDto.getConfirmPassword() != null){
            if(userRequestDto.getFirstName().length() >= 2 && userRequestDto.getPassword().length() >= 4 && userRequestDto.getPassword().equals(userRequestDto.getConfirmPassword())){
                if(userRequestDto.getTelephoneNumber() != null){
                    Pattern pattern = Pattern.compile(PHONE_NUMBER_VALIDATOR);
                    return pattern.matcher(userRequestDto.getTelephoneNumber()).matches()
                            && !userRepository.uniqueTelephoneNumber(userRequestDto.getTelephoneNumber());
                }
              return true;
            }
        }
        return false;



    }

    public boolean checkEmailUnique(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    }

