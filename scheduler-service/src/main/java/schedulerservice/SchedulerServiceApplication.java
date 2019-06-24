package schedulerservice;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import schedulerservice.requestsapi.CassandraRequests;
import schedulerservice.requestsapi.SmartShareRequests;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "schedulerservice")
@Slf4j@Component
public class SchedulerServiceApplication {

	@Bean
	@Scope(value = "singleton")
	public HttpEntity<String> httpEntitySmartShare(){
		HttpHeaders headers = new HttpHeaders();
		headers.set("Access-token",
				"");
		return new HttpEntity<>(null, headers);
	}

	@Bean
	public RestTemplate restTemplate(){
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

		SSLContext sslContext = null;
		try {
			sslContext = org.apache.http.ssl.SSLContexts.custom()
					.loadTrustMaterial(null, acceptingTrustStrategy)
					.build();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}

		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

		CloseableHttpClient httpClient = HttpClients.custom()
				.setSSLSocketFactory(csf)
				.build();

		HttpComponentsClientHttpRequestFactory requestFactory =
				new HttpComponentsClientHttpRequestFactory();

		requestFactory.setHttpClient(httpClient);

		RestTemplate restTemplate = new RestTemplate(requestFactory);

		return restTemplate;
	}

	@Bean
	@Scope(value = "singleton")
	public String smartshareAPIAddress(){
		return "https://localhost:9000";
	}

	@Bean
	@Scope(value = "singleton")
	public String smartHingeAPIServiceAddress(){
		return "http://smarthinge-api:8081";
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
	public String incartonatriceRecordsTableName(){
		return "records_incartonatrice";
	}

    @Bean
    @Scope(value = "singleton")
    public String bilanciaRecordsTableName(){
        return "records_bilancia";
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

	public static void main(String[] args) {
		SpringApplication.run(SchedulerServiceApplication.class, args);
	}


}

