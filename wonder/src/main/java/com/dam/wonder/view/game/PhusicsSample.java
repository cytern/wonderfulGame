//package com.dam.wonder.view.game;
//
//import com.almasb.fxgl.app.GameApplication;
//import com.almasb.fxgl.app.GameSettings;
//import com.almasb.fxgl.dsl.FXGL;
//import com.almasb.fxgl.physics.BoundingShape;
//import com.almasb.fxgl.physics.HitBox;
//import com.dam.wonder.model.config.constant.Constant;
//import com.sun.javafx.scene.control.skin.FXVK;
//
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//
//public class PhusicsSample extends GameApplication {
//    @Override
//    protected void initSettings(GameSettings gameSettings) {
//        gameSettings.setWidth(Constant.GameWindow.WIDTH);
//        gameSettings.setHeight(Constant.GameWindow.HEIGHT);
//        gameSettings.setTitle(Constant.GameWindow.GAME_NAME);
//    }
//    @Override
//    protected void initGame() {
//        FXGL.entityBuilder()
//                .type(Type.PLAYER)
//                .at(100, 100)
//                // 1. define hit boxes manually
//                .bbox(new HitBox(BoundingShape.box(40, 40)))
//                .view(new Rectangle(40, 40, Color.BLUE))
//                // 2. make it collidable
//                .collidable()
//                // Note: in case you are copy-pasting, this class is in dev.DeveloperWASDControl
//                // and enables WASD movement for testing
//                .with(new DeveloperWASDControl())
//                .buildAndAttach();
//
//        FXGL.entityBuilder()
//                .type(FXVK.Type.ENEMY)
//                .at(200, 100)
//                // 1. OR let the view generate it from view data
//                .viewWithBBox(new Rectangle(40, 40, Color.RED))
//                // 2. make it collidable
//                .collidable()
//                .buildAndAttach();
//    }
//
//    @Override
//    protected void initPhysics() {
//        // the order of entities is determined by
//        // the order of their types passed into this method
//        FXGL.onCollision(Type.PLAYER, Type.ENEMY, (player, enemy) -> {
//            System.out.println("On Collision");
//        });
//
//        // the above call uses DSL
//        // if you need more fine-tuned control, see below
//
////        PhysicsWorld physics = FXGL.getPhysicsWorld();
////
////        physics.addCollisionHandler(new CollisionHandler(Type.PLAYER, Type.ENEMY) {
////            @Override
////            protected void onHitBoxTrigger(Entity player, Entity enemy, HitBox playerBox, HitBox enemyBox) {
////                System.out.println(playerBox.getName() + " X " + enemyBox.getName());
////            }
////
////            @Override
////            protected void onCollisionBegin(Entity player, Entity enemy) {
////                System.out.println("On Collision Begin");
////            }
////
////            @Override
////            protected void onCollision(Entity player, Entity enemy) {
////                System.out.println("On Collision");
////            }
////
////            @Override
////            protected void onCollisionEnd(Entity player, Entity enemy) {
////                System.out.println("On Collision End");
////            }
////        });
//    }
//
//}
