package com.evoila.usingservices.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.nebhale.bindings.Binding;
import com.nebhale.bindings.Bindings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Objects;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        Binding[] bindings = Bindings.fromServiceBindingRoot();
        bindings = Bindings.filter(bindings, "mongodb");

        if (bindings.length != 1) {
            System.err.printf("Incorrect number of MongoDB drivers: %d\n", bindings.length);
            System.exit(1);
        }
        String uri =  "mongodb://" + bindings[0].get("username") + ":" + bindings[0].get("password") + "@" + bindings[0].get("host") + ":" + bindings[0].get("port") + "/" + bindings[0].get("database");
        //return null;
        return MongoClients.create(uri);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        try {
            Binding[] bindings = Bindings.fromServiceBindingRoot();
            bindings = Bindings.filter(bindings, "mongodb");
            return new MongoTemplate(mongoClient(), Objects.requireNonNull(bindings[0].get("database")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
