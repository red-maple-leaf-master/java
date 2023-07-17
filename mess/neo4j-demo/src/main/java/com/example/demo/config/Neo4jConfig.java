package com.example.demo.config;


import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.neo4j.ogm.config.Configuration.Builder;

@Configuration
@EnableNeo4jRepositories("com.example.demo.repository") // 声明neo4j repository存放地址
public class Neo4jConfig {

    @Value("${spring.data.neo4j.uri}")
    private String uri;
    @Value("${spring.data.neo4j.username}")
    private String userName;
    @Value("${spring.data.neo4j.password}")
    private String password;

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        return new Builder()
                .uri(uri)
                .connectionPoolSize(100)
                .credentials(userName, password)
                .withBasePackages("com.example.demo.repository")
                .build();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory(getConfiguration());
    }
//
//    @Bean("neo4jTransaction")
//    public Neo4jTransactionManager neo4jTransactionManager(SessionFactory sessionFactory) {
//        return new Neo4jTransactionManager(sessionFactory);
//    }

}



