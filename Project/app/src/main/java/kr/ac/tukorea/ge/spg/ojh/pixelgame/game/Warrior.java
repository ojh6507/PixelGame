package kr.ac.tukorea.ge.spg.ojh.pixelgame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spg.ojh.framework.objects.AnimSprite;
import kr.ac.tukorea.ge.spg.ojh.framework.res.Sound;
import kr.ac.tukorea.ge.spg.ojh.pixelgame.R;
import kr.ac.tukorea.ge.spg.ojh.framework.objects.Sprite;
import kr.ac.tukorea.ge.spg.ojh.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spg.ojh.framework.scene.Scene;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;

public class Warrior extends AnimSprite {
    private boolean changed;
    public enum State {
        idle, attack, hitted
    }
    private static final float WARRIOR_WIDTH = 3.f;
    private static final float WARRIOR_HEIGHT = WARRIOR_WIDTH;
    private static final float WARRIOR_ATTACK_MOTION_FRAME = 2;
    private static final float WARRIOR_ATTACK_MOTION_END_FRAME = 6;
    private static final float  WARRIOR_HITTED_MOTION_FRAME = 4;
    private static final float FIRE_INTERVAL = 0.93f;

    public static final float ANIM_FPS = 8.f;
    public static final float ANIM_IDLE_FPS = 5.f;
    public static final float ANIM_HITTED_FPS = 4.f;
    private boolean attaked = false;

    protected State state;

    private float fireCoolTime = FIRE_INTERVAL;
    private static final int[] resIds = {
            R.mipmap.warrior_idle_animsheet,R.mipmap.warrior_animsheet,R.mipmap.gethitted
    };
    public Warrior() {
        super(resIds[0],ANIM_IDLE_FPS);
        state = State.idle;
        setPosition(WARRIOR_WIDTH/2,WARRIOR_HEIGHT/2.3f , WARRIOR_WIDTH, WARRIOR_HEIGHT);
    }

    @Override
    public void update(float elapsedSeconds) {
        if(GameStateManager.getInstance().GetisPause())return;
        switch (state) {
            case attack:
                ChangeAnimSprite(resIds[1],ANIM_FPS);
            if (frameIndex==WARRIOR_ATTACK_MOTION_FRAME && !attaked) {
                Sound.playEffect(R.raw.slash);
                Slash();
                attaked = true;

            }
            if(frameIndex == WARRIOR_ATTACK_MOTION_END_FRAME) {
                state = State.idle;
                attaked = false;
                break;
            }
            if(frameIndex!=WARRIOR_ATTACK_MOTION_FRAME) attaked = false;

            break;
            case idle:
                ChangeAnimSprite(resIds[0],ANIM_IDLE_FPS);
                break;
            case hitted:
                ChangeAnimSprite(resIds[2],ANIM_HITTED_FPS);
                if(frameIndex >=WARRIOR_HITTED_MOTION_FRAME){
                    Sound.playEffect(R.raw.puch);
                    state = State.idle;
                }
                break;
        }
    }

    private void Slash() {

        Scene.top().add(MainScene.Layer.slash, SwordStrike.get(x-WARRIOR_WIDTH/4.f, y ));
    }
    public  void ChangeState(State s){
        if(!changed){
           anim_PlayTime =  System.currentTimeMillis();
            state = s;
           changed = true;
        }
    }
    public void ChangeHittedState(){
        state= State.hitted;
    }
    public void SetAvailableChangeState(){
        changed = false;
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

    }

}

