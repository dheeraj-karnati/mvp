package com.ekin.mvp.s3.util;

import org.apache.commons.lang3.StringUtils;

public class S3FileUtil {

    private S3FileUtil(){


    }

    public static String getS3KeyFromPrefixAndFileName(String prefix, String fileName) throws Exception {
        if(StringUtils.isNotBlank(prefix) && StringUtils.isNotBlank(fileName)){
            if(prefix.charAt(prefix.length()-1) != '/'){
                return prefix+'/'+fileName;
            }
            return prefix+fileName;
        }
        throw new Exception("Prefix or filename should not be empty");

    }

}
