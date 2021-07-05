package com.dam.wonder.model.pojo.item;

import lombok.Data;
import java.util.List;

/**
 * 建筑 2021-7-4 19:29:02
 */
@Data
public class Building extends Item {
    /**
     * 主原材料
     */
    private Item mainInputItem;
    /**
     * 其余材料
     */
    private List<Item> otherInputItem;
    /**
     * 主要输出产品
     */
    private Item mainOutputItem;
    /**
     * 其余输出产品
     */
    private List<Item> otherOutputItem;
    /**
     * 建造需要材料
     */
    private List<Item> requireItem;
    /**
     * 生成需要用时
     */
    private Integer productRequireTime;
    /**
     * 高度
     */
    private Integer height;
    /**
     * 宽度
     */
    private Integer width;

}
