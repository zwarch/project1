package com.github.project1.web.controller;


import com.github.project1.repository.UserEntity;
import com.github.project1.service.UserService;
import com.github.project1.web.dto.*;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public Result signup(@RequestBody SignupRequest signupRequest ){
        return userService.signup(signupRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse){
        User loginUser = userService.login(loginRequest);
        if( loginUser == null ) {
            return "로그인이 실패하였습니다.";
        }

        String jwt = Jwts.builder()
                    .setSubject("token1")
                    .claim("email", loginUser.getEmail())
                    .claim("name", loginUser.getUserName())
                    .claim("timestamp", loginUser.getLastLoginTime())
                    .compact();
        httpServletResponse.addHeader("Token",jwt);

        return "로그인이 성공적으로 완료되었습니다.";
    }


    @PostMapping("/logout")
    public String logout(@RequestHeader("Token") String token, @RequestBody LogoutRequest logoutRequest) {
        Claims claims = Jwts.parser()
                .parseClaimsJwt(token)
                .getBody();

        String email = (String) claims.get("email");
        String name = (String) claims.get("name");
        long timestamp = (long) claims.get("timestamp");

        if( email.equals(logoutRequest.getEmail()) == false ){
            return "로그아웃이 실패하였습니다.";
        }

    //    userService.logout(logoutRequest);
        return "로그아웃이 성공적으로 완료되었습니다.";
    }

    @GetMapping("/generate-token")
    public String generateToken(HttpServletResponse httpServletResponse){
        String jwt = Jwts.builder()
                .setSubject("token1")
                .claim("user","조인성")
                .claim("gender", "남자")
                .claim("job","배우")
                .compact();
        httpServletResponse.addHeader("Token",jwt);
        return "JWT set OK";
    }

    @GetMapping("/show-token")
    public String showTokwn(@RequestHeader("Token") String token){
        Claims claims = Jwts.parser()
                .parseClaimsJwt(token)
                .getBody();

        String user = (String) claims.get("user");
        String gender = (String) claims.get("gender");
        String job = (String) claims.get("job");


        return String.format("직업 : %s, 성별 : %s, 이름 : %s\n", job, gender, user );
    }

}
