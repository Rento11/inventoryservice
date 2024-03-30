package com.example.inventoryservice;

import com.example.inventoryservice.dao.entities.Creator;
import com.example.inventoryservice.dao.entities.Video;
import com.example.inventoryservice.service.CreatorManager;
import com.example.inventoryservice.service.VideoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class InventoryserviceApplication {

    @Autowired
    private CreatorManager creatorManager;
    @Autowired
    private VideoManager videoManager;
    public static void main(String[] args) {
        SpringApplication.run(InventoryserviceApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(){
        return args -> {
            //CREATING CREATORS
            Creator creator1 = new Creator();
            creator1.setName("TAHA ESSOU");
            creator1.setEmail("TAHA_ESSOU@gmail.com");
            Creator creator2 = new Creator();
            creator2.setName("SIMO SEDRATI");
            creator2.setEmail("SIMO_SEDRATI@gmail.com");
            Creator creator3 = new Creator();
            creator3.setName("MAMBAH FIT");
            creator3.setEmail("MAMBAH_FIT@gmail.com");

            creatorManager.addCreator(creator1);
            creatorManager.addCreator(creator2);
            creatorManager.addCreator(creator3);

            //CREATING VIDEOS
            Video video1 = new Video();
            video1.setName("SHOPPING IN THE MEDINA");
            video1.setUrl("https://youtu.be/uUoYna-Silk?si=e5yZ_Rk92mdBJHIi");
            video1.setDescription("LOREM IPSUM");
            video1.setDatePublication(new Date(2024,3,23));
            video1.setCreator(creator1);
            Video video2 = new Video();
            video2.setName("BACK TO MEMORIES");
            video2.setUrl("https://youtu.be/2WkmUiA-G7s?si=1KEM-ZCGJw1ZY8v-");
            video2.setDescription("LOREM IPSUM");
            video2.setDatePublication(new Date(2024,3,8));
            video2.setCreator(creator1);
            Video video3 = new Video();
            video3.setName("SEDPODCAST");
            video3.setUrl("https://youtu.be/amqYvtoulOc?si=jsRfQbxqX09thgyU");
            video3.setDescription("LOREM IPSUM");
            video3.setDatePublication(new Date(2023,06,18));
            video3.setCreator(creator2);
            Video video4 = new Video();
            video4.setName("CRYPTO");
            video4.setUrl("https://youtu.be/SgM5hQzGTZc?si=KrlXk3EO_5TiYZqV");
            video4.setDescription("LOREM IPSUM");
            video4.setDatePublication(new Date(2022,11,8));
            video4.setCreator(creator2);
            Video video5 = new Video();
            video5.setName("TRAINING WITH @");
            video5.setUrl("https://youtu.be/JAsQTE9MuUk?si=8v7DLPf4RYlVHK1v");
            video5.setDescription("LOREM IPSUM");
            video5.setDatePublication(new Date(2024,3,3));
            video5.setCreator(creator3);
            Video video6 = new Video();
            video6.setName("IFTAR WITH @");
            video6.setUrl("https://youtu.be/UjPX4qF1c5s?si=PoQP9pxcms7eDbjV");
            video6.setDescription("LOREM IPSUM");
            video6.setDatePublication(new Date(2024,3,24));
            video6.setCreator(creator3);

            videoManager.addVideo(video1);
            videoManager.addVideo(video2);
            videoManager.addVideo(video3);
            videoManager.addVideo(video4);
            videoManager.addVideo(video5);
            videoManager.addVideo(video6);

            //SETTING VIDEOS TO CREATORS
            creator1.setVideos(List.of(video1,video2));
            creator2.setVideos(List.of(video3,video4));
            creator3.setVideos(List.of(video5,video6));

            creatorManager.updateCreator(creator1);
            creatorManager.updateCreator(creator2);
            creatorManager.updateCreator(creator3);

        };
    }
}
