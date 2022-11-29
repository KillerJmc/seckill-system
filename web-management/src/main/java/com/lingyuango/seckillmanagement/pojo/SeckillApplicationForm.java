package com.lingyuango.seckillmanagement.pojo;

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
import xyz.erupt.toolkit.handler.SqlChoiceFetchHandler;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EruptDataSource("mysql_seckill_service_db")
@Table(name = "sk_seckill_application_form")
@Erupt(name = "客户申请表", power = @Power(importable = true, export = true))
public class SeckillApplicationForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EruptField
    private Integer id;

    /**
     * 秒杀活动id
     */
    @Column(name = "seckill_id")
    @EruptField(
            views = @View(title = "秒杀活动id", sortable = true),
            edit = @Edit(
                    search = @Search,
                    title = "秒杀活动id",
                    type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            fetchHandler = SqlChoiceFetchHandler.class,
                            fetchHandlerParams = "select seckill_id from seckill_service.sk_seckill_activity"
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

}
