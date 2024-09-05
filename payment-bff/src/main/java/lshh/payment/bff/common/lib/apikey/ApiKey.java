package lshh.payment.bff.common.lib.apikey;

public record ApiKey(
        String apiKey,
        String sellerId,
        ApiType apiType
) {
}
