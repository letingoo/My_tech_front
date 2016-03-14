package letingoo.entities;

/**
 * Created by barcelona on 2015/10/18.
 */


/**
 * 文章的具体信息
 */
public class ArticleDetail {

    private String post_id;
    private String content;


    public ArticleDetail(String post_id, String content) {
        this.post_id = post_id;
        this.content = content;
    }


    public ArticleDetail() {
    }

    /*
            Getter and Setter
             */
    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
