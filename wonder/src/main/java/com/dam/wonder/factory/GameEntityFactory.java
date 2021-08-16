package com.dam.wonder.factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.dam.wonder.component.MoveComponent;
import com.dam.wonder.constant.EntityType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameEntityFactory implements EntityFactory {
    @Spawns("road")
    public Entity newRoad(SpawnData spawnData) {
        return FXGL.entityBuilder(spawnData).type(EntityType.ROAD).build();
    }
    @Spawns("building")
    public Entity newBuilding(SpawnData spawnData) {
        return FXGL.entityBuilder(spawnData).type(EntityType.BUILDING).build();
    }

    @Spawns("bg")
    public Entity newBg(SpawnData spawnData) {
        VBox vBox =  new VBox(10);
        HBox hBox = new HBox(10);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle  rectangle = new Rectangle(100, 100, Color.RED);
                rectangle.setStroke(Color.BLUE);
                rectangle.setStrokeWidth(1.75);
                rectangle.setOpacity(0.5);
                hBox.getChildren().add(rectangle);
            }
        }
        return FXGL.entityBuilder(spawnData).viewWithBBox(hBox).build();
    }

    @Spawns("wall")
    public Entity newWall(SpawnData data) {
        Entity build = FXGL
                .entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))) )
                .collidable()
                .with(new PhysicsComponent())
                .build();
        return build;
    }

    @Spawns("player")
    public Entity player(SpawnData data) {
        Entity entity = FXGL.entityBuilder().with(new MoveComponent())
                .collidable()
                .build();
        entity.setType(EntityType.PLANE);
        entity.translateX(500);
        entity.translateY(500);

        return entity;
    }

}
