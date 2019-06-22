package smarthingeapiservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@EnableScheduling
@SpringBootApplication
@Slf4j
@Component
public class SmartHingeAPIServiceApplication {

    private String tappatriceCode = "PRCS-AS-5";
    private String tappatriceOutputCode = "DIC11";

    @Bean
    @Scope
    public QueryMachine queryTappatrice(){
        return new QueryMachine(tappatriceCode, tappatriceOutputCode);
    }

    @Bean
    @Scope(value = "singleton")
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceBuilder.url(
                "jdbc:sqlserver://;serverName=192.168.120.30;port=1433;databaseName=ORC_roh_ODM");
        dataSourceBuilder.username("Orchestra");
        dataSourceBuilder.password("Password2019");
        return dataSourceBuilder.build();
    }


    public static void main(String[] args) {
        SpringApplication.run(SmartHingeAPIServiceApplication.class, args);
    }

}
