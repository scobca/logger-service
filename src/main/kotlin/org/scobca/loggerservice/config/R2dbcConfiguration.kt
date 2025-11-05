package org.scobca.loggerservice.config

import org.scobca.loggerservice.io.converters.EventDescriptionReader
import org.scobca.loggerservice.io.converters.EventDescriptionWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.convert.CustomConversions
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
import org.springframework.data.r2dbc.dialect.PostgresDialect
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

/**
 * Configuration for custom R2DBC conversions.
 *
 * This class declares custom converters for reading and writing
 * [org.scobca.loggerservice.dto.EventDescriptionDto] objects in the reactive PostgreSQL database context.
 * It configures Spring Data R2DBC to use these converters along with
 * the PostgreSQL dialect.
 *
 * The `@EnableR2dbcRepositories` annotation enables reactive repository support.
 */
@Configuration
@EnableR2dbcRepositories
class R2dbcConfiguration {

    /**
     * Defines custom R2DBC conversions including [EventDescriptionReader] and [EventDescriptionWriter].
     *
     * These converters are registered along with the default PostgreSQL conversions
     * to enable proper mapping of complex types to database columns and vice versa.
     *
     * @return configured [R2dbcCustomConversions] instance with custom converters.
     */
    @Bean
    fun r2dbcCustomConversions(): R2dbcCustomConversions {
        val dialect = PostgresDialect()
        val storeConversions = CustomConversions.StoreConversions.of(dialect.simpleTypeHolder)

        val converters = listOf(
            EventDescriptionReader(),
            EventDescriptionWriter()
        )

        return R2dbcCustomConversions(storeConversions, converters)
    }
}