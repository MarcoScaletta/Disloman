package schedulerservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import schedulerservice.scheduler.CassandraRequests;
import schedulerservice.scheduler.SmartShareRequests;

import javax.sql.DataSource;

@EnableScheduling
@SpringBootApplication
@Slf4j@Component
public class SchedulerServiceApplication {

	@Bean
	@Scope(value = "singleton")
	public HttpEntity<String> httpEntitySmartShare(){
		HttpHeaders headers = new HttpHeaders();
		headers.set("Access-token", "fgjyu8s6-1020-11e9-ab14-d663bd873d93");
		return new HttpEntity<>(null, headers);
	}

	@Bean
	@Scope(value = "singleton")
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	@Scope(value = "singleton")
	public String smartshareAddress(){
		return "http://192.168.120.30:9000";
	}

	@Bean
	@Scope(value = "singleton")
	public String cassandraAPIServiceAddress(){
		return "http://cassandra-api:8080";
	}

    @Bean
    @Scope(value = "singleton")
    public String tappatriceRecordsTableName(){
        return "records_tappatrice";
    }

    @Bean
    @Scope(value = "singleton")
    public String astucciatriceRecordsTableName(){
        return "records_astucciatrice";
    }

    @Bean
    @Scope(value = "singleton")
    public String etichettatriceRecordsTableName(){
        return "records_etichettatrice";
    }

	@Bean
	@Scope(value = "singleton")
	public SmartShareRequests smartShareRequests() {
		return new SmartShareRequests();
	}

	@Bean
	@Scope(value = "singleton")
    public CassandraRequests cassandraRequests() {
		return new CassandraRequests();
	}

	@Bean
    @Scope(value = "singleton")
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceBuilder.url(
                "jdbc:sqlserver://;serverName=192.168.120.30;port=49718;databaseName=ORC_roh_ODM");
        dataSourceBuilder.username("Orchestra");
        dataSourceBuilder.password("Password2019");
        return dataSourceBuilder.build();
    }


	public static void main(String[] args) {
		SpringApplication.run(SchedulerServiceApplication.class, args);
	}

}

