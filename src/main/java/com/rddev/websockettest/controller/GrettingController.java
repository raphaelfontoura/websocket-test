package com.rddev.websockettest.controller;

import com.rddev.websockettest.model.Greeting;
import com.rddev.websockettest.model.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class GrettingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws InterruptedException {
        Thread.sleep(1000);
        return new Greeting("["+LocalDateTime.now() + "] To all : "+ HtmlUtils.htmlEscape(message.getName()));
    }

    @MessageMapping("/private")
    @SendToUser("/queue/message")
    public Greeting greetingPrivate(HelloMessage message, Principal principal) throws InterruptedException {
        Thread.sleep(2000);
        return new Greeting("["+LocalDateTime.now() + "] To me : "+ HtmlUtils.htmlEscape(message.getName()));
    }

    @MessageMapping("/private")
    public Greeting messageToUser(HelloMessage message, String username) throws InterruptedException {
        Thread.sleep(2000);
        return new Greeting("["+LocalDateTime.now() + "] To me : "+ HtmlUtils.htmlEscape(message.getName()));
    }

}
