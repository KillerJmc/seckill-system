package com.lingyuango.seckillmanagement.pojo;

import lombok.Getter;
import lombok.Setter;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.BoolType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.core.annotation.EruptDataSource;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EruptDataSource("mysql_seckill_service_db")
@Table(name = "sk_seckill_activity_rule")
@Erupt(name = "规则管理")
public class ActivityRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EruptField
    private Integer id;

    /**
     * 规则id
     */
    @Column(name = "rule_id")
    @EruptField(
            views = @View(title = "规则id", sortable = true),
            edit = @Edit(title = "规则id", notNull = true, search = @Search)
    )
    private Integer ruleId;

    /**
     * 规则名称
     */
    @Column(name = "rule_name")
    @EruptField(
            views = @View(title = "规则名", sortable = true),
            edit = @Edit(title = "规则名", notNull = true)
    )
    private String ruleName;

    /**
     * 工作状态（在岗或失业）
     */
    @Column(name = "work_status")
    @EruptField(
            edit = @Edit(title = "工作状态",
            boolType = @BoolType(trueText = "正常", falseText = "失业"))
    )
    private Boolean workStatus;

    /**
     * 最小年龄
     */
    @Column(name = "min_age")
    @EruptField(
            views = @View(title = "最小年龄"),
            edit = @Edit(title = "最小年龄")
    )
    private Integer minAge;

    /**
     * 最大年龄
     */
    @Column(name = "max_age")
    @EruptField(
            views = @View(title = "最大年龄"),
            edit = @Edit(title = "最大年龄")
    )
    private Integer maxAge;

    /**
     * 是否在失信人名单中
     */
    @Column(name = "in_credit_blacklist")
    @EruptField(
            edit = @Edit(title = "是否在失信人名单中",
            boolType = @BoolType(trueText = "是", falseText = "否")))
    private Boolean inCreditBlacklist;

    /**
     * 三年内最多逾期次数
     */
    @Column(name = "max_overdue_times")
    @EruptField(
            views = @View(title = "三年内最多逾期次数"),
            edit = @Edit(title = "三年内最多逾期次数")
    )
    private Integer maxOverdueTimes;

    /**
     * 三年内最多逾期天数
     */
    @Column(name = "max_overdue_days")
    @EruptField(
            views = @View(title = "三年内最多逾期天数"),
            edit = @Edit(title = "三年内最多逾期天数")
    )
    private Integer maxOverdueDays;

    /**
     * 三年内最多逾期金额
     */
    @Column(name = "max_overdue_money")
    @EruptField(
            views = @View(title = "三年内最多逾期金额"),
            edit = @Edit(title = "三年内最多逾期金额")
    )
    private double maxOverdueMoney;
}
