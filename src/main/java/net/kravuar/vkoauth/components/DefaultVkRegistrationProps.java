package net.kravuar.vkoauth.components;

public class DefaultVkRegistrationProps {
    public static final String clientAuthenticationMethod = "client_secret_post";
    public static final String authorizationGrantType = "authorization_code";
    public static final String redirectUri = "{baseUrl}/{action}/oauth2/code/{registrationId}";
    public static final String clientName = "vk";
}
