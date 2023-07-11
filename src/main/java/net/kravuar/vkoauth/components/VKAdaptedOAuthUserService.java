package net.kravuar.vkoauth.components;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;

public class VKAdaptedOAuthUserService extends DefaultOAuth2UserService {
    private static final String userNameAttributeName = "user_id";

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        var user = super.loadUser(userRequest);
        if (userRequest.getClientRegistration().getRegistrationId().equals("vk"))
            user = vkAdapt(user);
        return user;
    }

    protected OAuth2User vkAdapt(OAuth2User user) {
        @SuppressWarnings("unchecked")
        var userAttributes = ((List<Map<String, Object>>) user.getAttributes().get("response")).get(0);

        return new DefaultOAuth2User(user.getAuthorities(), userAttributes, userNameAttributeName);
    }
}
