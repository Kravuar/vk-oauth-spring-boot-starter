package net.kravuar.vkoauth.components;

public class DefaultVkProviderProps {
    public static final String authorizationUri = "https://oauth.vk.com/authorize?revoke=1";
    public static final String tokenUri = "https://oauth.vk.com/access_token";
    public static final String userNameAttribute = "response";
    public static final String userInfoUri = "https://api.vk.com/method/users.get?v=5.131&fields=photo_max";
}
