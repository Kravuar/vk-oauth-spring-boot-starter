package net.kravuar.vkoauth.components;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;

import java.util.Map;

public class TokenResponsePrefixInsertingConverter implements Converter<Map<String, Object>, OAuth2AccessTokenResponse> {
    private static final OAuth2AccessToken.TokenType prefix = OAuth2AccessToken.TokenType.BEARER;

    @Override
    public OAuth2AccessTokenResponse convert(Map<String, Object> tokenResponseParameters) {
        // Surely nothing will happen if I modify this map
        String accessToken = (String) tokenResponseParameters.remove(OAuth2ParameterNames.ACCESS_TOKEN);

        return OAuth2AccessTokenResponse.withToken(accessToken)
                .tokenType(prefix)
                .additionalParameters(tokenResponseParameters)
                .build();
    }
}