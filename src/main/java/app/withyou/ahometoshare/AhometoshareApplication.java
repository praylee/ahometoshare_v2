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

