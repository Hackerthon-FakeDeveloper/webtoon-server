package org.corodiak.scfakedeveloper.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.corodiak.scfakedeveloper.exception.FileUploadFailException;
import org.corodiak.scfakedeveloper.util.FileHandler;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender javaMailSender;
	private final FileHandler fileHandler;

	@Override
	public void sendMail(MultipartFile multipartFile) throws
		MessagingException {
		String[] target = {"ncw809@naver.com"};
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		mimeMessageHelper.setFrom("sdst74@kyonggi.ac.kr");
		mimeMessageHelper.setSubject("Window Capture Mail - Fake Developer");
		mimeMessageHelper.setTo(target);
		mimeMessageHelper.setText("Captured Image.");
		mimeMessageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
		javaMailSender.send(mimeMessage);
	}
}
