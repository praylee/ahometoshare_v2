/*
 * File: AhometoshareApplication.java
 * Author: Peng Li
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */


package app.withyou.ahometoshare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("app.withyou.ahometoshare.dao")
public class AhometoshareApplication {

	public static void main(String[] args) {
		SpringApplication.run(AhometoshareApplication.class, args);
	}

}

