package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.view.MotionEvent;

import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.HorizonBackground;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;

public class MainScene extends Scene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final WarriorHead warriorHead;
    private final Warrior warrior;

    //Score score; // package private

    public enum Layer {
        bg, board,item, up_player,under_player, enemy, slash, obstacle, controller, COUNT
    }
    public MainScene() {
        initLayers(Layer.COUNT);

        add(Layer.controller, new EnemyGenerator());
        add(Layer.controller, new CollisionChecker(this));

        add(Layer.bg, new HorizonBackground(R.mipmap.bg_forest));
        add(Layer.board,new Board(Layer.board));
        this.warriorHead = new WarriorHead();
        this.warrior = new Warrior();
        add(Layer.under_player, warriorHead);
        add(Layer.up_player, warrior);
        //this.score = new Score(R.mipmap.number_24x32, 8.5f, 0.5f, 0.6f);
        //score.setScore(0);
        //add(Layer.ui, score);
    }


    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        return warriorHead.onTouch(event);
    }
}
