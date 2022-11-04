package com.lingyuango.seckill.account.expose;

import com.jmc.net.R;
import com.lingyuango.seckill.account.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expose/token")
public class TokenExposeController {
    private final TokenService tokenService;

    /**
     * 通过token获取账号id
     * @param token 登录凭证
     * @return 账号id
     */
    @GetMapping("/getAccount")
    R<Integer> getAccount(String token) {
        return R.ok(tokenService.getAccount(token));
    }
}
