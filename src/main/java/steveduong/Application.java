package steveduong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "steveduong.v2")
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
