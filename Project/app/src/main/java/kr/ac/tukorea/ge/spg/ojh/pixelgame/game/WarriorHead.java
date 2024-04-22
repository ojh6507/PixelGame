package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;

public class WarriorHead extends Sprite {
    private static final float HEAD_WIDTH = 1.75f;
    private static final float HEAD_HEIGHT = HEAD_WIDTH * 80 / 72;
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
    private Bitmap targetBmp;
    private float fireCoolTime = FIRE_INTERVAL;

    public WarriorHead() {
        super(R.mipmap.rightface);
        setPosition(Metrics.width / 2, Metrics.height - 3, HEAD_WIDTH, HEAD_HEIGHT);
        setTargetX(x);

        targetBmp = BitmapPool.get(R.mipmap.rightface);
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
            setPosition(adjx, y, HEAD_WIDTH, HEAD_HEIGHT);
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
        if (dx != 0) {
            canvas.drawBitmap(targetBmp, null, targetRect, null);
        }
        super.draw(canvas);

    }

    private void setTargetX(float x) {
        targetX = Math.max(radius, Math.min(x, Metrics.width - radius));
        targetRect.set(
                targetX - TARGET_RADIUS, y - TARGET_RADIUS,
                targetX + TARGET_RADIUS, y + TARGET_RADIUS
        );
    }

    public boolean onTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float[] pts = Metrics.fromScreen(event.getX(), event.getY());
                setTargetX(pts[0]);
                return true;
        }
        return false;
    }
}
