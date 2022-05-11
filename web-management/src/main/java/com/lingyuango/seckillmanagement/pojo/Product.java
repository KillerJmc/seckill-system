package com.lingyuango.seckillmanagement.pojo;

import com.lingyuango.seckillmanagement.model.BaseGmtModel;
import lombok.Getter;
import lombok.Setter;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.core.annotation.EruptDataSource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@EruptDataSource("mysql_seckill_service_db")
@Table(name = "sk_product")
@Erupt(name = "产品管理")
public class Product extends BaseGmtModel {

    /**
     * 商品id
     */
    @Column(name = "product_id")
    @EruptField(
            views = @View(title = "产品id"),
            edit = @Edit(title = "产品id", notNull = true, search = @Search)
    )
    private Integer productId;

    /**
     * 商品名称
     */
    @Column(name = "name")
    @EruptField(
            views = @View(title = "商品名", sortable = true),
            edit = @Edit(title = "商品名", notNull = true, search = @Search)
    )
    private String name;

    /**
     * 商品价格
     */
    @Column(name = "price")
    @EruptField(
            views = @View(title = "商品价格", sortable = true),
            edit = @Edit(title = "商品价格", notNull = true, search = @Search)
    )
    private double price;

    /**
     * 商品介绍
     */
    @Column(name = "info", length = 100)
    @EruptField(
            views = @View(title = "商品信息"),
            edit = @Edit(title = "商品信息", type = EditType.TEXTAREA, notNull = true)
    )
    private String info;
}
