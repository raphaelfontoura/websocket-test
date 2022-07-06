package com.rddev.websockettest.controller;

import com.rddev.websockettest.model.Greeting;
import com.rddev.websockettest.model.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class GrettingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws InterruptedException {
        Thread.sleep(1000);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/private")
    @SendToUser("/queue/private")
    public Greeting greetingPrivate(HelloMessage message, Principal principal) throws InterruptedException {
        Thread.sleep(2000);
        return new Greeting("Hello private, " + HtmlUtils.htmlEscape(message.getName()) + "! ");
    }
}
