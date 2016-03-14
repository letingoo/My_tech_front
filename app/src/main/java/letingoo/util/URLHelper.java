package letingoo.util;

/**
 * Created by barcelona on 2015/9/19.
 */
public class URLHelper {

    private static final String TARGET_URL = "http://www.devtf.cn/api/v1/?type=articles";

    private static final String PAGE = "&page=";

    private static final String COUNT = "&count=";

    private static final String CATEGORY = "&category=";

    private static final int DEFAULT_COUNT = 20;


    private static final String GET_ARTICLE_CONTENT_URL = "http://www.devtf.cn/api/v1/?type=article&post_id=";


    public static String produceFetchArticlesURL(final int page, final int category) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TARGET_URL).append(PAGE).append(page).append(COUNT).append(DEFAULT_COUNT).append(CATEGORY).append(category);

        return stringBuilder.toString();
    }



    public static String produceFetchArticleContentURL( String post_id ) {

        return GET_ARTICLE_CONTENT_URL + post_id;
    }

}
