package org.corodiak.scfakedeveloper.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.corodiak.scfakedeveloper.exception.FileUploadFailException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Strings;

@Component
public class FileHandler {

	@Value("${resource.file.path}")
	private String filePath;

	@Value("${resource.file.allow-extension}")
	private String[] allowExtensions;

	public String saveFile(MultipartFile multipartFile) throws FileUploadFailException {
		String originalFilename = multipartFile.getOriginalFilename();
		if (!validateFilename(originalFilename)) {
			throw new FileUploadFailException("허용되지 않은 파일명 : " + originalFilename);
		}
		String extension = FilenameUtils.getExtension(originalFilename);
		if (!validateExtension(extension)) {
			throw new FileUploadFailException("허용되지 않은 확장자 : " + originalFilename);
		}
		String newFilename = System.nanoTime() + "_" + UUID.randomUUID() + "." + extension;
		File file = new File(filePath + File.separator + newFilename);
		try {
			multipartFile.transferTo(file);
			file.setReadable(true);
			file.setWritable(false);
			file.setExecutable(false);
		} catch (IOException e) {
			throw new FileUploadFailException("파일 저장 실패", e);
		}
		return newFilename;
	}

	private boolean validateExtension(String extension) {
		for (String allowExtension : allowExtensions) {
			if (allowExtension.equals(extension)) {
				return true;
			}
		}
		return false;
	}

	private boolean validateFilename(String fileName) {
		return !Strings.isNullOrEmpty(fileName);
	}
}
