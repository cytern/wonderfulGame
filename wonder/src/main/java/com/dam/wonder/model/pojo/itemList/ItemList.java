package com.dam.wonder.model.pojo.itemList;

import com.dam.wonder.model.pojo.item.Item;
import com.dam.wonder.model.pojo.item.Talk;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 封装的item列表 提供 通过code值或者id值 获取item的方法
 */
@Data
@Slf4j
public class ItemList {
    /**
     * 首先是itemList
     */
    private List<Talk> itemList;
    /**
     * 类型
     */
    private Integer type;

    /**
     * 获取item 通过id
     */
    public Item getItem(Integer id) {
        List<Item> collect = itemList.stream().filter(t -> t.getId().equals(id)).collect(Collectors.toList());
        if (collect.size() > 1) {
            log.error("获取物品实例发生异常 重复的id键值");
        } else if (collect.size() < 1) {
            log.error("获取物品实例发生异常 无法获取到对应id的物品");
            return new Item();
        }
        return collect.get(0);
    }

    public Item getItem(String code) {
        List<Item> collect = itemList.stream().filter(t -> t.getCode().equals(code)).collect(Collectors.toList());
        if (collect.size() > 1) {
            log.error("获取物品实例发生异常 重复的id键值");
        } else if (collect.size() < 1) {
            log.error("获取物品实例发生异常 无法获取到对应id的物品");
            return new Item();
        }
        return collect.get(0);
    }

    public List<Item> getAllTypeItem(Integer type) {
        List<Item> collect = itemList.stream().filter(t -> t.getType().equals(type)).collect(Collectors.toList());
        if (collect.size() < 1) {
            log.error("获取物品实例发生异常 该类型的物品实际存在数为零");
        }
        return collect;
    }

}

