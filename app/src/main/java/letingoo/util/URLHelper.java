package letingoo.util;

/**
 * Created by barcelona on 2015/9/19.
 */
public class URLHelper {






    public static final String host = "http://c.m.163.com/";
    public static final String endUrl = "-20.html";

    public static final String TOP_URL = host + "nc/article/headline/";






    public static String produceFetchArticlesURL(final int page, final String category) {

        int index = page * 20;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TOP_URL).append(category).append('/').append(index).append(endUrl);

        return stringBuilder.toString();
    }



    public static String produceFetchArticleContentURL( String post_id ) {

        //return GET_ARTICLE_CONTENT_URL + post_id;
        return null;
    }

}
