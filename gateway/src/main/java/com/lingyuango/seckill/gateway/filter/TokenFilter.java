package com.lingyuango.seckill.gateway.filter;

import com.jmc.net.R;
import com.lingyuango.seckill.gateway.common.Const;
import com.lingyuango.seckill.gateway.util.VoidMono;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@Slf4j
public class TokenFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取token的cookie对象
        var tokenCookie = exchange
                .getRequest()
                .getCookies()
                .getFirst(Const.TOKEN_NAME);

        // 获取token
        var token = Optional.ofNullable(tokenCookie)
                .map(HttpCookie::getValue)
                .filter(tokenValue -> !tokenValue.isBlank())
                .orElse(null);

        log.info("获取到token：{}", token);

        // 如果token为空直接返回错误
        if (token == null) {
            return VoidMono
                    .bind(exchange)
                    .withResponse(R.error("草泥马"));
        }

        // token存在就放行
        return chain.filter(exchange);
    }

}
