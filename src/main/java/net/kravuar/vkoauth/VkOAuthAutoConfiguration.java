package net.kravuar.vkoauth;

import net.kravuar.vkoauth.components.DefaultVkPropsInsertingBeanPostProcessor;
import net.kravuar.vkoauth.components.TokenResponsePrefixInsertingConverter;
import net.kravuar.vkoauth.components.VKAdaptedOAuthUserService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationProvider;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@AutoConfiguration(before = WebMvcAutoConfiguration.class)
@ConditionalOnClass({HttpSecurity.class, OAuth2LoginAuthenticationProvider.class})
@ConditionalOnProperty(prefix = "spring.security.oauth2.client.registration.vk", value = "client-id")
public class VkOAuthAutoConfiguration {
    @Bean
    public DefaultVkPropsInsertingBeanPostProcessor vkPropsInsertingBeanPostProcessor() {
        return new DefaultVkPropsInsertingBeanPostProcessor();
    }

    @Bean
    @Scope("prototype")
    @Primary
    public HttpSecurity httpSecurity(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .oauth2Login(oauth2 -> oauth2
                        .tokenEndpoint(tokenEndpointConfig ->
                                tokenEndpointConfig.accessTokenResponseClient(accessTokenResponseClient())
                        )
                        .userInfoEndpoint(userInfoEndpointConfig ->
                                userInfoEndpointConfig.userService(new VKAdaptedOAuthUserService())
                        )
                );
    }

    private OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
        var tokenResponseHttpMessageConverter = new OAuth2AccessTokenResponseHttpMessageConverter();
        tokenResponseHttpMessageConverter.setAccessTokenResponseConverter(new TokenResponsePrefixInsertingConverter());

        var restTemplate = new RestTemplate(Arrays.asList(new FormHttpMessageConverter(), tokenResponseHttpMessageConverter));
        restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());

        var accessTokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
        accessTokenResponseClient.setRestOperations(restTemplate);

        return accessTokenResponseClient;
    }
}
