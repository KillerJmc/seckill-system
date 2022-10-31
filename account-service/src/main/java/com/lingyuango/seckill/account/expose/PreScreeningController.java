package com.lingyuango.seckill.account.expose;

import com.jmc.net.R;
import com.lingyuango.seckill.account.service.PreScreeningService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jmc
 */
@RestController("preScreeningExposeController")
@RequiredArgsConstructor
@RequestMapping("/preScreening")
@Slf4j
public class PreScreeningController {
    private final PreScreeningService preScreeningService;

    @PostMapping("/insert")
    R<Void> insert(Integer customerId) {
        return preScreeningService.insert(customerId) == 1 ?
                R.ok().build() : R.error().build();
    }
}
