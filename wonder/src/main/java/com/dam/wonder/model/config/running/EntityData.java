package com.dam.wonder.model.config.running;

import com.almasb.fxgl.entity.Entity;
import com.dam.wonder.model.config.constant.Constant;
import com.dam.wonder.model.factory.ItemFactory;
import com.dam.wonder.model.pojo.item.Human;
import com.dam.wonder.model.pojo.item.Item;
import com.dam.wonder.model.pojo.item.Talk;
import com.dam.wonder.model.util.AssetsLoader;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@Slf4j
public class EntityData implements RunningData{
    @Autowired
    private  ItemFactory itemFactory;
    /**
     * 实体列表
     */
    private List<Item> itemList;

    /**
     * 加载初始数据
     */
    @Override
    public void init() {
        log.info("加载实体列表 加载对话");
        if (this.itemList == null) {
            this.itemList = new ArrayList<>();
        }
        try {
            List<Talk> talkList = AssetsLoader.loadItemAssets(Constant.assetsStatusDir.DIR_TALK, Talk.class);
            this.itemList.addAll(talkList);
        } catch (FileNotFoundException e) {
            log.error("对话加载失败",e);
        }
        log.info("加载实体列表 加载人物");

        try {
            List<Human> humanList = AssetsLoader.loadItemAssets(Constant.assetsStatusDir.DIR_HUMAN, Human.class);
            humanList.forEach(t -> {
                Entity human = itemFactory.constructorEntityAuto(t, "human");
                t.setEntity(human);
            });
            this.itemList.addAll(humanList);
        } catch (FileNotFoundException e) {
            log.error("人物加载失败",e);
        }

    }
    /**
     * 获取全部的任务
     */
    public List<Human> getAllHumans() {
        List<Item> itemList = this.itemList;
        ArrayList<Human> objects = new ArrayList<>();
        itemList.forEach(t -> {
            if (t.getType().equals(Constant.ItemCode.ITEM_HUMAN)) {
                Human tempHum =  (Human) t;
                objects.add(tempHum);
            }
        });
        return objects;
    }

    /**
     * 获取主角色
     */
    public Human getMainHumans() {
        List<Item> items = this.itemList;
        Human human = new Human();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(Constant.idCode.CODE_MAIN_HUMAN)){
                human = (Human) items.get(i);
            }
        }
        return human;
    }

}
