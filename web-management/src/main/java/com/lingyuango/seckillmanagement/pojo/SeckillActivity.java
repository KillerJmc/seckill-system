package com.lingyuango.seckillmanagement.pojo;

import com.lingyuango.seckillmanagement.handler.DataSourceBasedSqlChoiceFetchHandler;
import com.lingyuango.seckillmanagement.model.BaseGmtModel;
import com.lingyuango.seckillmanagement.proxy.SeckillIdFillerProxy;
import lombok.Getter;
import lombok.Setter;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.PreDataProxy;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.ChoiceType;
import xyz.erupt.annotation.sub_field.sub_edit.DateType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.core.annotation.EruptDataSource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@EruptDataSource("mysql_seckill_service_db")
@Table(name = "sk_seckill_activity")
@Erupt(name = "活动管理")
@PreDataProxy(SeckillIdFillerProxy.class)
public class SeckillActivity extends BaseGmtModel {

    /**
     * 秒杀活动id
     */
    @Column(name = "seckill_id")
    @EruptField(views = @View(title = "秒杀活动id", sortable = true))
    private Integer seckillId;

    /**
     * 商品id
     */
    @Column(name = "product_id")
    @EruptField(
            views = @View(title = "产品id"),
            edit = @Edit(
                    search = @Search,
                    title = "产品id",
                    type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            fetchHandler = DataSourceBasedSqlChoiceFetchHandler.class,
                            fetchHandlerParams = {
                                    "mysql_seckill_service_db",
                                    "select product_id from seckill_service.sk_product",
                                    "5000"
                            }
                    )
            )
    )
    private Integer productId;

    /**
     * 活动中商品总量
     */
    @Column(name = "amount")
    @EruptField(
            views = @View(title = "库存数量", sortable = true),
            edit = @Edit(title = "库存数量", notNull = true, search = @Search)
    )
    private Integer amount;

    /**
     * 秒杀活动开始时间
     */
    @Column(name = "start_time")
    @EruptField(
            views = @View(title = "开始时间"),
            edit = @Edit(title = "开始时间", dateType = @DateType(type = DateType.Type.DATE_TIME))
    )
    private Date startTime;

    /**
     * 秒杀活动信息
     */
    @Column(name = "activity_info", length = 300)
    @EruptField(
            views = @View(title = "活动信息"),
            edit = @Edit(title = "活动信息", type = EditType.TEXTAREA, notNull = true)
    )
    private String activityInfo;


    /**
     * 秒杀活动规则id
     */
    @Column(name = "activity_rule_id")
    @EruptField(
            views = @View(title = "规则id"),
            edit = @Edit(
                    search = @Search,
                    title = "规则id",
                    type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            fetchHandler = DataSourceBasedSqlChoiceFetchHandler.class,
                            fetchHandlerParams = {
                                    "mysql_seckill_service_db",
                                    "select rule_id from seckill_service.sk_seckill_activity_rule",
                                    "5000"
                            }
                    )
            )
    )
    private Integer activityRuleId;
}
