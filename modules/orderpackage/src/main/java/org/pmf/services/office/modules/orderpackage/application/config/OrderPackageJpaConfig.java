package org.pmf.services.office.modules.orderpackage.application.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
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
        basePackages = {"org.pmf.services.office.modules.orderpackage.domain"},
        entityManagerFactoryRef = "mainRequestEntityManagerFactory",
        transactionManagerRef = "mainRequestTransactionManager",
        repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class
)
public class OrderPackageJpaConfig {

    @Bean("mainRequestDataSourceProperties")
    @Primary
    @ConfigurationProperties("order-package.datasource")
    public DataSourceProperties mainRequestDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean("mainRequestDataSource")
    @Primary
    @ConfigurationProperties("order-package.datasource")
    public DataSource mainDataSource(@Qualifier("mainRequestDataSourceProperties") DataSourceProperties mainRequestDataSourceProperties) {
        return mainRequestDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "mainRequestTransactionManager")
    @Primary
    public JpaTransactionManager transactionManager(@Qualifier("mainRequestEntityManagerFactory") EntityManagerFactory mainEntityManager){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mainEntityManager);

        return transactionManager;
    }

    @Bean(name = "mainRequestEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean getMainEntityManager(
            EntityManagerFactoryBuilder builder,
            @Qualifier("mainRequestDataSource") DataSource mainRequestDataSource){


        return builder
                .dataSource(mainRequestDataSource)
                .packages("org.pmf.services.office.modules.orderpackage.domain")
                .persistenceUnit("mainRequest")
                .properties(additionalJpaProperties())
                .build();

    }
    Map<String,?> additionalJpaProperties(){
        Map<String,String> map = new HashMap<>();
        map.put("org.hibernate.envers.audit_table_suffix", "_aud");
        map.put("hibernate.show_sql", "true");
        map.put("hibernate.implicit_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
        map.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");

        return map;
    }

}

