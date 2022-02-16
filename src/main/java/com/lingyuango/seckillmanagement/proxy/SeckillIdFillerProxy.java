package com.lingyuango.seckillmanagement.proxy;

import com.jmc.util.Rand;
import com.lingyuango.seckillmanagement.pojo.SeckillActivity;
import xyz.erupt.annotation.fun.DataProxy;

import java.util.Random;

public class SeckillIdFillerProxy implements DataProxy<SeckillActivity> {
    @Override
    public void beforeAdd(SeckillActivity seckillActivity) {
        // 设置秒杀id为随机的int
        seckillActivity.setSeckillId(Rand.nextInt(100000000, Integer.MAX_VALUE - 1));
    }
}
