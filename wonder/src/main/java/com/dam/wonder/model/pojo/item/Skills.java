package com.dam.wonder.model.pojo.item;

import com.dam.wonder.model.config.constant.Constant;
import lombok.Data;

@Data
public class Skills extends Item {
    /**
     * 等级
     */
    private Integer level;
    /**
     * 熟练度
     */
    private Integer proficiency;

    private Integer type = Constant.ItemCode.ITEM_SKILLS;
}
