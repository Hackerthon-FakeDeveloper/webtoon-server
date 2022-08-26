package org.corodiak.scfakedeveloper.service;

import javax.mail.MessagingException;

import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
	void sendMail(MultipartFile multipartFile) throws MessagingException;
}
