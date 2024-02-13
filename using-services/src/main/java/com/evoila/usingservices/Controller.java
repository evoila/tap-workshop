package com.evoila.usingservices;


import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.nebhale.bindings.Binding;
import com.nebhale.bindings.Bindings;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.conversions.Bson;
import org.bson.Document;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class Controller {

    public Controller() {


        String uri = getURI();

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .serverApi(serverApi)
                .build();
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("admin");
            try {
                // Send a ping to confirm a successful connection
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
                // Add collection
                database.createCollection("person");
            } catch (MongoException me) {
                System.err.println(me);
            }
        }
        /*
        List<Binding> bindings = new Bindings().filterBindings("MongoDB");
        if (bindings.size() > 0) {
            Mongo
        }

         */
    }


    @GetMapping("/")    
    public String addPerson() {
        String uri = getURI();
        try (MongoClient mongoClient = MongoClients.create(uri)){
            MongoDatabase database = mongoClient.getDatabase("person");

            Bson command = new BsonDocument("ping", new BsonInt64(1));
            Document commandResult = database.runCommand(command);
            System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            return "Pinged your deployment. You successfully connected to MongoDB!";
        } catch (MongoException me) {
            System.err.println("Error while accessing database: " + me);
            return me.getMessage();
        }
        //return null;
        
    }

    String getURI() {
        Binding[] bindings = Bindings.fromServiceBindingRoot();
        bindings = Bindings.filter(bindings, "mongodb");

        if (bindings.length != 1) {
            System.err.printf("Incorrect number of PostgreSQL drivers: %d\n", bindings.length);
            System.exit(1);
        }
        return "mongodb://" + bindings[0].get("username") + ":" + bindings[0].get("password") + "@" + bindings[0].get("host") + ":" + bindings[0].get("port") + "/" + bindings[0].get("database");
        //return null;
    }
}
