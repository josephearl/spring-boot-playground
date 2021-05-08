package uk.co.mo.springboot.playground;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.mapping.NamingStrategy;

import java.util.Optional;

// Disable force quoting so that column names in @Column and @MappedCollection are case-insensitive
// https://spring.io/blog/2020/05/20/migrating-to-spring-data-jdbc-2-0
@Configuration
@EnableJdbcRepositories
public class JdbcConfiguration extends AbstractJdbcConfiguration {
  @Bean
  @Override
  public JdbcMappingContext jdbcMappingContext(Optional<NamingStrategy> namingStrategy, JdbcCustomConversions customConversions) {
    JdbcMappingContext mappingContext = super.jdbcMappingContext(namingStrategy, customConversions);
    mappingContext.setForceQuote(false);
    return mappingContext;
  }
}
