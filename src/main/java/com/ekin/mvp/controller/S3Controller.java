package com.ekin.mvp.controller;


import com.ekin.mvp.s3.client.S3Copy;
import com.ekin.mvp.s3.client.S3Read;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ekin/s3")
public class S3Controller {


    private static final String WRITE_FILE = "app/export/files/lc/1.555.lc";
    private static final String COPY_FILE = "app/export/files/di/1.110102.di";

    @Autowired
    private S3Copy s3Copy;


    @Autowired
    private S3Read s3Read;

    @GetMapping("/s3copy")
    public boolean getS3CopyStatus(){
        boolean copyStatus = false;
        try{
            copyStatus = s3Copy.copyFileFromSourceToDestination(WRITE_FILE,COPY_FILE);

        }catch (Exception e){


        }
        return copyStatus;
    }

    @GetMapping("/s3read")
    public String getS3ReadStatus(){

        String readResponse = null;

        try {

            readResponse = s3Read.readFileContentsFromS3String(WRITE_FILE);

        }catch (Exception e){

        }
        return readResponse;

    }









}
