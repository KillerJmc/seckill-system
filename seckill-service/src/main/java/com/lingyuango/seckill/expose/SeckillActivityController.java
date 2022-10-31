package com.lingyuango.seckill.expose;

import com.jmc.net.R;
import com.lingyuango.seckill.common.MsgMapping;
import com.lingyuango.seckill.pojo.Product;
import com.lingyuango.seckill.pojo.SeckillActivityRule;
import com.lingyuango.seckill.service.SeckillActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jmc
 */
@RestController("seckillActivityExposeController")
@RequiredArgsConstructor
@RequestMapping("/seckillActivity")
@Slf4j
public class SeckillActivityController {
    private final SeckillActivityService seckillActivityService;

    /**
     * 获取最新规则
     */
    @PostMapping("/getRule")
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
    @PostMapping("/getProduct")
    public R<Product> getProduct() {
        var activity = seckillActivityService.getLatest();
        return R.ok(activity.getProduct());
    }

    /**
     * 获取最新的秒杀id
     */
    @PostMapping("/getSeckillId")
    public R<Integer> getSeckillId() {
        var seckillId = seckillActivityService.getLatestSeckillId();
        return seckillId == null ? R.error("Latest seckillId is null") : R.ok(seckillId);
    }
}
