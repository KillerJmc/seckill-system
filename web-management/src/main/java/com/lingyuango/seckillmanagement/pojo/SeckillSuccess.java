package com.lingyuango.seckillmanagement.pojo;

import com.lingyuango.seckillmanagement.handler.DataSourceBasedSqlChoiceFetchHandler;
import com.lingyuango.seckillmanagement.model.BaseGmtModel;
import lombok.Getter;
import lombok.Setter;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.Power;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.ChoiceType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.core.annotation.EruptDataSource;
import xyz.erupt.upms.handler.SqlChoiceFetchHandler;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@EruptDataSource("mysql_seckill_payment_db")
@Table(name = "sk_seckill_success")
@Erupt(name = "秒杀成功客户表", power = @Power(importable = true, export = true, add = false))
public class SeckillSuccess extends BaseGmtModel {

    /**
     * 秒杀活动id
     */
    @Column(name = "seckill_id")
    @EruptField(
            views = @View(title = "秒杀活动id",sortable = true),
            edit = @Edit(
                    search = @Search,
                    title = "秒杀活动id",
                    type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            fetchHandler = DataSourceBasedSqlChoiceFetchHandler.class,
                            fetchHandlerParams = {
                                    "mysql_seckill_service_db",
                                    "select seckill_id from seckill_service_db.sk_seckill_activity",
                                    "5000"
                            }
                    )
            )
    )
    private Integer seckillId;

    /**
     * 顾客账号
     */
    @Column(name = "account_id")
    @EruptField(
            views = @View(title = "账号"),
            edit = @Edit(title = "账号", search = @Search, notNull = true)
    )
    private Integer accountId;

    /**
     * 顾客姓名
     */
    @Column(name = "order_id", length = 30)
    @EruptField(
            views = @View(title = "订单号"),
            edit = @Edit(title = "订单号", search = @Search, notNull = true)
    )
    private String orderId;
}
