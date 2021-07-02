package com.dam.wonder.model.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Human extends Item{
    /**
     * 拥有的技能
     */
    private List<Skills> skills;
    /**
     * 拥有的关系
     */
    private List<RelationShip> relationShips;
    /**
     * 装备
     */
    private List<Equipment> equipments;
    /**
     * 攻击
     */
    private Integer attack;
    /**
     * 防御
     */
    private Integer defence;
    /**
     * 生命值
     */
    private Integer health;
    /**
     * 体力
     */
    private Integer strength;
    /**
     * 移速
     */
    private Integer speed;

}
