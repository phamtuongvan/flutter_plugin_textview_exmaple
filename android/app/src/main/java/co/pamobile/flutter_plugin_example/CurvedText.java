package co.pamobile.flutter_plugin_example;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.view.View;

public class CurvedText extends View {
    private Paint mPaint;
    private Paint mPaintBorder;

    private Path mPath;
    private Paint mPathPaint;

    private Typeface mFont;
    float superSetTextSize = 0;

    public String getTextOnPath() {
        return textOnPath;
    }

    public void setTextOnPath(String textOnPath, float textSize) {
        this.textOnPath = textOnPath;
        this.superSetTextSize = textSize;
    }

    public void setTextOnPath(String textOnPath) {
        this.textOnPath = textOnPath;
    }

    private String textOnPath = "Card Name";

    float scaleRatio = 1;

    private void makePath(Path p,float scaleRatio, String cardType) {
        switch (cardType){
            case "minion":
                p.moveTo(0, 5*scaleRatio);
                p.cubicTo(30*scaleRatio, 20*scaleRatio, 195*scaleRatio, -45*scaleRatio, 248*scaleRatio, 5*scaleRatio);//small
                break;
            case "spell":
                p.moveTo(0, 10*scaleRatio);
                p.cubicTo(62*scaleRatio, -20*scaleRatio, 186*scaleRatio, -20*scaleRatio, 248*scaleRatio, 10*scaleRatio);//small
                break;
            case "hero":
                p.moveTo(0, 15*scaleRatio);
                p.cubicTo(55*scaleRatio, -17*scaleRatio, 167*scaleRatio, -17*scaleRatio, 222*scaleRatio, 15*scaleRatio);//small
                break;
        }
    }


    public CurvedText(Context context, float scaleRatio, String cardType) {
        super(context);
        init(scaleRatio,cardType);
    }

    void init(float scaleRatio, String cardType){
        setFocusable(true);
        this.scaleRatio = scaleRatio;





        //allocate resource, avoid allocate in onDraw\
        mPaintBorder = new Paint();

        mPaint = new Paint();

        mPath = new Path();
        makePath(mPath, scaleRatio, cardType);

        mPathPaint = new Paint();
        mPathPaint.setAntiAlias(true);
        mPathPaint.setColor(Color.TRANSPARENT);
        mPathPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);

        canvas.translate(0, 40 * scaleRatio);

        canvas.drawPath(mPath, mPathPaint);

        mPaintBorder.setStyle(Paint.Style.STROKE);
        mPaintBorder.setStrokeWidth(12f);
        mPaintBorder.setColor(Color.BLACK);
        mPaintBorder.setTextSize(superSetTextSize);

        mPaintBorder.setAntiAlias(true);
        mPaintBorder.setStrokeJoin(Paint.Join.ROUND);
        mPaintBorder.setStrokeMiter(10f);
        mPaintBorder.setTextAlign(Paint.Align.CENTER);
        canvas.drawTextOnPath(textOnPath, mPath, 0, 0, mPaintBorder);
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(0f);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(superSetTextSize);

        mPaint.setAntiAlias(true);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeMiter(10f);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawTextOnPath(textOnPath, mPath, 0, 0, mPaint);

        super.onDraw(canvas);
    }

}