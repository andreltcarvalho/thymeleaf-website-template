package com.template.services;

import com.template.entities.UserEntity;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Component
@Service
public class SenderMailService {

    private static Logger logger = LoggerFactory.getLogger(SenderMailService.class);

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private UserEntityService userService;

    @Value("${site.url}")
    private String siteURL;

    @Value("${spring.profiles.active}")
    private String profile;

    public void sendVerificationEmail(UserEntity user)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "thymeleaf.template@gmail.com";
        String senderName = "Thymeleaf Template";
        String subject = "Por favor, verifique sua conta";
        String content = "Olá [[name]],<br> <br><br>"
                + "Clique no link abaixo para habilitar sua conta:<br><br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFICAR CONTA</a></h3><br><br>"
                + "Este é um e-mail automático e não deve ser respondido.<br>"
                + "Obrigado,<br><br>"
                + "André Luis.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        content = content.replace("[[name]]", user.getName());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);
        if (profile.equals("prod")) {
            mailSender.send(message);
        } else {
            System.out.println(content);
        }
    }

    public void sendNewPasswordEmail(UserEntity user) {
        String toAddress = user.getEmail();
        String randomCode = RandomString.make(64);
        String fromAddress = "thymeleaf.template@gmail.com";
        String senderName = "Thymeleaf Template";
        String subject = "Recuperar Senha ";
        String content = "Olá [[name]],<br><br>"
                + "Para cadastrar uma nova senha, entre no link abaixo: <br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">RECUPERAR SENHA</a></h3><br><br>"
                + "André Luis.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            content = content.replace("[[name]]", user.getName());
            user.setVerificationCode(randomCode);
            String verifyURL = siteURL + "/newPassword?verificationCode=" + user.getVerificationCode();
            content = content.replace("[[URL]]", verifyURL);
            helper.setText(content, true);
            userService.update(user);
            if (profile.equals("prod")) {
                mailSender.send(message);
            } else {
                System.out.println(content);
            }
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}