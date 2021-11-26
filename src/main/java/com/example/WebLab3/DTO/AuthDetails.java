package com.example.WebLab3.dto;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
@Data
public class AuthDetails {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String login;
    private String password;

    @PostConstruct
    public void init() {
        login = System.getenv("login");
        password = System.getenv("password");
        if (login == null || password == null) {
            logger.warn("Please set env params \"login\" and \"password\"");
        }
    }
}
