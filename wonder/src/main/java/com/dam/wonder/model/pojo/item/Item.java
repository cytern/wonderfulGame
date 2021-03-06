package com.dam.wonder.model.pojo.item;

import com.dam.wonder.model.config.constant.Constant;
import lombok.Data;
import org.lwjgl.system.CallbackI;

@Data
public class Item {
    /**
     * 物品id
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 物品码值 一般为物品的英文名
     */
    private String code;
    /**
     * 物品描述
     */
    private String describe;
    /**
     * 物品价格
     */
    private Integer price;
    /**
     * 物品数量
     */
    private Integer num;
    /**
     * 物品类型
     */
    private Integer type = Constant.ItemCode.ITEM_BASE;

    /**
     * x坐标
     */
    private Integer pX;
    /**
     * y坐标
     */
    private Integer pY;

}
