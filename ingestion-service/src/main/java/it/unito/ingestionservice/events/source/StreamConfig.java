package it.unito.ingestionservice.events.source;

import it.unito.ingestionservice.events.GreetingStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(GreetingStreams.class)
public class StreamConfig {
}
