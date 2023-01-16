package org.pmf.services.office.modules.financial_request.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"org.pmf.services.office.modules.financial_request.domain"},
        entityManagerFactoryRef = "financialRequestEntityManagerFactory",
        transactionManagerRef = "financialRequestTransactionManager"
)
class FinancialRequestJpaConfig {

    @Bean("financialRequestDataSourceProperties")
    @ConfigurationProperties("financial-request.datasource")
    public DataSourceProperties financialRequestDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean("financialRequestDataSource")
    @ConfigurationProperties("financial-request.datasource")
    public DataSource serversDataSource(@Qualifier("financialRequestDataSourceProperties") DataSourceProperties financialRequestDataSourceProperties) {
        return financialRequestDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "financialRequestTransactionManager")
    public JpaTransactionManager transactionManager(@Qualifier("financialRequestEntityManagerFactory") EntityManagerFactory serversEntityManager){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(serversEntityManager);

        return transactionManager;
    }

    @Bean(name = "financialRequestEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getServersEntityManager(
            EntityManagerFactoryBuilder builder,
            @Qualifier("financialRequestDataSource") DataSource financialRequestDataSource){


        return builder
                .dataSource(financialRequestDataSource)
                .packages("org.pmf.services.office.modules.financial_request.domain")
                .persistenceUnit("financialRequest")
                .properties(additionalJpaProperties())
                .build();

    }
    Map<String,?> additionalJpaProperties(){
        Map<String,String> map = new HashMap<>();

//        map.put("hibernate.hbm2ddl.auto", "create");
        map.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        map.put("hibernate.show_sql", "true");
        map.put("hibernate.enable_lazy_load_no_trans", "true");

        return map;
    }
}
