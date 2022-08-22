package org.corodiak.scfakedeveloper.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@RequestMapping
	public void index(HttpServletResponse response) throws IOException {
		response.sendRedirect("/swagger-ui/index.html");
	}

}
