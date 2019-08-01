package it.unito.cassandraapiservice.controller;

import com.datastax.driver.core.policies.ConstantReconnectionPolicy;

public class ReconnectionPolicy extends ConstantReconnectionPolicy {
    public ReconnectionPolicy() {
        super(30000);
    }
}
