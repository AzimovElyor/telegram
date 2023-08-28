package uz.pdp.telegram.repository.user;

import uz.pdp.telegram.model.User;
import uz.pdp.telegram.repository.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    boolean uniqueTelephoneNumber(String phoneNumber);
    Optional<User>  findByUsername(String username);
    Optional<User>  findByEmail(String email);
}
