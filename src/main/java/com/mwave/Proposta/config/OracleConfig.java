package com.mwave.Proposta.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.mwave.Proposta.oracle.repository", entityManagerFactoryRef = "oracleEntityManagerFactory", transactionManagerRef = "oracleTransactionManager")
public class OracleConfig {

    @Bean
    @ConfigurationProperties(prefix = "oracle.datasource")
    public DataSourceProperties oracleDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean
    public DataSource oracleDataSource(@Qualifier("oracleDataSourceProperties") DataSourceProperties pgsqlDataSourceProperties) {
        return pgsqlDataSourceProperties
                .initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory(@Qualifier("oracleDataSource") DataSource pgsqlDataSource, EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(pgsqlDataSource)
                .packages("com.mwave.Proposta.oracle.entity")
                .persistenceUnit("oracle")
                .build();
    }

    @Bean
    public PlatformTransactionManager oracleTransactionManager(@Qualifier("oracleEntityManagerFactory") EntityManagerFactory pgsqlEntityManagerFactory) {
        return new JpaTransactionManager(pgsqlEntityManagerFactory);
    }

}