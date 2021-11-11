package com.ekin.mvp.s3.client;

import com.amazonaws.services.s3.model.S3Object;

public interface S3Read {

    String readFileContentsFromS3String(String key) throws Exception;

    byte[] readFileContentsFromS3Bytes(String key) throws Exception;

    String readFileContentsFromS3String(String prefix, String fileName) throws Exception;

    byte[] readFileContentsFromS3Bytes(String prefix, String fileName) throws Exception;

    S3Object downloadObject(String key) throws Exception;

    S3Object downloadObject(String prefix, String fileName) throws Exception;
}
