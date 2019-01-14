package it.unito.oeecomputationservice.events.handler;

import it.unito.oeecomputationservice.events.GreetingStreams;
import it.unito.oeecomputationservice.events.model.CorkingLog;
import it.unito.oeecomputationservice.events.model.Greeting;
import it.unito.oeecomputationservice.repository.CorkingLogMongoRepository;
import it.unito.oeecomputationservice.repository.GreetingMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Slf4j
@Component
public class MessageHandler {

    @Autowired
    private GreetingMongoRepository greetingMongoRepository;
    @Autowired
    private CorkingLogMongoRepository corkingLogMongoRepository;

    @StreamListener(GreetingStreams.INPUT_GREETINGS)
    public void handleGreetings(@Payload Greeting greeting) {

        log.info("Il messaggio ricevuto e: {}", greeting);
        Greeting newGreeting = new Greeting(greeting.getId(),greeting.getMessage()+" MODIFIED", greeting
                .getTimestamp());
        greetingMongoRepository.save(newGreeting);
        log.info("Ho eseguito il salvataggio in <greetings> su MongoDB");
    }


    @StreamListener(GreetingStreams.INPUT_CORKINGLOGS)
    public void handleCorkingLogs(@Payload CorkingLog corkingLog) {

        log.info("Il corkinglog ricevuto e': {}", corkingLog );
        corkingLog.setInfo(corkingLog.getInfo()+" MODIFIED");
        corkingLogMongoRepository.save(corkingLog);
        log.info("Ho eseguito il salvataggio in <corkinglogs> su MongoDB");
    }
}
