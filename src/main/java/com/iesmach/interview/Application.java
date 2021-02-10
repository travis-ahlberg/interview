package com.iesmach.interview;

import com.iesmach.interview.movies.MoviesConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import static java.util.Arrays.stream;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{Application.class, MoviesConfig.class}, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Beans in context:");
            stream(ctx.getBeanDefinitionNames())
                    .sorted()
                    .forEach(System.out::println);

            System.out.println("\n\nServer started!");
        };
    }

}
