
package com.tintim.webhook.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import java.net.URI;

@Configuration
public class AwsConfig {

    @Value("${AWS_REGION:sa-east-1}")
    private String region;

    @Value("${AWS_SQS_ENDPOINT:http://localstack:4566}")
    private String sqsEndpoint;

    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder()
                .region(Region.of(region))
                .endpointOverride(java.net.URI.create(sqsEndpoint))
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .build();
    }
}
