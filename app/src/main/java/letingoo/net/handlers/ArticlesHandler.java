package letingoo.net.handlers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import letingoo.entities.Article;
import letingoo.entities.NewsModule;
import letingoo.entities.TopNews;

/**
 * Created by barcelona on 2015/9/19.
 */
public class ArticlesHandler implements RespHandler<List<NewsModule>, JSONObject> {

    private static Gson gson = new Gson();

    /**
     * 这里解析JSONArray数据使用Gson
     * @param data
     * @return
     */
    @Override
    public List<NewsModule> parse(JSONObject data) {

        // JSONArray是否具有符合要求的toString？
        String json_string = data.toString();

        TopNews topNews = gson.fromJson(json_string, TopNews.class);

        return topNews.getT1348647909107();
    }
}
