package letingoo.util;

import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import letingoo.Application.TFApplication;

/**
 * Created by barcelona on 2016/3/15.
 */
public class LruBitmapCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> mMemoryCache;

    public static Bitmap cacheBitmap;

    public LruBitmapCache() {
        mMemoryCache = new LruCache<String, Bitmap>(TFApplication.memoryCacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mMemoryCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
    }
}
