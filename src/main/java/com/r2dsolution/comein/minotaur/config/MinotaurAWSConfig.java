package com.r2dsolution.comein.minotaur.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.r2dsolution.comein.minotaur.util.SecretManagerUtils;


@Configuration
@PropertySource("classpath:aws.properties")
public class MinotaurAWSConfig {
	
	@Value( "${comein.mode}" )
	public String mode;
	
	@Value( "${accessKey}" )
	public String accessKey;
	
	@Value( "${secretKey}" )
	public String secretKey;
	
	
	@Value( "${region}" )
	public String region;
	
	@Bean
    AWSCognitoIdentityProviderClientBuilder initAWSCognitoIdentityProviderClientBuilder(AWSSecretsManager secretManager) {
    	Map<String,String> awsSecrets = SecretManagerUtils.getSecret(secretManager, mode+"/cognito/comein");
    	String _accessKey = awsSecrets.get("accessKey");
    	String _secretKey = awsSecrets.get("secretKey");
    	//String _region = awsSecrets.get("region");
    	BasicAWSCredentials awsCreds = new BasicAWSCredentials(_accessKey,_secretKey);
    	return AWSCognitoIdentityProviderClientBuilder.standard()
    			.withCredentials(new AWSStaticCredentialsProvider(awsCreds));
//    			.withRegion(RegionsUtils.initRegions(region));
//				.withCredentials(new ClasspathPropertiesFileCredentialsProvider("aws.properties"));
//				 .withRegion(region);
    }
	
	 @Bean
	    public AWSCredentialsProvider initCredentialsProvider() {
	    	
	    	BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey,secretKey);
	    	
	    	
	    	return new AWSStaticCredentialsProvider(awsCreds);
	    }

	    
	    @Bean 
	    public AWSSecretsManager initAWSSecretsManager() {
	    	Regions region = Regions.AP_SOUTHEAST_1;
	    	 AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard()
						.withCredentials(initCredentialsProvider()) 
	                 .withRegion(region)
	                 .build();
	    	 return client;
	    }

}
