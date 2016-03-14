package letingoo.presenter;

import java.util.ArrayList;
import java.util.List;

import letingoo.entities.Article;
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
    private List<Article> articles;


    private boolean noMoreArticles = false;


    public List<Article> getArticles() {
        return articles;
    }

    public ArticlePresenter(ArticlesViewInterface view) {

        this.view = view;
        articleAPI = new ArticleAPIImpl();

        articles = new ArrayList<Article>();

    }



    public void fetchArticles(int category) {

        articleAPI.fetchArticles(category, new DataListener<List<Article>>() {
            @Override
            public void onComplete(List<Article> result) {
                handleInformation(result);
            }
        });
    }



    public void loadMoreArticles(int category) {

        if ( noMoreArticles )
            return;

        articleAPI.loadMore(category, new DataListener<List<Article>>() {
            @Override
            public void onComplete(List<Article> result) {
                addMoreArticles(result);

                if (result.size() == 0)
                    noMoreArticles = true;
            }
        });
    }





    private void handleInformation(List<Article> result) {

        articles.removeAll(result);
        articles.addAll(result);
        view.showData(articles);
    }



    private void addMoreArticles(List<Article> result) {

        articles.addAll(result);
        view.addData(articles);
    }




}
