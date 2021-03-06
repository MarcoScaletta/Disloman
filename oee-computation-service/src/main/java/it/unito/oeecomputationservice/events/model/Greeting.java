package it.unito.oeecomputationservice.events.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "greetings")
public class Greeting {

    @Id
    private String id;
    private String message;
    private String timestamp;

    public Greeting(String id, String message, String timestamp) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Greeting() {
    }
}
