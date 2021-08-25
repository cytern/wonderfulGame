package com.dam.wonder.component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.image.ImageView;
import lombok.extern.slf4j.Slf4j;

/**
 * 为实体添加一个问号
 */
@Slf4j
public class WhyComponent extends Component {
    private Entity whyEntity;
    @Override
    public void onAdded() {
        log.info("开始添加问号");
        ImageView imageView = new ImageView("assets/ui/buttons/why.png");
        Texture  texture =  new Texture(imageView.getImage());
        log.info("问号生产");
        whyEntity = FXGL.entityBuilder(new SpawnData(entity.getX() + 16, entity.getY() - 40))
                .view(texture)
                .opacity(0)
                .scale(0.5,0.5)
                .build();
        log.info("问号加入世界");
        FXGL.getGameWorld().addEntity(whyEntity);

    }

    public void play() {
             whyEntity.setOpacity(1);
    }

    public void stop() {
        whyEntity.setOpacity(0);
    }
}
