package com.ekin.mvp.s3.client.impl;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.ekin.mvp.s3.client.S3Copy;
import com.ekin.mvp.s3.config.S3Configuration;
import org.springframework.beans.factory.annotation.Autowired;

public class S3CopyImpl implements S3Copy {

    @Autowired
    AmazonS3 amazonS3Client;

    @Autowired
    S3Configuration s3Configuration;

    public boolean copyFileFromSourceToDestination(String sourceKey, String destinationKey) throws Exception {
        try {
            CopyObjectRequest copyObjectRequest = new CopyObjectRequest(s3Configuration.getBucketName(), sourceKey, s3Configuration.getBucketName(), destinationKey);
            amazonS3Client.copyObject(copyObjectRequest);
        } catch (SdkClientException e) {
            throw new Exception(e);
        }
        return true;
    }
}
