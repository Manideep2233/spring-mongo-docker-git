package com.spring_mongo_docker_git.configuration;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

//@Configuration
public class MongoConfig {

//    @Bean
    public MongoDatabaseFactory mongoDbFactory() {
        return new SimpleMongoClientDatabaseFactory(
                "mongodb+srv://manideepvalut1:jaHJ0k4UHh8apwE7@mani-cluster.9ephlrw.mongodb.net/test1DB?retryWrites=true&w=majority&appName=Mani-Cluster"
        );
    }

//    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }
}

