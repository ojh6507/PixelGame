package kr.ac.tukorea.ge.spg.ojh.framework.objects;

import android.view.MotionEvent;

import kr.ac.tukorea.ge.spg.ojh.framework.interfaces.ITouchable;
import kr.ac.tukorea.ge.spg.ojh.framework.view.Metrics;

public class Button extends Sprite implements ITouchable {
    public enum Action {
        pressed, released,
    }
    public interface Callback {
        public boolean onTouch(Action action);
    }
    private static final String TAG = Button.class.getSimpleName();
    private final Callback callback;
    private boolean processedDown;
    public Button(int bitmapResId, float cx, float cy, float width, float height, Callback callback) {
        super(bitmapResId, cx, cy, width, height);
        this.callback = callback;
    }
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float[] pts = Metrics.fromScreen(e.getX(), e.getY());
        if (!dstRect.contains(pts[0], pts[1])) {
            return false;
        }
        //Log.d(TAG, "Button.onTouch(" + System.identityHashCode(this) + ", " + e.getAction() + ", " + e.getX() + ", " + e.getY());
        int action = e.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            processedDown = callback.onTouch(Action.pressed);
        } else if (action == MotionEvent.ACTION_UP && processedDown) {
            callback.onTouch(Action.released);
        }
        return true;
    }
}
