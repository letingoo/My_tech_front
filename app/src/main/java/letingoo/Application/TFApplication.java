package letingoo.Application;

import android.app.Application;

import letingoo.net.mgr.RequestQueueMgr;

/**
 * Created by barcelona on 2015/9/19.
 */
public class TFApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RequestQueueMgr.init(this);
    }
}
