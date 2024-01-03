package com.baeldung.testconainers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@Testable
public class PostgreSqlContainerLiveTest {

    @Rule
    public PostgreSQLContainer postgresContainer = new PostgreSQLContainer("postgres:16-alpine");

    @Test
    public void whenSelectQueryExecuted_thenResultsReturned() throws Exception {
        var result = performQuery(postgresContainer, "SELECT 1");
        assertEquals(1, result);
    }

    private Object performQuery(PostgreSQLContainer container, String query) throws SQLException {
        var jdbcUrl = container.getJdbcUrl();
        var username = container.getUsername();
        var password = container.getPassword();

        try (var connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            try (var stmt = connection.createStatement()) {
                try (var rs = stmt.executeQuery(query)) {
                    rs.next();
                    return rs.getObject(1);
                }
            }
        }
    }
}
