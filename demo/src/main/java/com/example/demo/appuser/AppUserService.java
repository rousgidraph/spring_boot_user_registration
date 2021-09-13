package com.example.demo.appuser;

import com.example.demo.Registration.token.ConfirmationTokenService;
import com.example.demo.Registration.token.confirmation_token;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@Service
public class AppUserService implements UserDetailsService {

    private final String User_Not_Found = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder encoder ;
    private final ConfirmationTokenService service ;


    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(User_Not_Found,email)) );
    }

    public String signUpUser (appUser user){
        boolean user_exists = appUserRepository.findByEmail(user.getEmail())
                .isPresent();

        if(user_exists){
            throw new IllegalStateException("email already taken ");
        }

        String encoded_pass =  encoder.encode(user.getPassword());
        user.setPassword(encoded_pass);

        appUserRepository.save(user);

        String token = UUID.randomUUID().toString();
        confirmation_token confirmationtoken = new confirmation_token(
            token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),user
        );

        service.saveConfirmationToken(confirmationtoken);

        //todo send email
        return token;
    }

}
