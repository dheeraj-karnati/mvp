package com.ekin.mvp.s3.client.impl;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectAclRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.ekin.mvp.s3.client.S3Read;
import com.ekin.mvp.s3.config.S3Configuration;
import com.ekin.mvp.s3.util.S3FileUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class S3ReadImpl implements S3Read {


    @Autowired
    AmazonS3 amazonS3Client;

    @Autowired
    S3Configuration s3Configuration;

    @Override
    public String readFileContentsFromS3String(String key) throws Exception {

        String response = null;
        S3Object s3Object = downloadObject(key);
        try {
            if(Objects.nonNull(s3Object)){

                response = IOUtils.toString(s3Object.getObjectContent(), StandardCharsets.UTF_8.name());
                s3Object.close();
            }
        }catch (Exception e){
            throw new Exception(e);
        }
        return response;

    }

    @Override
    public byte[] readFileContentsFromS3Bytes(String key) throws Exception {
        byte[] response = null;
        S3Object s3Object = downloadObject(key);
        try {
            if(Objects.nonNull(s3Object)){

                response = IOUtils.toByteArray(s3Object.getObjectContent());
                s3Object.close();
            }
        }catch (Exception e){
            throw new Exception(e);
        }
        return response;
    }

    @Override
    public String readFileContentsFromS3String(String prefix, String fileName) throws Exception {
        String key = S3FileUtil.getS3KeyFromPrefixAndFileName(prefix,fileName);

        return readFileContentsFromS3String(key);
    }

    @Override
    public byte[] readFileContentsFromS3Bytes(String prefix, String fileName) throws Exception {
        String key = S3FileUtil.getS3KeyFromPrefixAndFileName(prefix,fileName);

        return readFileContentsFromS3Bytes(key);
    }

    @Override
    public S3Object downloadObject(String key) throws Exception {
        S3Object s3Object;

        try {
            GetObjectRequest getObjectRequest = new GetObjectRequest(s3Configuration.getBucketName(), key);
            s3Object = amazonS3Client.getObject(getObjectRequest);
        } catch (SdkClientException e){
            throw new Exception(e);
        }
        return s3Object;
    }

    @Override
    public S3Object downloadObject(String prefix, String fileName) throws Exception {
        String key = S3FileUtil.getS3KeyFromPrefixAndFileName(prefix,fileName);
        return downloadObject(key);
    }
}
