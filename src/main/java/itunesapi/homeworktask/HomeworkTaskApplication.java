package itunesapi.homeworktask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application to search for favorite artist and save as your favorite.
 * Also it is possible to look up your favorite artist top albums.
 */
@SpringBootApplication
public class HomeworkTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeworkTaskApplication.class, args);
    }

}
