package ui.animation;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CircularPropertyAnimation extends Animation {

    private float startAngle = 90;
    private View animatingView;
    private View rotationAnchorView;
    private float cx, cy;           // center x,y position of circular path
    private float prevX, prevY;     // previous x,y position of image during animation
    private float r;                // radius of circle
    private float prevDx, prevDy;
    private int width;

    private AnimationListener animationListener;

    private boolean isRadiusConstant = false;

    /**
     * @param rotationAnchorView - View that will be animated
     * @param startAngle         - startAngle
     */
    public CircularPropertyAnimation(View animatingView, View rotationAnchorView, float startAngle) {

        this.animatingView = animatingView;
        this.rotationAnchorView = rotationAnchorView;
        this.startAngle = startAngle;
    }


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
    protected void applyTransformation(float interpolatedTime, Transformation transformationX) {
        if (interpolatedTime == 0) {
            //t.getMatrix().setTranslate(prevDx, prevDy);
            animatingView.setTranslationX(prevDx);
            animatingView.setTranslationY(prevDy);
            return;
        }

        float angleDeg = (interpolatedTime * 1800f + startAngle) % 1800f;
        float angleRad = (float) Math.toRadians(angleDeg);

        Log.d("CPA", "interpolatedTime : " + interpolatedTime);
        Log.d("CPA", "angleDeg : " + angleDeg);


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

        //t.getMatrix().setTranslate(dx, dy);

        animatingView.setTranslationX(dx);
        animatingView.setTranslationY(dy);
    }
}