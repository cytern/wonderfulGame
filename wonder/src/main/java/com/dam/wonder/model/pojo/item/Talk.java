package com.dam.wonder.model.pojo.item;

import com.dam.wonder.model.config.constant.Constant;
import com.dam.wonder.model.pojo.choose.TalkChoose;
import lombok.Data;

import java.util.List;

@Data
public class Talk extends Item{
    /**
     * 情绪类型
     */
    private Integer emotionType;
    /**
     * 言语
     */
    private String word;
    /**
     * 持有者姓名
     */
    private String humanCode;
    /**
     * 触发着id
     */
    private String targetHumanCode;

    /**
     * 总date
     */
    private Integer date;
    /**
     * 星期几
     */
    private Integer week;
    /**
     * 后续选择
     */
    private List<TalkChoose> talkChooses;
    /**
     * 重写类型
     */
    private Integer type = Constant.ItemCode.ITEM_TALK;
}
