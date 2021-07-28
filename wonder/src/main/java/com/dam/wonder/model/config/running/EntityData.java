package com.dam.wonder.model.config.running;

import com.almasb.fxgl.entity.Entity;
import com.dam.wonder.model.config.constant.Constant;
import com.dam.wonder.model.factory.ItemFactory;
import com.dam.wonder.model.factory.RectangleFactory;
import com.dam.wonder.model.pojo.item.Human;
import com.dam.wonder.model.pojo.item.Talk;
import com.dam.wonder.model.util.AssetsLoader;
import javafx.scene.input.MouseButton;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Data
@Slf4j
public class EntityData implements RunningData{
    /**
     * 实体列表
     */
    private List<Human> humanList = new ArrayList<>();
    /**
     * 对话列表
     */
    private List<Talk> talkList = new ArrayList<>();

    /**
     * 加载初始数据
     */
    @Override
    public void init() {
        log.info("加载实体列表 加载对话");
        try {
            List<Talk> talkList = AssetsLoader.loadItemAssets(Constant.assetsStatusDir.DIR_TALK, Talk.class);
            this.talkList.addAll(talkList);
        } catch (FileNotFoundException e) {
            log.error("对话加载失败",e);
        }
        log.info("加载实体列表 加载人物");

        try {
            List<Human> humanList = AssetsLoader.loadItemAssets(Constant.assetsStatusDir.DIR_HUMAN, Human.class);
            humanList.forEach(t -> {
                /**
                 * 构建实体
                 */
                Entity human = ItemFactory.constructorEntityAuto(t, "human");
                human.getViewComponent().addOnClickHandler((event) -> {
                    //鼠标左键
                    if (event.getButton().equals(MouseButton.PRIMARY)) {
                         human.getViewComponent().addChild(Objects.requireNonNull(RectangleFactory.constructorRectangleAuto("heightLight", human.getHeight(), human.getHeight())));
                    }
                });
                t.setEntity(human);
            });
            this.humanList.addAll(humanList);
        } catch (FileNotFoundException e) {
            log.error("人物加载失败",e);
        }

    }
    /**
     * 获取全部的任务
     */
    public List<Human> getAllHumans() {
        return humanList;
    }

    /**
     * 获取主角色
     */
    public Human getMainHumans() {
        List<Human> items = this.humanList;
        Human human = new Human();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(Constant.idCode.CODE_MAIN_HUMAN)){
                human = (Human) items.get(i);
            }
        }
        return human;
    }

    public List<Talk> getAllTalks() {
           return  talkList;
    }

}
