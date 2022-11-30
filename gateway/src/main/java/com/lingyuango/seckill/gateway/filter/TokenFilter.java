package com.lingyuango.seckill.gateway.filter;

import com.jmc.net.R;
import com.lingyuango.seckill.gateway.common.Const;
import com.lingyuango.seckill.gateway.common.MsgMapping;
import com.lingyuango.seckill.gateway.service.TokenService;
import com.lingyuango.seckill.gateway.util.VoidMono;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenFilter implements GlobalFilter {
    private final TokenService tokenService;

    /**
     * 排除验证的URI
     */
    private static final List<String> EXCLUDE_URI = List.of(
            "/customer/login",
            "/customer/logout",
            "/customer/register"
    );

    /**
     * 判断是否为需要放行的URI
     * @param exchange 服务网络交换器
     * @return 是否需要放行
     */
    private boolean isExcludeURI(ServerWebExchange exchange) {
        // 请求的uri
        var uri = exchange.getRequest().getURI().getPath();
        log.info("获取到uri：{}", uri);

        return EXCLUDE_URI.contains(uri);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 放行排除的URI
        if (isExcludeURI(exchange)) {
            log.info("uri在排除列表中，放行");
            return chain.filter(exchange);
        }

        // 获取cookies
        var cookies = exchange.getRequest().getCookies();

        // 获取cookie中的token
        var token = getToken(cookies);
        log.info("获取到cookie：token -> {}", token.orElse(null));

        // 没有token拒绝请求
        if (token.isEmpty()) {
            log.warn("token不存在，拒绝请求");
            return VoidMono
                    .bind(exchange)
                    .withResponse(R.error(MsgMapping.NOT_LOGGED_ON));
        }

        // 获取cookie中的账号
        var account = getAccount(cookies);
        log.info("获取到cookie：account -> {}", account);

        // 通过token获取实际的账号
        var actualAccount = tokenService.getAccount(token.get());

        // 如果账号无效拒绝请求
        if (!account.equals(actualAccount)) {
            log.warn("cookie中账号无效，拒绝请求");
            return VoidMono
                    .bind(exchange)
                    .withResponse(R.error(MsgMapping.NOT_LOGGED_ON));
        }

        log.info("验证通过，放行");
        return chain.filter(exchange);
    }

    /**
     * 获取cookie中的token值
     * @param cookies 存放cookies的map对象
     * @return cookie中的token值
     */
    private Optional<String> getToken(MultiValueMap<String, HttpCookie> cookies) {
        // 获取token的cookie对象
        var tokenCookie = cookies.getFirst(Const.COOKIE_TOKEN_NAME);

        // 获取token
        return Optional.ofNullable(tokenCookie)
                .map(HttpCookie::getValue)
                .filter(tokenValue -> !tokenValue.isBlank());
    }

    /**
     * 获取cookie中的账号的值
     * @param cookies 存放cookies的map对象
     * @return cookie中的账号值
     */
    private Integer getAccount(MultiValueMap<String, HttpCookie> cookies) {
        // 获取账号的cookie对象
        var accountCookie = cookies.getFirst(Const.COOKIE_ACCOUNT_NAME);

        // 获取账号
        return Optional.ofNullable(accountCookie)
                .map(HttpCookie::getValue)
                .map(Integer::parseInt)
                .orElse(-1);
    }
}
