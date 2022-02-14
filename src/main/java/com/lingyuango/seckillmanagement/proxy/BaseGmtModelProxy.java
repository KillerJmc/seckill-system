package com.lingyuango.seckillmanagement.proxy;

import com.lingyuango.seckillmanagement.model.BaseGmtModel;
import xyz.erupt.annotation.fun.DataProxy;

import java.util.Date;

public class BaseGmtModelProxy implements DataProxy<BaseGmtModel> {
    @Override
    public void beforeAdd(BaseGmtModel hyperModel) {
        hyperModel.setGmtCreate(new Date());
        hyperModel.setGmtModified(new Date());
    }

    @Override
    public void beforeUpdate(BaseGmtModel hyperModel) {
        hyperModel.setGmtModified(new Date());
    }
}
