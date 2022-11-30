package com.lingyuango.seckillmanagement.pojo;

import lombok.Getter;
import lombok.Setter;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
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
@EruptDataSource("mysql_seckill_payment_db")
@Table(name = "sk_storage")
@Erupt(name = "库存管理")
public class Storage {
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
     * 库存数量
     */
    @Column(name = "amount")
    @EruptField(
            views = @View(title = "库存数量", sortable = true),
            edit = @Edit(title = "库存数量", notNull = true, search = @Search)
    )
    private Integer amount;
}
