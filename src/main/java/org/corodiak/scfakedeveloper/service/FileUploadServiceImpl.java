package org.corodiak.scfakedeveloper.service;

import org.corodiak.scfakedeveloper.exception.FileUploadFailException;
import org.corodiak.scfakedeveloper.util.FileHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

	private final FileHandler fileHandler;

	@Value("${resource.file.url}")
	private String fileURL;

	@Override
	public String saveFile(MultipartFile multipartFile) throws FileUploadFailException {
		String realFilename = fileHandler.saveFile(multipartFile);
		return fileURL + "/" + realFilename;
	}
}
