package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spg.ojh.framework.objects.AnimSprite;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;

public class Warrior extends Sprite {
    private static final float WARRIOR_WIDTH = 1.75f;
    private static final float WARRIOR_HEIGHT = WARRIOR_WIDTH * 80 / 72;
    private static final float TARGET_RADIUS = 0.5f;
    private static final float SPEED = 5.0f;
    private static final float FIRE_INTERVAL = 0.25f;
    private static final float SPARK_DURATION = 0.1f;
    private static final float SPARK_WIDTH = 1.125f;
    private static final float SPARK_HEIGHT = SPARK_WIDTH * 3 / 5;
    private static final float SPARK_OFFSET = 0.66f;
    private static final float BULLET_OFFSET = 0.8f;


    private float targetX;
    private RectF targetRect = new RectF();
    private float fireCoolTime = FIRE_INTERVAL;

    public Warrior() {
        super(R.mipmap.rightface);
        setPosition(Metrics.width / 2, Metrics.height - 3, WARRIOR_WIDTH, WARRIOR_HEIGHT);
        setTargetX(x);
    }

    @Override
    public void update(float elapsedSeconds) {
        if (targetX < x) {
            dx = -SPEED;
        } else if (x < targetX) {
            dx = SPEED;
        } else {
            dx = 0;
        }
        super.update(elapsedSeconds);
        float adjx = x;
        if ((dx < 0 && x < targetX) || (dx > 0 && x > targetX)) {
            adjx = targetX;
        } else {
            adjx = Math.max(radius, Math.min(x, Metrics.width - radius));
        }
        if (adjx != x) {
            setPosition(adjx, y, WARRIOR_WIDTH, WARRIOR_HEIGHT);
            dx = 0;
        }
        fireCoolTime -= elapsedSeconds;
        if (fireCoolTime <= 0) {
            fireBullet();
            fireCoolTime = FIRE_INTERVAL;
        }
    }

    private void fireBullet() {
        Scene.top().add(MainScene.Layer.bullet, SwordStrike.get(x, y - BULLET_OFFSET));
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

    }

    private void setTargetX(float x) {

    }

    public boolean onTouch(MotionEvent event) {
        return false;
    }
}
