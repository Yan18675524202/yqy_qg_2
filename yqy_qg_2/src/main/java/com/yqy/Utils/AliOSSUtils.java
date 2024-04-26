package com.yqy.Utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyuncs.exceptions.ClientException;
import jakarta.servlet.http.Part;

import java.io.*;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */

public class AliOSSUtils {

        private String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        private String bucketName = "yqy-qg-2-image";

        //实现上传图片到OSS
    public String upload(Part file) throws IOException, ClientException {

        // 避免文件覆盖
        String originalFilename = file.getName();
        String fileName = UUID.randomUUID().toString() +".jpeg";

        //上传文件到 OSS

        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        OSS ossClient = new OSSClientBuilder().build(endpoint,credentialsProvider);
        ossClient.putObject(bucketName, fileName, file.getInputStream());

        //文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }


}
