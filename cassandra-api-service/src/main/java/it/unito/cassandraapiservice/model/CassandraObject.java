package it.unito.cassandraapiservice.model;

import com.datastax.driver.core.utils.UUIDs;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table(value = "cassandraobjects")
@Data
public class CassandraObject {

    @PrimaryKey
    @Id
    private final String id = UUIDs.timeBased().toString();

    private final String name;
    private final int age;

}
