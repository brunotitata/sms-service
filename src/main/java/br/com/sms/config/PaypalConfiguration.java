package br.com.sms.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;

@Configuration
public class PaypalConfiguration {

    @Value("${paypal.client.id}")
    private String paypalClientId;

    @Value("${paypal.secret}")
    private String paypalSecret;

    @Value("${paypal.mode}")
    private String paypalMode;

    @Bean
    public Map<String, String> paypalSdkConfig() {
	Map<String, String> config = new HashMap<String, String>();
	config.put("mode", paypalMode);
	return config;
    }

    @Bean
    public OAuthTokenCredential authTokenCredential() {
	return new OAuthTokenCredential(paypalClientId, paypalSecret, paypalSdkConfig());
    }

    @Bean
    public APIContext apiContext() {
	APIContext apiContext = new APIContext(paypalClientId, paypalSecret, paypalMode);
	apiContext.setConfigurationMap(paypalSdkConfig());
	return apiContext;
    }

}
