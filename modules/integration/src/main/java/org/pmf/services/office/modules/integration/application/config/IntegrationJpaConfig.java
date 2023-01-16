package org.pmf.services.office.modules.integration.application.config;

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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"org.pmf.services.office.modules.integration.domain"},
        entityManagerFactoryRef = "integrationRequestEntityManagerFactory",
        transactionManagerRef = "integrationRequestTransactionManager"
)
public class IntegrationJpaConfig {

    @Bean("integrationRequestDataSourceProperties")
    @ConfigurationProperties("integration.datasource")
    public DataSourceProperties integrationRequestDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean("integrationRequestDataSource")
    @ConfigurationProperties("integration.datasource")
    public DataSource integrationDataSource(@Qualifier("integrationRequestDataSourceProperties") DataSourceProperties integrationRequestDataSourceProperties) {
        return integrationRequestDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "integrationRequestTransactionManager")
    public JpaTransactionManager transactionManager(@Qualifier("integrationRequestEntityManagerFactory") EntityManagerFactory integrationEntityManager){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(integrationEntityManager);

        return transactionManager;
    }

    @Bean(name = "integrationRequestEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getIntegrationEntityManager(
            EntityManagerFactoryBuilder builder,
            @Qualifier("integrationRequestDataSource") DataSource integrationRequestDataSource){


        return builder
                .dataSource(integrationRequestDataSource)
                .packages("org.pmf.services.office.modules.integration.domain")
                .persistenceUnit("integrationRequest")
                .properties(additionalJpaProperties())
                .build();

    }
    Map<String,?> additionalJpaProperties(){
        Map<String,String> map = new HashMap<>();

//        map.put("hibernate.hbm2ddl.auto", "create");
        map.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        map.put("hibernate.show_sql", "true");

        return map;
    }
}
