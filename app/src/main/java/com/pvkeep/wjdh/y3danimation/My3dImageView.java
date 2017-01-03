package com.pvkeep.wjdh.y3danimation;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;

/**
 * Created by Admin on 2016/12/20.
 */
public class My3dImageView extends ImageView implements View.OnClickListener, Animation.AnimationListener {

    private Drawable drawable1;
    private Drawable drawable2;
    private FlipAnimator animator;

    public My3dImageView(Context context){
        super(context);
        init(context);
    }

    public My3dImageView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }

    public My3dImageView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context){
        drawable1 = getDrawable();
        drawable2 = context.getResources().getDrawable(R.mipmap.dianzhan_zhengzaichuli);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    /**
     * Animation part All credits goes to coomar
     */
    public class FlipAnimator extends Animation {

        private Camera camera;
        private Drawable toDrawable;
        private float centerX;
        private float centerY;
        private boolean visibilitySwapped;

        public void setToDrawable(Drawable to) {
            toDrawable = to;
            visibilitySwapped = false;
        }

        public FlipAnimator() {
            setFillAfter(true);
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            camera = new Camera();
            this.centerX = width / 2;
            this.centerY = height / 2;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            // Angle around the y-axis of the rotation at the given time. It is
            // calculated both in radians and in the equivalent degrees.
            final double radians = Math.PI * interpolatedTime;
            float degrees = (float) (180.0 * radians / Math.PI);

//            degrees = -degrees;

            // Once we reach the midpoint in the animation, we need to hide the
            // source view and show the destination view. We also need to change
            // the angle by 180 degrees so that the destination does not come in
            // flipped around. This is the main problem with SDK sample, it does not
            // do this.
            if (interpolatedTime >= 0.5f) {
//                degrees += 180.f;
                degrees -= 180.f;

                if (!visibilitySwapped) {
                    setImageDrawable(toDrawable);
                    visibilitySwapped = true;
                }
            }

            final Matrix matrix = t.getMatrix();

            camera.save();
            camera.translate(0.0f, 0.0f, (float) (150.0 * Math.sin(radians)));
            camera.rotateY(degrees);
            camera.getMatrix(matrix);
            camera.restore();

            matrix.preTranslate(-centerX, -centerY);
            matrix.postTranslate(centerX, centerY);
        }
    }
}
