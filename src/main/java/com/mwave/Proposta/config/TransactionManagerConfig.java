package com.mwave.Proposta.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TransactionManagerConfig {

    @Bean(name = "chainedTransactionManager")
    public ChainedTransactionManager transactionManager (
            @Qualifier("pgsqlPlatformTransactionManager") PlatformTransactionManager pgsqlTransactionManager,
            @Qualifier("oracleTransactionManager") PlatformTransactionManager oracleTransactionManager) {
        return new ChainedTransactionManager(oracleTransactionManager, pgsqlTransactionManager);
    }
}

