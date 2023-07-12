package com.example.fxgl;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * @author lqm
 * @date 2023/7/11 18:47
 */

public class MyGame extends GameApplication {

    private Entity player;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("My Game");
    }

    @Override
    protected void initInput() {
        // 添加输入控制
        FXGL.onKey(KeyCode.A, "Move Left", () -> player.translateX(-5));
        FXGL.onKey(KeyCode.D, "Move Right", () -> player.translateX(5));
        FXGL.onKey(KeyCode.W, "Jump", () -> player.translateY(-15));
        FXGL.onKey(KeyCode.SPACE, "Shoot", () -> shoot());
    }

    @Override
    protected void initGame() {
        // 初始化游戏
        FXGL.getGameScene().setBackgroundColor(Color.LIGHTBLUE);

        player = FXGL.entityBuilder()
                .at(400, 300)
                .view(new Rectangle(30, 30, Color.BLUE))
                .buildAndAttach();

        // 添加背景图片
//        FXGL.getGameScene().set(FXGL.texture("background.png"));

        // 创建和添加平台
        generatePlatforms();
    }

    @Override
    protected void initPhysics() {
        // 初始化物理
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(1, 1) {
            @Override
            protected void onCollisionBegin(Entity player, Entity platform) {
                // 处理碰撞逻辑
            }
        });
    }

    private void generatePlatforms() {
        for (int i = 0; i < 10; i++) {
            double x = Math.random() * (FXGL.getAppWidth() - 100);
            double y = Math.random() * (FXGL.getAppHeight() - 100);

            Entity platform = FXGL.entityBuilder()
                    .at(x, y)
                    .view(new Rectangle(100, 10, Color.DARKGREEN))
                    .buildAndAttach();
        }
    }

    private void shoot() {
        Entity bullet = FXGL.entityBuilder()
                .at(player.getCenter())
                .view(new Circle(5, Color.BLACK))
                .with(new ProjectileComponent(new Point2D(1, 0), 300))
                .buildAndAttach();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
