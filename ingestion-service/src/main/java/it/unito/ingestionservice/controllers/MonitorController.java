package it.unito.ingestionservice.controllers;

import it.unito.ingestionservice.events.model.CorkingLog;
import it.unito.ingestionservice.events.model.Greeting;
import it.unito.ingestionservice.events.source.GreetingSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
//@CrossOrigin
public class MonitorController {

    private final GreetingSource greetingSource;

    @Autowired
    public MonitorController(GreetingSource greetingSource) {
        this.greetingSource = greetingSource;
    }

    @PostMapping("/greetings")
    public @ResponseBody Greeting createMessage(@RequestBody Greeting greeting) {
        log.info("Ricevuto nuovo dato da Orchestra! Aggiungo il timestamp!");
        greeting.setTimestamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        greetingSource.sendGreeting(greeting);
        return greeting;
    }

    @PostMapping("/corkinglogs")
    public @ResponseBody
    CorkingLog creaCorkingLog(@RequestBody CorkingLog corkingLog) {
        log.info("Ricevuto nuovo dato da Orchestra! Aggiungo il timestamp!");
        corkingLog.setInfo(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        greetingSource.sendCorkingLog(corkingLog);
        return corkingLog;
    }

}
