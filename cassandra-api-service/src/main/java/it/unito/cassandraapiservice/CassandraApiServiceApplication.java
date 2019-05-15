package it.unito.cassandraapiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableBinding(GreetingStreams.class)
@SpringBootApplication
public class CassandraApiServiceApplication
{
	public static void main(String[] args) {
		SpringApplication.run(CassandraApiServiceApplication.class, args);
	}

}

