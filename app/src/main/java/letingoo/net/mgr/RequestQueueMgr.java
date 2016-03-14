package letingoo.net.mgr;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by barcelona on 2015/9/19.
 */
public class RequestQueueMgr {

    private static RequestQueue requestQueue;

    private RequestQueueMgr() {

    }


    public static void init(Context context) {

        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(context);
    }


    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

}
