package com.dam.wonder.model.factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.dam.wonder.model.pojo.item.Human;
import com.dam.wonder.model.pojo.item.Item;
import com.dam.wonder.view.game.component.MoveComponent;
import org.springframework.stereotype.Component;

/**
 * 实体工厂
 */
@Component
public class ItemFactory implements EntityFactory {


    public ItemFactory() {

    }

    public  Entity constructorEntityAuto(Item item,String type) {
        switch (type){
            case "human": {
                Human human =(Human) item;
                MoveComponent moveComponent = new MoveComponent();
                moveComponent.setMaxSpeed(human.getSpeed());
                Entity build = FXGL.entityBuilder(new SpawnData(human.getPX(), human.getPY())).viewWithBBox(human.getTextureDir()).with(moveComponent).collidable().build();
                return build;
            }
            default:{
                return null;
            }
        }
    }


}
