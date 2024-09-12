package lshh.modules.apikey;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lshh.modules.clock.ClockManager;

import java.io.IOException;
import java.time.Clock;

public class ApiKeyFilter implements Filter {
    private final ClockManager clockManager;
    private final ApiKeySellerProvider apiKeySellerProvider;

    public ApiKeyFilter(ClockManager clockManager, ApiKeySellerProvider apiKeySellerProvider) {
        this.clockManager = clockManager;
        this.apiKeySellerProvider = apiKeySellerProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Clock clock = clockManager.getClock();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getRequestURI().contains("/swagger-ui/")
                || request.getRequestURI().contains("/v3/api-docs")
        ) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String apiKeyValue = request.getHeader("apikey");
        String sellerId = apiKeySellerProvider.getSellerId(apiKeyValue);
        ApiType apiType = ApiType.valueOf(request.getHeader("apiType"));

        ApiKey apikey = new ApiKey(
                apiKeyValue,
                sellerId,
                apiType
        );
        ApiKeyThreadHelper.setApiKey(apikey);

        try{
            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            ApiKeyThreadHelper.clear();
        }
    }
}