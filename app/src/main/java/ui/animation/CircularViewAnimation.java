package ui.animation;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;

public class CircularViewAnimation extends Animation {

    private float startAngle = 90;
    private View animatingView;
    private View rotationAnchorView;
    private float cx, cy;           // center x,y position of circular path
    private float prevX, prevY;     // previous x,y position of image during animation
    private float r;                // radius of circle
    private float prevDx, prevDy;
    private int width;

    private AnimationListener animationListener;

    private OnTransformationListener onTransformationListener;
    private boolean isRadiusConstant;

    public OnTransformationListener getOnTransformationListener() {
        return onTransformationListener;
    }

    public void setOnTransformationListener(OnTransformationListener onTransformationListener) {
        this.onTransformationListener = onTransformationListener;
    }

    public AnimationListener getMyAnimationListener() {
        return animationListener;
    }

    public void setMyAnimationListener(AnimationListener animationListener) {
        this.animationListener = animationListener;
    }

    public interface OnTransformationListener {

        public void onTransform(float dx, float dy);
    }

    /**
     * @param rotationAnchorView       - View that will be animated
     * @param startAngle - startAngle
     */
    public CircularViewAnimation(View animatingView, View rotationAnchorView, float startAngle) {
        this.animatingView = animatingView;
        this.rotationAnchorView = rotationAnchorView;
        this.startAngle = startAngle;

        initThings();
    }

    private void initThings() {

        setAnimationListener(mAnimationListener);
    }

    AnimationListener mAnimationListener = new AnimationListener() {

        @Override
        public void onAnimationStart(Animation animation) {

            if(animationListener != null) {

                animationListener.onAnimationStart(animation);
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {

            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(animatingView.getWidth(), animatingView.getHeight());
            params2.leftMargin += getPrevDx() + rotationAnchorView.getLeft() + rotationAnchorView.getWidth()/2 - animatingView.getWidth()/2;
            params2.topMargin += getPrevDy() + rotationAnchorView.getTop() + rotationAnchorView.getHeight()/2 - animatingView.getHeight()/2;

            Log.d("params2.leftMargin", ""+params2.leftMargin);
            Log.d("params2.topMargin", ""+params2.topMargin);

            animatingView.setLayoutParams(params2);

            if(animationListener != null) {

                animationListener.onAnimationEnd(animation);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

            if(animationListener != null) {

                animationListener.onAnimationRepeat(animation);
            }
        }
    };

    @Override
    public boolean willChangeBounds() {
        return true;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {

        this.width = width;

        // calculate position of image center
        /*int cxImage = width / 2;
        int cyImage = height / 2;
        cx = view.getLeft() + cxImage;
        cy = view.getTop() + cyImage;*/

        cx = rotationAnchorView.getWidth() / 2.0f;
        cy = animatingView.getHeight() / 2.0f;

        if (r == 0) {

            r = cx;
        }

        // set previous position to center
        prevX = cx;
        prevY = cy;

    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        if (interpolatedTime == 0) {
            t.getMatrix().setTranslate(prevDx, prevDy);
            return;
        }

        float angleDeg = (interpolatedTime * 1800f + startAngle) % 1800f;
        float angleRad = (float) Math.toRadians(angleDeg);

        Log.d("CVA", "interpolatedTime : " + interpolatedTime);
        Log.d("CVA", "angleDeg : " + angleDeg);


        // r = radius, cx and cy = center point, a = angle (radians)
        float x = (float) (cx + r * Math.cos(angleRad));
        float y = (float) (cy + r * Math.sin(angleRad));

        if (!isRadiusConstant) {

            r = r - (r * interpolatedTime / 200.0f);
        }

        if (r < width / 2) {

            r = width / 2;
        }

        float dx = prevX - x;
        float dy = prevY - y;

        prevX = x;
        prevY = y;

        prevDx = dx;
        prevDy = dy;

        t.getMatrix().setTranslate(dx, dy);

        if (onTransformationListener != null) {

            onTransformationListener.onTransform(dx, dy);
        }

    }

    public float getPrevDx() {
        return prevDx;
    }

    public void setPrevDx(float prevDx) {
        this.prevDx = prevDx;
    }

    public float getPrevDy() {
        return prevDy;
    }

    public void setPrevDy(float prevDy) {
        this.prevDy = prevDy;
    }

    public boolean isRadiusConstant() {
        return isRadiusConstant;
    }

    public void setRadiusConstant(boolean radiusConstant) {
        isRadiusConstant = radiusConstant;
    }
}