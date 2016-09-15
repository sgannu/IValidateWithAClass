package com.sgannu.ivalidator.example;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sgannu.ivalidator.error.ErrorToJsonTranslator;

@Controller
public class SampleWebController {

	@ResponseBody
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/results")
	public String validate(@ModelAttribute("sampleWebForm") final SampleWebForm form) {

		return "{status: 'success'}";
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("ContentType", MediaType.APPLICATION_JSON);
		String error = new ErrorToJsonTranslator().transformToJson(ex.getBindingResult().getFieldErrors());

		return new ResponseEntity<String>(error, headers, org.springframework.http.HttpStatus.OK);
	}

}
