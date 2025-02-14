
package fcu.sep.fcushop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
public class TestController {

	@GetMapping("/greeting")
	public String sayHello(@RequestParam String name) {
		return "Hello " + name;
	}

	@GetMapping("/api/cookie1")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp){
		//創建cookie
		Cookie cookie = new Cookie("name", "zhangsan");
		resp.addCookie(cookie);
		//return "ok";
	}
}
