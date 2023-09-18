package com.github.project1.web.advice;

import com.github.project1.service.exceptions.NotAcceptException;
import com.github.project1.service.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException nfe){
        log.error("DB 조회시 에러로 다음처럼 출력합니다." + nfe.getMessage() );
        return nfe.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(NotAcceptException.class)
    public String handleNotAcceptException(NotAcceptException nae){
        log.error("Client 요청이 모종의 이유로 거부됩니다. " + nae.getMessage() );
        return nae.getMessage();
    }
}
