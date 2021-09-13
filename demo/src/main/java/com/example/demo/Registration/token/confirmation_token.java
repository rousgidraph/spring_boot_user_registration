package com.example.demo.Registration.token;

import com.example.demo.appuser.appUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class confirmation_token {

    @SequenceGenerator(name = "confirmation_token_sequence"
            ,sequenceName = "confirmation_token_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence")
    private long id ;


    @ManyToOne
    @JoinColumn(nullable = false, name = "app_user_id")
    private appUser user ;

    @Column(nullable = false)
    private String token ;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    public confirmation_token(String token, LocalDateTime createdAt, LocalDateTime expiresAt, appUser AppUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;

        this.user = AppUser;
    }
}
