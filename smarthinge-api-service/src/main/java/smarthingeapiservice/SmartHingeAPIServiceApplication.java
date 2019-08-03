package smarthingeapiservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@EnableScheduling
@SpringBootApplication
@Slf4j
@Component
public class SmartHingeAPIServiceApplication {

    private String bilanciaCode = "PRCS-AS-5";
    private String bilanciaOutputCode = "DIC11";

    private String incartonatriceCode = "PRCS-AS-5";
    private String incartonatriceOutputCode = "DIC15";

    private String tappatriceCode = "PRCS-AS-6";
    private String tappatriceOutputCode = "DIC01";

    private String etichettatriceCode = "PRCS-AS-7";
    private String etichettatriceOutputCode = "DIC05";


    @Bean
    @Scope(value = "singleton")
    public QueryMachine queryBilancia(){
        return new QueryMachine(bilanciaCode, "bilancia", bilanciaOutputCode);
    }

    @Bean
    @Scope(value = "singleton")
    public QueryMachine queryIncartonatrice(){
        return new QueryMachine(incartonatriceCode, "incartonatrice", incartonatriceOutputCode);
    }

    @Bean
    @Scope(value = "singleton")
    public QueryMachine queryTappatrice(){
        return new QueryMachine(tappatriceCode, "tappatrice", tappatriceOutputCode);
    }

    @Bean
    @Scope(value = "singleton")
    public QueryMachine queryEtichettatrice(){
        return new QueryMachine(etichettatriceCode, "etichettatrice", etichettatriceOutputCode);
    }


    @Bean
    @Scope(value = "singleton")
    public QueryPLD queryPLD(){
        return new QueryPLD();
    }

    public static void main(String[] args) {
        SpringApplication.run(SmartHingeAPIServiceApplication.class, args);
    }

}
