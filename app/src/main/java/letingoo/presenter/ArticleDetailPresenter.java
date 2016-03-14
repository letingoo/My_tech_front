package letingoo.presenter;

import letingoo.entities.ArticleDetail;
import letingoo.interfaces.DataListener;
import letingoo.net.impl.ArticleAPIImpl;
import letingoo.net.interfaces.ArticleAPI;
import letingoo.ui.interfaces.ArticleDetailInterface;

/**
 * Created by barcelona on 2015/10/17.
 */
public class ArticleDetailPresenter {


    private ArticleDetailInterface detailView;



    private ArticleAPI articleAPI = new ArticleAPIImpl();


    public void fetchArticleContent(final String post_id) {

        fetchArticleFromNetwork(post_id);

    }


    private void fetchArticleFromNetwork(final String post_id) {

        articleAPI.fetchArticleContent(
                post_id,

                new DataListener<String>() {
                    @Override
                    public void onComplete(String result) {
                        ArticleDetail articleDetail = new ArticleDetail(post_id, result);
                        detailView.showData(articleDetail);
                    }
                });
    }


    public ArticleDetailPresenter(ArticleDetailInterface detailInterface) {
        detailView = detailInterface;
    }


}
