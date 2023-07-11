package net.kravuar.vkoauth.components;

import lombok.NonNull;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;

public class DefaultVkPropsInsertingBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) {
        if (bean instanceof OAuth2ClientProperties oauthProps ) {
            var vkProvider = oauthProps.getProvider().get("vk");
            if (vkProvider == null) {
                vkProvider = new OAuth2ClientProperties.Provider();
                oauthProps.getProvider().put("vk", vkProvider);
            }

            // Won't be null because on Conditional on AutoConfiguration
            var vkRegistration = oauthProps.getRegistration().get("vk");

            if (vkProvider.getAuthorizationUri() == null)
                vkProvider.setAuthorizationUri(DefaultVkProviderProps.authorizationUri);
            if (vkProvider.getUserInfoUri() == null)
                vkProvider.setUserInfoUri(DefaultVkProviderProps.userInfoUri);
            if (vkProvider.getIssuerUri() == null)
                vkProvider.setIssuerUri(DefaultVkProviderProps.issuerUri);
            if (vkProvider.getTokenUri() == null)
                vkProvider.setTokenUri(DefaultVkProviderProps.tokenUri);
            if (vkProvider.getUserNameAttribute() == null)
                vkProvider.setUserNameAttribute(DefaultVkProviderProps.userNameAttribute);

            if (vkRegistration.getAuthorizationGrantType() == null)
                vkRegistration.setAuthorizationGrantType(DefaultVkRegistrationProps.authorizationGrantType);
            if (vkRegistration.getClientName() == null)
                vkRegistration.setClientName(DefaultVkRegistrationProps.clientName);
            if (vkRegistration.getClientAuthenticationMethod() == null)
                vkRegistration.setClientAuthenticationMethod(DefaultVkRegistrationProps.clientAuthenticationMethod);
        }
        return bean;
    }
}

