package ilike.ildrio.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class IndexController {

	@GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String robots() {
		return "User-agent: *\nAllow: /$\nDisallow: /";
	}


	@GetMapping(value = {"/", "/index"})
	public String index() {
		return  "index";
	}


}
