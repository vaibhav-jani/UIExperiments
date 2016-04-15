package ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RoundedImageView extends ImageView {

    public RoundedImageView(Context context) {

        super(context);
    }

    public RoundedImageView(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
    }

    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {

        Bitmap sbmp;
        if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        } else {
            sbmp = bmp;
        }
        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(),
                sbmp.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffa19774;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(sbmp.getWidth() / 2, sbmp.getHeight() / 2,
                sbmp.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);
        return output;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = b.copy(Config.ARGB_8888, true);

        int w = getWidth(), h = getHeight();

        Bitmap roundBitmap = getCroppedBitmap(bitmap, w);
        canvas.drawBitmap(roundBitmap, 0, 0, null);
    }
}

/*
        private int borderWidth = 0;
	    private int viewWidth;
	    private int viewHeight;
	    private Bitmap image;
	    private Paint paint;
	    private Paint paintBorder;
	    private BitmapShader shader;

	    public RoundedImageView(Context context)
	    {
	        super(context);
	        setup();
	    }

	    public RoundedImageView(Context context, AttributeSet attrs)
	    {
	        super(context, attrs);
	        setup();
	    }

	    public RoundedImageView(Context context, AttributeSet attrs, int defStyle)
	    {
	        super(context, attrs, defStyle);
	        setup();
	    }

	    @SuppressLint("NewApi")
	 private void setup()
	    {
	        // init paint
	        paint = new Paint();
	        paint.setAntiAlias(true);

	        paintBorder = new Paint();
	        setBorderColor(android.R.color.transparent);	        
	        this.setLayerType(LAYER_TYPE_SOFTWARE, paintBorder);
	      //  paintBorder.setShadowLayer(4.0f, 0.0f, 2.0f, Color.BLACK);
	    }

	    public void setBorderWidth(int borderWidth)
	    {
	        this.borderWidth = borderWidth;
	        this.invalidate();
	    }

	    public void setBorderColor(int borderColor)
	    {
	        if (paintBorder != null)
	            paintBorder.setColor(borderColor);

	        this.invalidate();
	    }

	    private void loadBitmap()
	    {
	        BitmapDrawable bitmapDrawable = (BitmapDrawable) this.getDrawable();

	        if (bitmapDrawable != null)
	            image = bitmapDrawable.getBitmap();
	    }

	    @Override
	    public void onDraw(Canvas canvas)
	    {
	        // load the bitmap
	        loadBitmap();


	        if (image != null)
	        {
	            shader = new BitmapShader(Bitmap.createScaledBitmap(image, canvas.getWidth(), canvas.getHeight(), false), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
	            paint.setShader(shader);
	            int circleCenter = viewWidth / 2;


	            canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter + borderWidth - 4.0f, paintBorder);
	            canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter - 4.0f, paint);
	        }
	    }

	    @Override
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	    {
	        int width = measureWidth(widthMeasureSpec);
	        int height = measureHeight(heightMeasureSpec, widthMeasureSpec);

	        viewWidth = width - (borderWidth * 2);
	        viewHeight = height - (borderWidth * 2);

	        setMeasuredDimension(width, height);
	    }

	    private int measureWidth(int measureSpec)
	    {
	        int result = 0;
	        int specMode = MeasureSpec.getMode(measureSpec);
	        int specSize = MeasureSpec.getSize(measureSpec);

	        if (specMode == MeasureSpec.EXACTLY)
	        {
	            // We were told how big to be
	            result = specSize;
	        }
	        else
	        {
	            // Measure the text
	            result = viewWidth;
	        }

	        return result;
	    }

	    private int measureHeight(int measureSpecHeight, int measureSpecWidth)
	    {
	        int result = 0;
	        int specMode = MeasureSpec.getMode(measureSpecHeight);
	        int specSize = MeasureSpec.getSize(measureSpecHeight);

	        if (specMode == MeasureSpec.EXACTLY)
	        {
	            result = specSize;
	        }
	        else
	        {
	            result = viewHeight;
	        }

	        return (result + 2);
	    }
 */