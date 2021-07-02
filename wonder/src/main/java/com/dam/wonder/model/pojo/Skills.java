package com.dam.wonder.model.pojo;

import lombok.Data;

@Data
public class Skills extends Item{
    /**
     * 等级
     */
    private Integer level;
    /**
     * 熟练度
     */
    private Integer proficiency;
}
