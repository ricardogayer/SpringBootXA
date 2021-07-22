package com.mwave.Proposta.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.mwave.Proposta.postgresql.repository", entityManagerFactoryRef = "pgsqlEntityManagerFactory", transactionManagerRef = "pgsqlPlatformTransactionManager")

public class PostgreSQLConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "postgresql.datasource")
    public DataSourceProperties pgsqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource pgsqlDataSource(@Qualifier("pgsqlDataSourceProperties") DataSourceProperties pgsqlDataSourceProperties) {
        return pgsqlDataSourceProperties
                .initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean pgsqlEntityManagerFactory(@Qualifier("pgsqlDataSource") DataSource pgsqlDataSource, EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(pgsqlDataSource)
                .packages("com.mwave.Proposta.postgresql.entity")
                .persistenceUnit("pgsql")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager pgsqlPlatformTransactionManager(@Qualifier("pgsqlEntityManagerFactory") EntityManagerFactory pgsqlEntityManagerFactory) {
        return new JpaTransactionManager(pgsqlEntityManagerFactory);
    }

}