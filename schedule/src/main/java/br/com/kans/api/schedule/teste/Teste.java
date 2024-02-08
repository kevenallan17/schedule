package br.com.kans.api.schedule.teste;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class Teste {

	private static final Logger log = LoggerFactory.getLogger(Teste.class);
	
	 @Autowired
	 private JavaMailSender mailSender;
	
	@Scheduled(fixedRate = 10000)
	public void x() {
		log.info("hora atual: {}", LocalDateTime.now());
		try {
			this.sendEmail("kevenallan98@gmail.com", "Teste", "Teste 123");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


    public void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true); // O segundo parâmetro indica se o texto é HTML
        mailSender.send(message);
    }
	
}
