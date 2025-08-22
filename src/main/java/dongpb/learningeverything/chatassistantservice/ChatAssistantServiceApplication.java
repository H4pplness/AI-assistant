package dongpb.learningeverything.chatassistantservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ChatAssistantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatAssistantServiceApplication.class, args);
    }

}
