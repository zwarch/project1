package com.github.project1.web.dto;

import com.github.project1.repository.UserEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@Slf4j
public class User {
    @ApiModelProperty(name = "email", value = "사용자 Email", example = "ssss@abc.com") private String email;
    @ApiModelProperty(name = "password", value = "사용자 Password", example = "password") private String password;
    @ApiModelProperty(name = "user_name", value = "사용자 Name", example = "테스트 유저") private String userName;
    @ApiModelProperty(name = "create_at", value = "생성 시간", example = "2023-09-18 20:00:00") private String createAt;
    @ApiModelProperty(name = "last_login_at", value = "마지막 로그인 시간", example = "2023-09-18 20:00:00") private String lastLoginAt;


    public User(UserEntity userEntity){
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.userName = userEntity.getUserName();
        this.createAt = userEntity.getCreateAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        this.lastLoginAt = userEntity.getLastLoginAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public long getLastLoginTime(){
        log.info("this.lastLoginAt : " + this.lastLoginAt);
        LocalDateTime lastTime = LocalDateTime.parse(this.lastLoginAt, formatter);
        log.info("lastTime : " + lastTime );
        return Timestamp.valueOf(lastTime).getTime();
    }

}
