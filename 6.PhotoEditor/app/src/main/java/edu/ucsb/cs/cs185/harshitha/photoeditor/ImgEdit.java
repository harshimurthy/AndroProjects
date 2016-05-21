package edu.ucsb.cs.cs185.harshitha.photoeditor;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.almeros.android.multitouch.MoveGestureDetector;
import com.almeros.android.multitouch.RotateGestureDetector;
import com.almeros.android.multitouch.ShoveGestureDetector;

/**
 * Created by harshitha on 5/20/16.
 */
public class ImgEdit extends ImageView
{
        private Matrix mMatrix = new Matrix();
        private float mScaleFactor = 1f;
        private float mRotationDegrees = 0.f;
        private float mFocusX = 0.f;
        private float mFocusY = 0.f;
        private int mAlpha = 255;
        private int mImageHeight, mImageWidth;

        private ScaleGestureDetector mScaleDetector;
        private RotateGestureDetector mRotateDetector;
        private MoveGestureDetector mMoveDetector;
        private ShoveGestureDetector mShoveDetector;

        public ImgEdit(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @SuppressWarnings("deprecation")
        public void transformsForMe() {
            // Determine the center of the screen to center 'earth'
            mFocusX = getWidth() / 2f;
            mFocusY = getHeight() / 2f;

            // Set this class as touchListener to the ImageView

            // Determine dimensions of 'earth' image
            Drawable d = getDrawable();
            mImageHeight = d.getIntrinsicHeight();
            mImageWidth = d.getIntrinsicWidth();

            // View is scaled and translated by matrix, so scale and translate initially
            float scaledImageCenterX = (mImageWidth * mScaleFactor) / 2;
            float scaledImageCenterY = (mImageHeight * mScaleFactor) / 2;

            mMatrix.postScale(mScaleFactor, mScaleFactor);
            mMatrix.postTranslate(mFocusX - scaledImageCenterX, mFocusY - scaledImageCenterY);
            setScaleType(ScaleType.MATRIX);
            setImageMatrix(mMatrix);

            // Setup Gesture Detectors
            mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
            mRotateDetector = new RotateGestureDetector(getContext(), new RotateListener());
            mMoveDetector = new MoveGestureDetector(getContext(), new MoveListener());
            mShoveDetector = new ShoveGestureDetector(getContext(), new ShoveListener());
        }

        public boolean onTouchEvent(MotionEvent event) {
            mScaleDetector.onTouchEvent(event);
            mRotateDetector.onTouchEvent(event);
            mMoveDetector.onTouchEvent(event);
            mShoveDetector.onTouchEvent(event);

            float scaledImageCenterX = (mImageWidth * mScaleFactor) / 2;
            float scaledImageCenterY = (mImageHeight * mScaleFactor) / 2;

            mMatrix.reset();
            mMatrix.postScale(mScaleFactor, mScaleFactor);
            mMatrix.postRotate(mRotationDegrees, scaledImageCenterX, scaledImageCenterY);
            //mMatrix.postTranslate(mFocusX - scaledImageCenterX, mFocusY - scaledImageCenterY);
            mMatrix.postTranslate(mFocusX, mFocusY);

            ImageView view = this;
            view.setImageMatrix(mMatrix);
            view.setAlpha(mAlpha);
            invalidate();
            return true; // indicate event was handled
        }

        private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                mScaleFactor *= detector.getScaleFactor(); // scale change since previous event

                // Don't let the object get too small or too large.
                mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));

                return true;
            }
        }

        private class RotateListener extends RotateGestureDetector.SimpleOnRotateGestureListener {
            @Override
            public boolean onRotate(RotateGestureDetector detector) {
                mRotationDegrees -= detector.getRotationDegreesDelta();
                return true;
            }
        }

        private class MoveListener extends MoveGestureDetector.SimpleOnMoveGestureListener {
            @Override
            public boolean onMove(MoveGestureDetector detector) {
                PointF d = detector.getFocusDelta();
                mFocusX += d.x;
                mFocusY += d.y;

                // mFocusX = detector.getFocusX();
                // mFocusY = detector.getFocusY();
                return true;
            }
        }

        private class ShoveListener extends ShoveGestureDetector.SimpleOnShoveGestureListener {
            @Override
            public boolean onShove(ShoveGestureDetector detector) {
                mAlpha += detector.getShovePixelsDelta();
                if (mAlpha > 255)
                    mAlpha = 255;
                else if (mAlpha < 0)
                    mAlpha = 0;

                return true;
            }
        }



    }


