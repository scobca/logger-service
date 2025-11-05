package org.scobca.loggerservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * Configuration properties for database connection.
 *
 * This class binds properties with prefix "database" from the application
 * configuration files (resources/application.yml)
 * to strongly typed Kotlin fields.
 *
 * Properties include connection details such as host, port, database name,
 * username, password, and schema.
 */
@Configuration
@ConfigurationProperties(prefix = "database")
class DatabaseProperties {

    /**
     * Hostname or IP address of the database server.
     */
    lateinit var host: String

    /**
     * Port number on which the database listens.
     */
    lateinit var port: String

    /**
     * Name of the database to connect to.
     */
    lateinit var name: String

    /**
     * Username for authenticating to the database.
     */
    lateinit var username: String

    /**
     * Password for authenticating to the database.
     */
    lateinit var password: String

    /**
     * Database schema to be used.
     */
    lateinit var schema: String
}