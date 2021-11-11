package com.ekin.mvp.s3.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.retry.RetryPolicy;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Objects;

@Configuration
public class S3ClientConfiguration {

    @Autowired
    private S3Configuration s3Configuration;

    @Primary
    @Bean
    public AmazonS3 amazonS3(){

        return AmazonS3ClientBuilder.standard()
                .withAccelerateModeEnabled(false)
                .withClientConfiguration(clientConfiguration())
                .withRegion(s3Configuration.getRegion()).build();
    }

    private ClientConfiguration clientConfiguration() {

        ClientConfiguration clientConfiguration = new ClientConfiguration().withMaxConnections(1)
                .withConnectionTimeout(2000)
                .withClientExecutionTimeout(6000);

        clientConfiguration.setRetryPolicy(new RetryPolicy(RetryPolicy.RetryCondition.NO_RETRY_CONDITION, RetryPolicy.BackoffStrategy.NO_DELAY, 10, true));

        return clientConfiguration;
    }
}
