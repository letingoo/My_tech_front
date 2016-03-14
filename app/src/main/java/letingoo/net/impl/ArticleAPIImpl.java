package letingoo.net.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;

import java.util.List;

import letingoo.entities.Article;
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
    private int mPage = 1;

    private static final int INITIAL_PAGE = 1;

    // 写的不好
    private RespHandler<List<Article>, JSONArray> mRespHandler;

    public ArticleAPIImpl() {
        mRespHandler = new ArticlesHandler();
    }

    @Override
    public void fetchArticles(int category, DataListener<List<Article>> listener) {

        performRequest(INITIAL_PAGE, category, listener);
    }


    @Override
    public void loadMore(int category, DataListener<List<Article>> listener) {

        performRequest(++mPage, category, listener);
    }

    /**
     * 执行网络请求，网络请求使用Volley
     * @param page
     * @param category
     */

    private void performRequest(final int page, final int category, final DataListener<List<Article>> dataListener) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                URLHelper.produceFetchArticlesURL(page, category),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        dataListener.onComplete( mRespHandler.parse(jsonArray) );
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }
        );

        RequestQueueMgr.getRequestQueue().add(jsonArrayRequest);

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
