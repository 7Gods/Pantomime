package animation.paper.wall.pantomime;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.service.wallpaper.WallpaperService;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

/**
 * Created by fukuda on 2016/06/06.
 */
class Wallpaper extends WallpaperService {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Engine onCreateEngine() {
        return new PantomimeEngine();
    }

    class PantomimeEngine extends Engine{

        private static final String TAG = "PantomimeEngine";

        @Override //エンジンが作られた時に呼ばれる
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);

            Log.d(TAG,"onCreate");
            //表示画面サイズ取得
            DisplayMetrics displaySize = Resources.getSystem().getDisplayMetrics();
            Log.d(TAG,
                    "width:"+displaySize.widthPixels+
                    " height:"+displaySize.heightPixels);

        }

        @Override //エンジンが破棄される時に呼ばれる
        public void onDestroy() {
            super.onDestroy();
            Log.d(TAG,"onDestroy");
        }

        @Override //ホームスクリーンのアクティブ状態が変更された
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);

            //アクティブになった
            if(visible) {

                Log.d(TAG,"onVisibilityChanged:Active");
            //非アクティブになった
            }else {

                Log.d(TAG,"onVisibilityChanged:Not Active");
            }
        }

        public void doDraw(int x,int y){

            Canvas canvas = getSurfaceHolder().lockCanvas();

            Paint paint = new Paint();
            canvas.drawColor(Color.BLACK);
            paint.setTextSize(24);
            paint.setColor(Color.WHITE);
            canvas.drawText("肉",x,y,paint);

            getSurfaceHolder().unlockCanvasAndPost(canvas);
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
        }

        @Override //サーフェイスが変更されたタイミングで呼ばれる
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
        }

        @Override // サーフェイスが作られたタイミングで呼ばれる
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
        }

        @Override
        public void onTouchEvent(MotionEvent event) {
            super.onTouchEvent(event);
            if(event.getAction() == MotionEvent.ACTION_MOVE ||
                    event.getAction() == MotionEvent.ACTION_DOWN){
                doDraw((int)event.getX(),(int)event.getY());
            }
        }
    }
}
