package schedulerservice;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import schedulerservice.requestsapi.CassandraRequests;
import schedulerservice.requestsapi.SmartShareRequests;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import java.io.FileNotFoundException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.TimeZone;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "schedulerservice")
@Slf4j@Component
@PropertySource("classpath:properties.yml")
public class SchedulerServiceApplication {


	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Rome"));
		System.out.println("Spring boot application running in Europe/Rome timezone :"+new Date());
	}

	@Value(value = "${orchestra.token}")
	private String token;

	@Bean
	@Scope(value = "singleton")
	public HttpEntity<String> httpEntitySmartShare() throws FileNotFoundException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Access-token",token);
		return new HttpEntity<>(null, headers);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	@Bean
	public RestTemplate restTemplate(){

		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

		SSLContext sslContext = null;
		try {
			sslContext = org.apache.http.ssl.SSLContexts.custom()
					.loadTrustMaterial(null, acceptingTrustStrategy)
					.build();
		} catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
			e.printStackTrace();
		}

		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

		CloseableHttpClient httpClient = HttpClients.custom()
				.setSSLSocketFactory(csf)
				.build();

		HttpComponentsClientHttpRequestFactory requestFactory =
				new HttpComponentsClientHttpRequestFactory();

		requestFactory.setHttpClient(httpClient);


		return new RestTemplate(requestFactory);
	}

	@Bean
	@Scope(value = "singleton")
	public String smartshareAPIAddress(){
		return "https://192.168.120.30:9000";
	}

	@Bean
	@Scope(value = "singleton")
	public String smartHingeAPIServiceAddress(){
		return "http://smarthinge-api:8090";
	}

	@Bean
	@Scope(value = "singleton")
	public String cassandraAPIServiceAddress(){
		return "http://cassandra-api.dl.ssbprogetti.it";
	}

//	@Bean
//	@Scope(value = "singleton")
//	public String cassandraAPIServiceAddress(){
//		return "http://cassandra-api:8080";
//	}

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

	public static void main(String[] args) {
		SpringApplication.run(SchedulerServiceApplication.class, args);
	}


}

