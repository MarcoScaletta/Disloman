package it.unito.cassandraapiservice.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "cassandraobjects")
@Data
public class CassandraObject {

    @PrimaryKey
    private final String id;

    private final String name;
    private final int age;

}
