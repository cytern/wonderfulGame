package com.dam.wonder.model.pojo;

import lombok.Data;

/**
 * 关系
 */
@Data
public class RelationShip extends Item{
    /**
     * 关系持有者id
     */
    private Integer ownerId;
    /**
     * 持有者姓名
     */
    private Integer ownerCode;
    /**
     * 作用着id
     */
    private Integer otherId;
    /**
     * 作用者姓名
     */
    private Integer otherCode;
    /**
     * 等级
     */
    private Integer level;
    /**
     * 熟练度
     */
    private Integer proficiency;
}
