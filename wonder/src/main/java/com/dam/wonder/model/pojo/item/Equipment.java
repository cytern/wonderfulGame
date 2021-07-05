package com.dam.wonder.model.pojo.item;

import com.dam.wonder.model.config.constant.Constant;
import lombok.Data;

/**
 * 装备
 */
@Data
public class Equipment {
    /**
     * 攻击力
     */
    private Integer attack;
    /**
     * 防御力
     */
    private Integer defense;
    /**
     * 生命值
     */
    private Integer health;
    /**
     * 部位
     */
    private String part;

    private Integer type = Constant.ItemCode.ITEM_EQUIPMENT;
}
