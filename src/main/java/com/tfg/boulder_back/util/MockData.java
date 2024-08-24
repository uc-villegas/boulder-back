package com.tfg.boulder_back.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tfg.boulder_back.domain.response.BoulderDetails;
import com.tfg.boulder_back.domain.response.RouteDetails;
import com.tfg.boulder_back.domain.response.UserDetails;
import com.tfg.boulder_back.domain.response.VideoDetails;

import java.util.Arrays;
import java.util.List;

public class MockData {

    public static UserDetails getUserMockData(){

        UserDetails userDetails = new UserDetails();

        try{

            // User Details
            userDetails.setIdUser("123456");
            userDetails.setName("John");
            userDetails.setSurname("Doe");
            userDetails.setEmail("johnDoe@gmail.com");

            // Create an instance of ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(userDetails);

            System.out.println(json);

        }catch(Exception e){
            e.printStackTrace();
        }

        return userDetails;
    }

    public static BoulderDetails getBoulderData(){

        BoulderDetails boulderDetails = new BoulderDetails();

        try{

            // Video Details
            VideoDetails videoDetails1 = new VideoDetails();
            videoDetails1.setTitle("Video 1");

            VideoDetails videoDetails2 = new VideoDetails();
            videoDetails2.setTitle("Video 2");

            VideoDetails videoDetails3 = new VideoDetails();
            videoDetails3.setTitle("Video 3");

            // Route Details
            RouteDetails routeDetails1 = new RouteDetails();
            routeDetails1.setIdRoute(1L);
            routeDetails1.setName("Ruta 1");
            routeDetails1.setColor("Azul");
            routeDetails1.setDifficulty("Principiante");
            routeDetails1.setVideos(Arrays.asList(videoDetails1, videoDetails2, videoDetails3));

            RouteDetails routeDetails2 = new RouteDetails();
            routeDetails2.setIdRoute(2L);
            routeDetails2.setName("Ruta 2");
            routeDetails2.setColor("Verde");
            routeDetails2.setDifficulty("Intermedio");
            routeDetails2.setVideos(List.of(videoDetails3));

            RouteDetails routeDetails3 = new RouteDetails();
            routeDetails3.setIdRoute(3L);
            routeDetails3.setName("Ruta 3");
            routeDetails3.setColor("Amarillo");
            routeDetails3.setDifficulty("Dificil");

            // Boulder Details

            boulderDetails.setIdBoulder(123456L);
            boulderDetails.setName("Rocodromos Fernando");
            boulderDetails.setMail("rocodromosFernando@gmail.com");
            boulderDetails.setPhone("+34612345678");
            boulderDetails.setAddress("Calle San Fernando S/N");
            boulderDetails.setRoutes(Arrays.asList(routeDetails1, routeDetails2, routeDetails3));


            // Create an instance of Object Mapper
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(boulderDetails);

            System.out.println(json);

        }catch(Exception e){
            e.printStackTrace();
        }

        return boulderDetails;
    }
}
