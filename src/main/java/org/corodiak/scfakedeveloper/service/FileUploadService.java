package org.corodiak.scfakedeveloper.service;

import org.corodiak.scfakedeveloper.exception.FileUploadFailException;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	String saveFile(MultipartFile multipartFile) throws FileUploadFailException;
}
