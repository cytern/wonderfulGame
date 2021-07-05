package com.dam.wonder.model.pojo.item;

import com.dam.wonder.model.config.constant.Constant;
import lombok.Data;

/**
 * 地图
 */
@Data
public class Map extends Item{

    private Integer type = Constant.ItemCode.ITEM_MAP;
}
