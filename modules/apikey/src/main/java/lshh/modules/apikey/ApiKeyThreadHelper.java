package lshh.modules.apikey;

public class ApiKeyThreadHelper {
    private static final ThreadLocal<ApiKey> API_KEY = new ThreadLocal<>();

    public static void setApiKey(ApiKey apiKey) {
        API_KEY.set(apiKey);
    }
    public static ApiKey getApiKey() {
        return API_KEY.get();
    }
    public static void clear() {
        API_KEY.remove();
    }
}
