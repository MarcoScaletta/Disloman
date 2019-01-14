package it.unito.ingestionservice.events.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CorkingLog {


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
}
