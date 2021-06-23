package com.gleb.zemskoi.testing.adverts.integration.config;

import com.gleb.zemskoi.testing.adverts.integration.container.PostgresqlContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class ContainersEnvironment {
    @Container
    public static PostgresqlContainer postgreSQLContainer = PostgresqlContainer.getInstance();
}
