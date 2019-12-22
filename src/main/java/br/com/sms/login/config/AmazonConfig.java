package br.com.sms.login.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

//    @Value("${AWS_SES_ACCESS_KEY_ID}")
//    private String sesAccessKey;
//
//    @Value("${AWS_SES_SECRET_KEY}")
//    private String sesSecretKey;

//    @Bean
//    public AmazonSimpleEmailService getSESClient() {
//        return AmazonSimpleEmailServiceAsyncClientBuilder.standard()
//                .withCredentials(new AWSCredentialsProvider() {
//                    @Override
//                    public AWSCredentials getCredentials() {
//                        return new AWSCredentials() {
//                            @Override
//                            public String getAWSAccessKeyId() {
//                                return sesAccessKey;
//                            }
//
//                            @Override
//                            public String getAWSSecretKey() {
//                                return sesSecretKey;
//                            }
//                        };
//                    }
//
//                    @Override
//                    public void refresh() {
//                    }
//                }).withRegion(Regions.US_EAST_1).build();
//    }

}
