package com.gleb.zemskoi.adverts.config;

import com.gleb.zemskoi.adverts.container.PostgresqlContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class ContainersEnvironment {
    @Container
    public static PostgresqlContainer postgreSQLContainer = PostgresqlContainer.getInstance();
}