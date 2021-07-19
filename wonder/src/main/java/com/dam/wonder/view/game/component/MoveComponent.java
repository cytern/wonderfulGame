package com.dam.wonder.view.game.component;

import com.almasb.fxgl.entity.component.Component;
import com.dam.wonder.model.config.running.GameRunningData;

@org.springframework.stereotype.Component
public class MoveComponent extends Component {

    private final GameRunningData gameRunningData;

    public MoveComponent(GameRunningData gameRunningData) {
        this.gameRunningData = gameRunningData;
    }

    /**
     * Called after the component is added to entity.
     */
    @Override
    public void onAdded() {
        super.onAdded();
    }

    /**
     * Called each frame when not paused.
     *
     * @param tpf time per frame
     */
    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
    }

    public void up(){

    }
}
