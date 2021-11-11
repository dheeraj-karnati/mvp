package com.ekin.mvp.s3.client;

public interface S3Copy {

    boolean copyFileFromSourceToDestination(String sourceKey, String destinationKey) throws Exception ;
}
