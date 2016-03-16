package letingoo.ui.interfaces;

import java.util.List;

import letingoo.entities.Article;
import letingoo.entities.NewsModule;

/**
 * Created by barcelona on 2015/10/18.
 */
public interface ArticlesViewInterface extends BaseViewInterface<List<NewsModule>>{

    /**
     * 添加新的数据
     * @param moreArticles
     */
    public void addData(List<NewsModule> moreArticles);
}
