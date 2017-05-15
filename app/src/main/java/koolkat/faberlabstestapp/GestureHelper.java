package koolkat.faberlabstestapp;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Admin on 5/12/2017.
 */

public class GestureHelper implements View.OnTouchListener {

    //Class used to manage swipe events on views

    private final GestureDetector mGestureDetector;

    public GestureHelper(Context context) {
        mGestureDetector = new GestureDetector(context, new GestureListener(this));
    }

    public void onSwipeRight() {
    };

    public void onSwipeLeft() {
    };

    public void onSwipeTop() {
    };

    public void onSwipeBottom() {
    };

    public void onDoubleTap() {
    };

    public void onClick() {
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private static final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        //Minimum swipe distance
        private static final int SWIPE_THRESHOLD = 100;
        //Minimum swipe velocity
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
        private GestureHelper mHelper;

        public GestureListener(GestureHelper helper) {
            mHelper = helper;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            mHelper.onClick();
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            mHelper.onDoubleTap();
            return true;
        }

        //e1 is the point in the view where the user starts to swipe
        //e2 is the point in the view the user finishes the swipe action
        //so the swipe action happens between e1 and e2
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {

                        if (diffX > 0) {
                            //when the difference is positive
                            mHelper.onSwipeRight();
                        } else {
                            //when the difference is negative
                            mHelper.onSwipeLeft();
                        }
                    }
                } else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            //when the difference is positive
                            mHelper.onSwipeBottom();
                        } else {
                            //when the difference is negative
                            mHelper.onSwipeTop();
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return result;
        }
    }

}