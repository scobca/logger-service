package org.scobca.loggerservice.config

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.ConnectionFactoryOptions.DATABASE
import io.r2dbc.spi.ConnectionFactoryOptions.DRIVER
import io.r2dbc.spi.ConnectionFactoryOptions.HOST
import io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD
import io.r2dbc.spi.ConnectionFactoryOptions.PORT
import io.r2dbc.spi.ConnectionFactoryOptions.USER
import io.r2dbc.spi.Option
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Configuration class for setting up reactive R2DBC ConnectionFactory
 * to connect to a Postgres database.
 *
 * This configuration builds the connection options dynamically using
 * application properties and creates a reactive [ConnectionFactory]
 * bean for database operations.
 *
 * @property databaseProperties The properties object containing database connection details.
 */
@Configuration
class DatabaseConfig(private val databaseProperties: DatabaseProperties) {

    /**
     * Creates a reactive [ConnectionFactory] bean configured for Postgres
     * using the application's database properties.
     *
     * @return a configured [ConnectionFactory] instance.
     */
    @Bean
    fun connectionFactory(): ConnectionFactory {
        val options = ConnectionFactoryOptions.builder()
            .option(DRIVER, "postgresql")
            .option(HOST, databaseProperties.host)
            .option(PORT, databaseProperties.port.toInt())
            .option(DATABASE, databaseProperties.name)
            .option(USER, databaseProperties.username)
            .option(PASSWORD, databaseProperties.password)
            .option(Option.valueOf("schema"), databaseProperties.schema)
            .build()

        return ConnectionFactories.get(options)
    }
}