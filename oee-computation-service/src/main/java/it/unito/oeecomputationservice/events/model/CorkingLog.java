package it.unito.oeecomputationservice.events.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "corkinglogs")
public class CorkingLog {

    @Id
    private String id;

    private String info = "";
    private String date;
    private String time;
    private String timestamp;
    private String di12;
    private String di13;
    private String di4;
    private String di14;
    private String ai1;
    private String di1;
    private String di2;
    private String totdi12;
    private String totdi13;
    private String almgenerico;
    private String di4neq0;

    public CorkingLog() {
    }
}
