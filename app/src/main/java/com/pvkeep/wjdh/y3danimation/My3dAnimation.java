package com.pvkeep.wjdh.y3danimation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Admin on 2016/12/20.
 */
public class My3dAnimation extends Animation {

    private Camera mCamera;
    private final float mFromDegrees;
    private final float mToDegrees;
    private final float mCenterX;
    private final float mCenterY;

    public My3dAnimation(float fromDegrees, float toDegrees, float centerX, float centerY){
        mCenterX = centerX;
        mCenterY = centerY;
        mFromDegrees = fromDegrees;
        mToDegrees = toDegrees;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float degrees = mFromDegrees + ((mToDegrees - mFromDegrees) * interpolatedTime);
//        degrees = - degrees;
        if (interpolatedTime >= 0.5){
//            degrees += 180.f;
            //旋转从0 - +90度，然后从-90 - 0;从而从而产生旋转的感觉；
            degrees -= 180.f;
        }
        Log.i("luke", "degrees=" + degrees);
        final Matrix matrix = t.getMatrix();
        mCamera.save();
        mCamera.rotateY(degrees);
//        mCamera.rotateX(degrees);
        mCamera.getMatrix(matrix);
        mCamera.restore();

        matrix.preTranslate(-mCenterX, -mCenterY);
        matrix.postTranslate(mCenterX, mCenterY);
    }
}