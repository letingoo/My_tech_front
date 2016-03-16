package letingoo.net.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import letingoo.entities.Article;
import letingoo.entities.NewsModule;
import letingoo.interfaces.DataListener;
import letingoo.net.handlers.ArticlesHandler;
import letingoo.net.handlers.RespHandler;
import letingoo.net.interfaces.ArticleAPI;
import letingoo.net.mgr.RequestQueueMgr;
import letingoo.util.URLHelper;

/**
 * Created by barcelona on 2015/9/19.
 */
public class ArticleAPIImpl implements ArticleAPI {

    /**
     * 当前页码
     */
    private int mPage = 0;

    private static final int INITIAL_PAGE = 1;

    // 写的不好
    private RespHandler<List<NewsModule>, JSONObject> mRespHandler;

    public ArticleAPIImpl() {
        mRespHandler = new ArticlesHandler();
    }

    @Override
    public void fetchArticles(String category, DataListener<List<NewsModule>> listener) {

        performRequest(INITIAL_PAGE, category, listener);
    }


    @Override
    public void loadMore(String category, DataListener<List<NewsModule>> listener) {

        performRequest(++mPage, category, listener);
    }

    /**
     * 执行网络请求，网络请求使用Volley
     * @param page
     * @param category
     */

    private void performRequest(final int page, final String category, final DataListener<List<NewsModule>> dataListener) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                URLHelper.produceFetchArticlesURL(page, category),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        dataListener.onComplete( mRespHandler.parse(jsonObject) );
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }
        );

        RequestQueueMgr.getRequestQueue().add(jsonObjectRequest);

    }


    @Override
    public void fetchArticleContent(String post_id, final DataListener<String> listener) {

        StringRequest request = new StringRequest(
                URLHelper.produceFetchArticleContentURL(post_id),

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String html) {
                        listener.onComplete(html);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }
        );
    }
}
