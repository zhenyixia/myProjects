package com.lyp.springboot01.authmanage.service;

public interface MailService {

    void sendMail(String to ,String subject,String content);
}
