package letingoo.net.handlers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.List;

import letingoo.entities.Article;

/**
 * Created by barcelona on 2015/9/19.
 */
public class ArticlesHandler implements RespHandler<List<Article>, JSONArray> {

    private static Gson gson = new Gson();

    /**
     * 这里解析JSONArray数据使用Gson
     * @param data
     * @return
     */
    @Override
    public List<Article> parse(JSONArray data) {

        // JSONArray是否具有符合要求的toString？
        String json_string = data.toString();

        List<Article> articles = gson.fromJson(json_string, new TypeToken<List<Article>>() {}.getType());

        return articles;
    }
}
