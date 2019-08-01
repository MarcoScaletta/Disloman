package it.unito.cassandraapiservice.controller;

public class ExceptionError {

    public static String noHostAvailable(){
        return "Cassandra is not available";
    }
}
