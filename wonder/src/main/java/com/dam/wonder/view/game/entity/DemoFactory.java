package com.dam.wonder.view.game.entity;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ExpireCleanComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.dam.wonder.model.pojo.type.ItemType;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class DemoFactory implements EntityFactory {
    @Spawns("explosion")
    public Entity newHappy(SpawnData data) {
        var emitter = ParticleEmitters.newFireEmitter(350);
        emitter.setMaxEmissions(10);
        emitter.setSize(3, 20);
        emitter.setStartColor(Color.SADDLEBROWN);
        emitter.setEndColor(Color.RED);
        emitter.setSpawnPointFunction(i -> new Point2D(128, 128));

        Entity view = FXGL.entityBuilder().view(FXGL.texture("explosion.png").toAnimatedTexture(16, Duration.seconds(1)).play())
                .type(ItemType.BOOM_ANIMATION)
                .with(new ExpireCleanComponent(Duration.seconds(1)))
                .with(new ParticleComponent(emitter))
                .build();
        return view;
    }

    public Entity newPlane(SpawnData spawnData) {
        FXGL.entityBuilder(spawnData).view(FXGL.texture("player.png"))
                .type(ItemType.USER_PLANE)

    }
}
