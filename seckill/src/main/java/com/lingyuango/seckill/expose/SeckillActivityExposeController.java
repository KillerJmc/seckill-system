package com.lingyuango.seckill.expose;

import com.jmc.net.R;
import com.lingyuango.seckill.common.MsgMapping;
import com.lingyuango.seckill.pojo.Product;
import com.lingyuango.seckill.pojo.SeckillActivityRule;
import com.lingyuango.seckill.service.SeckillActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/expose/seckillActivity")
public class SeckillActivityExposeController {
    private final SeckillActivityService seckillActivityService;

    /**
     * 获取最新规则
     */
    @GetMapping("/getRule")
    public R<SeckillActivityRule> getRule() {
        SeckillActivityRule res;
        if ((res = seckillActivityService.getRule()) == null) {
            return R.error(MsgMapping.NO_RULES);
        } else {
            return R.ok(res);
        }
    }

    /**
     * 获取商品信息
     */
    @GetMapping("/getProduct")
    public R<Product> getProduct() {
        var activity = seckillActivityService.getLatest();
        return R.ok(activity.getProduct());
    }

    /**
     * 获取最新的秒杀id
     */
    @GetMapping("/getSeckillId")
    public R<Integer> getSeckillId() {
        var seckillId = seckillActivityService.getLatestSeckillId();
        return seckillId == null ? R.error("Latest seckillId is null") : R.ok(seckillId);
    }
}
