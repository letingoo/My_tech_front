package letingoo.Application;

import android.app.Application;

import com.android.volley.RequestQueue;

import letingoo.net.mgr.RequestQueueMgr;

/**
 * Created by barcelona on 2015/9/19.
 */
public class TFApplication extends Application {

    public static int memoryCacheSize;
    @Override
    public void onCreate() {
        super.onCreate();

        RequestQueueMgr.init(this);


        memoryCacheSize = getMemoryCacheSize();
    }






    /**
     * @description
     *
     *
     * @return 得到需要分配的缓存大小，这里用八分之一的大小来做
     */
    private int getMemoryCacheSize() {
        // Get memory class of this device, exceeding this amount will throw an
        // OutOfMemory exception.
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        // Use 1/8th of the available memory for this memory cache.
        return maxMemory / 8;
    }
}
