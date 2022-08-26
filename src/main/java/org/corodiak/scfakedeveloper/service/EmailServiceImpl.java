package org.corodiak.scfakedeveloper.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	@Value("${email.receiver}")
	private String[] receiver;

	@Override
	public void sendMail(MultipartFile multipartFile) throws
		MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		mimeMessageHelper.setFrom(sender);
		mimeMessageHelper.setSubject("Window Capture Mail - Fake Developer");
		mimeMessageHelper.setTo(receiver);
		mimeMessageHelper.setText("Captured Image.");
		mimeMessageHelper.addAttachment("capture.png", multipartFile);
		javaMailSender.send(mimeMessage);
	}
}
