package ui;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.vaibhav.uifactory.R;

/**
 * Created by vaibhav on 15/4/16.
 */
public class ProgressAnimator {

    private ProgressBar progressBar;

    private OnProgressCompleteListener progressCompleteListener;

    private boolean isWorking;

    public ProgressAnimator(ProgressBar progressBar) {

        this.progressBar = progressBar;
    }

    public synchronized void moveProgress(final int addProgress) {

        if (isWorking) {

            return;
        }

        isWorking = true;

        if (progressCompleteListener != null) {

            progressCompleteListener.onProgressStart();
        }

        int current = progressBar.getProgress();

        if (addProgress < 0) {

            Drawable drawable = progressBar.getResources().getDrawable(R.drawable.vertical_progress_bar_red);
            progressBar.setProgressDrawable(drawable);

        } else {

            Drawable drawable = progressBar.getResources().getDrawable(R.drawable.vertical_progress_bar_green);
            progressBar.setProgressDrawable(drawable);
        }

        final int next = current + addProgress;

        final Handler handler = new Handler();

        final long duration = (long) (40 * 10.0 / Math.abs(addProgress));

        Log.d("TAGV", "moveProgress: duration " + duration);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                int progress = progressBar.getProgress();

                if (progress != next) {

                    if (addProgress > 0) {

                        progressBar.setProgress(progress + 1);

                    } else {

                        progressBar.setProgress(progress - 1);

                    }

                    handler.postDelayed(this, duration);

                } else {

                    if (progressCompleteListener != null) {

                        progressCompleteListener.onProgressComplete();
                    }

                    resetDrawable();

                    isWorking = false;
                }
            }

        }, duration);
    }

    private void resetDrawable() {

        int progress = progressBar.getProgress();

        progressBar.setProgress(0);

        Drawable progressDrawable = progressBar.getResources().getDrawable(R.drawable.vertical_progress_bar);
        progressDrawable.setBounds(progressBar.getProgressDrawable().getBounds());
        progressBar.setProgressDrawable(progressDrawable);
        progressBar.setProgress(progress);
    }

    public OnProgressCompleteListener getProgressCompleteListener() {
        return progressCompleteListener;
    }

    public void setProgressCompleteListener(OnProgressCompleteListener progressCompleteListener) {
        this.progressCompleteListener = progressCompleteListener;
    }

    public interface OnProgressCompleteListener {

        void onProgressComplete();

        void onProgressStart();
    }
}
