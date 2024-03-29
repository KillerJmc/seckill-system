package com.lingyuango.seckill.expose;

import com.jmc.net.R;
import com.lingyuango.seckill.service.SeckillApplicationFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/expose/seckillApplicationForm")
public class SeckillApplicationFormExposeController {
    private final SeckillApplicationFormService seckillApplicationFormService;

    /**
     * 判断某客户是否在申请表中
     */
    @GetMapping("/contains")
    public R<Boolean> contains(Integer account) {
        return R.ok(seckillApplicationFormService.contains(account));
    }
}
