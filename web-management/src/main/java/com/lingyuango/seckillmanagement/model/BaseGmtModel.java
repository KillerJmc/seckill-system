package com.lingyuango.seckillmanagement.model;

import com.lingyuango.seckillmanagement.proxy.BaseGmtModelProxy;
import lombok.Getter;
import lombok.Setter;
import xyz.erupt.annotation.PreDataProxy;
import xyz.erupt.annotation.config.SkipSerialize;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@PreDataProxy(BaseGmtModelProxy.class)
public class BaseGmtModel extends BaseModel {
    @SkipSerialize
    @Column(name = "gmt_create")
    private Date gmtCreate;

    @SkipSerialize
    @Column(name = "gmt_modified")
    private Date gmtModified;
}
