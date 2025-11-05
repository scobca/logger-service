package org.scobca.loggerservice.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

/**
 * Configuration class for Flyway database migrations.
 *
 * This class configures a [DataSource] using HikariCP connection pool based
 * on the provided [DatabaseProperties]. It also sets up Flyway to perform
 * database schema migrations on application startup.
 *
 * The Flyway bean triggers migration at startup and logs applied migrations.
 *
 * @property databaseProperties Configuration properties for database connection.
 */
@Configuration
class FlywayConfig(private val databaseProperties: DatabaseProperties) {

    /**
     * Configures and returns a HikariCP [DataSource] based on database properties.
     *
     * @return configured [DataSource] for database connections.
     */
    @Bean
    fun dataSource(): DataSource {
        val config = HikariConfig().apply {
            jdbcUrl =
                "jdbc:postgresql://${databaseProperties.host}:${databaseProperties.port}/${databaseProperties.name}"
            username = databaseProperties.username
            password = databaseProperties.password
            schema = databaseProperties.schema
        }
        return HikariDataSource(config)
    }

    /**
     * Configures Flyway with the provided [DataSource], runs migrations,
     * and logs migration details.
     *
     * @param dataSource the database connection source for Flyway.
     * @return configured and executed Flyway instance.
     */
    @Bean
    fun flyway(dataSource: DataSource): Flyway {
        val logger = LoggerFactory.getLogger(FlywayConfig::class.java)
        logger.info("Flyway data source: {}", dataSource.javaClass.simpleName)

        val flyway = Flyway.configure()
            .dataSource(dataSource)
            .baselineOnMigrate(true)
            .load()

        flyway.migrate()

        logger.debug("=== Applied migrations ===")
        for (info in flyway.info().applied()) {
            logger.debug("{} - {}", info.version, info.description)
        }

        return flyway
    }
}