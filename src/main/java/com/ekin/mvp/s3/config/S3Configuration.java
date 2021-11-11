package com.ekin.mvp.s3.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "ekin.sdk.s3", ignoreUnknownFields = false)
@Validated
public class S3Configuration {

    @NotNull
    private String region;

    @NotNull
    private String bucketName;

    private String kmsKeyId;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getKmsKeyId() {
        return kmsKeyId;
    }

    public void setKmsKeyId(String kmsKeyId) {
        this.kmsKeyId = kmsKeyId;
    }
}
