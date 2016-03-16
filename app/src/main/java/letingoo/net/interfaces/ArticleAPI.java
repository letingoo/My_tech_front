package letingoo.net.interfaces;

import java.util.List;

import letingoo.entities.Article;
import letingoo.entities.NewsModule;
import letingoo.interfaces.DataListener;

/**
 * Created by barcelona on 2015/9/19.
 * 网络交互的接口
 */
public interface ArticleAPI {

    /**
     *根据分类通过网络获取文章列表
     * @param category 分类的标识
     */
    public void fetchArticles(String category, DataListener<List<NewsModule>> listener);


    /**
     * 加载更多文章列表
     * @param category
     * @param listener
     */
    public void loadMore(String category, DataListener<List<NewsModule>> listener);


    /**
     * 获取文章具体内容
     * @param post_id
     * @param listener
     */
    public void fetchArticleContent(final String post_id, DataListener<String> listener);

}
