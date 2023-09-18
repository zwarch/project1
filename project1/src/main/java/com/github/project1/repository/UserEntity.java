package com.github.project1.repository;

import com.github.project1.web.dto.SignupRequest;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name ="users")
public class UserEntity {
    @Id
    @Column(name="email")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;


    public UserEntity(SignupRequest signupRequest){
        this.email = signupRequest.getEmail();
        this.password = signupRequest.getPassword();
        this.userName = signupRequest.getUserName();
        this.createAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        UserEntity that = (UserEntity) o;
        return email != null && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
