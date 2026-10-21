package com.fleet.driver.api;

public class DriverNotFoundException extends RuntimeException {
    public DriverNotFoundException(String key) { super("No existe conductor para " + key + "."); }
}
