package com.gleb.zemskoi.testing.adverts.integration.container;


import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresqlContainer extends PostgreSQLContainer<PostgresqlContainer> {

    private static final String IMAGE_VERSION = "postgres:12.5";
    private static PostgresqlContainer container;

    private PostgresqlContainer() {
        super(IMAGE_VERSION);
    }

    public static PostgresqlContainer getInstance() {
        if (container == null) {
            container = new PostgresqlContainer().withInitScript("sql/init.sql");
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("POSTGRES_DB_URL", container.getJdbcUrl());
        System.setProperty("POSTGRES_DB_USERNAME", container.getUsername());
        System.setProperty("POSTGRES_DB_PASSWORD", container.getPassword());
    }
}
