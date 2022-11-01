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

    @PostMapping("/insert")
    R<Void> insert(Integer customerId) {
        return preScreeningService.insert(customerId) == 1 ?
                R.ok().build() : R.error().build();
    }
}
