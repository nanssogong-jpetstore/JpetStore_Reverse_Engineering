package org.mybatis.jpetstore.configuration;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;

import static org.mybatis.jpetstore.configuration.Constants.*;

public class AWSS3 {

    // Amazon-s3-sdk
    private AmazonS3 s3Client;
    final private String accessKey = ACCESS_KEY; // 액세스키
    final private String secretkey = SECRET_KEY; // 스크릿 엑세스 키
    private Regions clientRegion = REGION; // 한국
    private String bucket = BUCKET; // 버킷 명

    private AWSS3() {
        createS3Client();
    }

    // singleton 으로 구현
    static private AWSS3 instance = null;

    public static AWSS3 getInstance() {
        if (instance == null) {
            return new AWSS3();
        } else {
            return instance;
        }
    }

    // aws S3 client 생성
    private void createS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretkey);
        this.s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(clientRegion).build();
    }

    // upload 메서드 | 단일 파일 업로드!
    public void upload(File file, String key) {
        uploadToS3(new PutObjectRequest(this.bucket, key, file));
    }


    // PutObjectRequest는 Aws s3 버킷에 업로드할 객체 메타 데이터와 파일 데이터로 이루어져 있다.
    public void uploadToS3(PutObjectRequest putObjectRequest) {
        try {
            this.s3Client.putObject(putObjectRequest);
            System.out.println(String.format("[%s] upload complete", putObjectRequest.getKey()));
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
