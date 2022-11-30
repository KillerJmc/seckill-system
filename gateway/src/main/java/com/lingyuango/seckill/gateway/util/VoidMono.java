package com.lingyuango.seckill.gateway.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmc.lang.Tries;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 带返回值的Mono&lt;Void&gt;对象
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VoidMono {
    /**
     * Json序列号对象
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * gateway服务网络交换机
     */
    @Setter
    private ServerWebExchange exchange;

    /**
     * 绑定gateway网络交换机，并返回示例
     * @param exchange gateway网络交换机
     * @return VoidMono实例
     */
    public static VoidMono bind(ServerWebExchange exchange) {
        return new VoidMono() {{ setExchange(exchange); }};
    }

    /**
     * 将返回值对象转换为json，并直接写入返回体，返回最终封装上述行为的Mono&lt;Void&gt;
     * @param value 返回值对象
     * @return Mono&lt;Void&gt;
     */
    public Mono<Void> withResponse(Object value){
        // 获取返回值对象的json字符串对应的字节数组
        var bytes = Tries.tryReturnsT(() -> objectMapper.writeValueAsBytes(value));

        // 获取gateway网络交换机里的response对象
        var resp = exchange.getResponse();

        // 构建存放返回字节数组的数据缓冲区
        var dataBuffer =  resp.bufferFactory().wrap(bytes);

        // 将返回字节数组的数据写入response的body的行为封装为最终的Mono<Void>并返回
        return resp.writeWith(Mono.just(dataBuffer));
    }
}
