package com.example.cheggexercise.service;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.example.cheggexercise.repository")
//@ComponentScan(basePackages = "com.example.cheggexercise.service")
public class CheggExerciseConfiguration {

    @Value("${elasticsearch.host:localhost}")
    private String elasticHost;

    @Value("${elasticsearch.port:9300}")
    private int elasticPort;

    @Value("${elasticsearch.clustername:docker-cluster}")
    private String elasticClusterName;

    @Bean
    public Client client() throws UnknownHostException {
        Settings elasticsearchSettings = Settings.builder()
                .put("client.transport.sniff", true)
//				.put("path.home", elasticsearchHome)
                .put("cluster.name", elasticClusterName).build();
        TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName(elasticHost), elasticPort));
        return client;
    }
    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }
}
