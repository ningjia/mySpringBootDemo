package com.mytest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MySpringBootDemoApplication extends SpringBootServletInitializer {

	@Value(value = "${app.name}")
	private String appNme;

	@Autowired
	private Book book;

	/**
	 *重写configure,用于生成war文件时,能够成功初始化Servlet
	 * @param application
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MySpringBootDemoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootDemoApplication.class, args);
	}

	@RequestMapping(value = "/",produces = "text/plain;charset=UTF-8")
	String index(){
		return "Hello, Spring Boot! App_Name="+appNme+" The BookName is "+book.getName()+";and Book Author is "+book.getAuthor()+";and Book price is "+book.getPrice();
	}
}
