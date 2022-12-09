package com.example.UserAccountService.config;


import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice

public class GlobalConfig {

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(MethodArgumentTypeMismatchException.class)
public Map<String,String>    handleMethod(MethodArgumentTypeMismatchException e){
Map<String,String> map=new LinkedHashMap();
map.put("ERROR",e.getMessage());
     return map;
}
}