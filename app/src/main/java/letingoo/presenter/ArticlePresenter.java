package letingoo.presenter;

import java.util.ArrayList;
import java.util.List;

import letingoo.entities.Article;
import letingoo.entities.NewsModule;
import letingoo.interfaces.DataListener;
import letingoo.net.impl.ArticleAPIImpl;
import letingoo.net.interfaces.ArticleAPI;
import letingoo.ui.interfaces.ArticlesViewInterface;
import letingoo.ui.interfaces.BaseViewInterface;

/**
 * Created by barcelona on 2015/9/20.
 */
public class ArticlePresenter {

    /**
     * 网络端
     */
    private ArticleAPI articleAPI;


    /**
     * UI端
     */
    private ArticlesViewInterface view;


    /**
     * Articles的列表
     */
    private List<NewsModule> articles;



    // 新闻类型
    private String newsCategory;

    public void setNewsCategory(String newsCategory) {
        this.newsCategory = newsCategory;
    }

    public String getNewsCategory() {
        return newsCategory;
    }

    private boolean noMoreArticles = false;


    public List<NewsModule> getArticles() {
        return articles;
    }

    public ArticlePresenter(ArticlesViewInterface view) {

        this.view = view;
        articleAPI = new ArticleAPIImpl();

        articles = new ArrayList<NewsModule>();

    }



    public void fetchArticles(String category) {

        articleAPI.fetchArticles(category, new DataListener<List<NewsModule>>() {
            @Override
            public void onComplete(List<NewsModule> result) {
                handleInformation(result);
            }
        });
    }



    public void loadMoreArticles(String category) {

        if ( noMoreArticles )
            return;

        articleAPI.loadMore(category, new DataListener<List<NewsModule>>() {
            @Override
            public void onComplete(List<NewsModule> result) {
                addMoreArticles(result);

                if (result.size() == 0)
                    noMoreArticles = true;
            }
        });
    }





    private void handleInformation(List<NewsModule> result) {

        articles.removeAll(result);
        articles.addAll(result);
        view.showData(articles);
    }



    private void addMoreArticles(List<NewsModule> result) {

        articles.addAll(result);
        view.addData(articles);
    }




}
