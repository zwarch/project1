package com.github.project1.service.mapper;


import com.github.project1.repository.UserEntity;
import com.github.project1.web.dto.LoginRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Mapping(target="email", source = "email")
    @Mapping(target="password", source = "password")
    UserEntity loginRequestToUserEntity(LoginRequest signupRequest);

    @Named("convert")
    static String localDateTimeToString(LocalDateTime localDateTime){
        if(localDateTime != null) return localDateTime.format(formatter);
        else return null;
    }


}
