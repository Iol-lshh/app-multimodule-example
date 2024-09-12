package lshh.modules.apikey;

public record ApiKey(
        String apiKey,
        String sellerId,
        ApiType apiType
) {
}
