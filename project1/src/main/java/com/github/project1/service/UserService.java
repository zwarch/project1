package com.github.project1.service;


import com.github.project1.repository.UserEntity;
import com.github.project1.repository.UserJpaRepository;
import com.github.project1.service.exceptions.NotAcceptException;
import com.github.project1.service.exceptions.NotFoundException;
import com.github.project1.web.dto.LoginRequest;
import com.github.project1.web.dto.LogoutRequest;
import com.github.project1.web.dto.Result;
import com.github.project1.web.dto.SignupRequest;
import com.github.project1.web.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserJpaRepository userJpaRepository;


    @Transactional(transactionManager = "tmJpa")
    public Result signup(SignupRequest signupRequest) {
        // Repository : User Repository
        UserEntity userEntity = new UserEntity(signupRequest);
        //UserEntity userEntity = UserMapper.INSTANCE.signupRequestToUserEntity(signupRequest);
        //UserEntity userEntityCreated;

        try {
            userJpaRepository.save(userEntity);
        } catch(RuntimeException e) {
            log.error(e.getMessage());
            log.error("userEntity : " + userEntity.toString());
            log.error("signup :: DB Fail");
            throw new NotAcceptException("회원가입이 실패했습니다.");
        }

        log.info("signup :: SUCC");
        return new Result("회원가입이 완료되었습니다.");
    }

    @Transactional(transactionManager = "tmJpa")
    public User login(LoginRequest loginRequest) {
        // Repository : User Repository
        UserEntity selectUser = userJpaRepository.findByEmail(loginRequest.getEmail());
        if( selectUser == null ){
            log.error("login :: NotFound User");
            throw new NotFoundException("입력한 가입자가 존재하지 않습니다.");
        }

        if( selectUser.getPassword().equals(loginRequest.getPassword()) == false ){
            log.error("login :: Invalid Password");
            throw new NotAcceptException("비밀번호가 다릅니다.");
        }

        selectUser.setLastLoginAt(LocalDateTime.now());

        log.info("login :: SUCC");
        return new User(selectUser);
    }

    @Transactional(transactionManager = "tmJpa")
    public Result logout(LogoutRequest logoutRequest) {
        // Repository :: User Repository
        log.info("logout :: " + logoutRequest.getEmail() );
        log.info("logout :: SUCC");
        return new Result("로그아웃되었습니다.");
    }
}
