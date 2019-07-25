package it.unito.cassandraapiservice;

import it.unito.cassandraapiservice.model.machinepersistentoperations.BilanciaOperations;
import it.unito.cassandraapiservice.model.machinepersistentoperations.EtichettatriceOperations;
import it.unito.cassandraapiservice.model.machinepersistentoperations.IncartonatriceOperations;
import it.unito.cassandraapiservice.model.machinepersistentoperations.TappatriceOperations;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class CassandraApiServiceApplication{

	@Bean
	@Scope(value = "singleton")
	public TappatriceOperations tappatriceOperations(){
		return new TappatriceOperations();
	}

	@Bean
	@Scope(value = "singleton")
	public IncartonatriceOperations incartonatriceOperations(){
		return new IncartonatriceOperations();
	}

	@Bean
	@Scope(value = "singleton")
	public EtichettatriceOperations etichettatriceOperations(){
		return new EtichettatriceOperations();
	}

	@Bean
	@Scope(value = "singleton")
	public BilanciaOperations  bilanciaOperations(){
		return new BilanciaOperations();
	}

	public static void main(String[] args) {
		SpringApplication.run(CassandraApiServiceApplication.class, args);
	}

}

