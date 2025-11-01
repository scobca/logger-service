package org.scobca.loggerservice.config

import org.scobca.loggerservice.io.converters.EventDescriptionReader
import org.scobca.loggerservice.io.converters.EventDescriptionWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.convert.CustomConversions
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
import org.springframework.data.r2dbc.dialect.PostgresDialect
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Configuration
@EnableR2dbcRepositories
class R2dbcConfiguration {
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