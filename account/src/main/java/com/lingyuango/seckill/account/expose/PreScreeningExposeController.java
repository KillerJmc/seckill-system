package com.lingyuango.seckill.account.expose;

import com.jmc.net.R;
import com.lingyuango.seckill.account.service.PreScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/expose/preScreening")
public class PreScreeningExposeController {
    private final PreScreeningService preScreeningService;

    /**
     * 插入初筛记录
     * @param account 账号
     */
    @PostMapping("/insert")
    R<Void> insert(Integer account) {
        return R.stream()
                .exec(() -> preScreeningService.insert(account))
                .build();
    }
}
