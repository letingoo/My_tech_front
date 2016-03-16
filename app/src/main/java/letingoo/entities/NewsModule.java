package letingoo.entities;

import java.io.Serializable;

/**
 * Created by barcelona on 2016/3/15.
 */
public class NewsModule implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 618528940129458276L;


    /**
     * 标题
     */
    private String title;

    /**
     * 新闻的url(3w)
     */
    private String url_3w;


    /**
     * 新闻的url(3g)
     */
    private String url;


    /**
     * 时间
     */
    private String ptime;

    /**
     * 配图的url
     */
    private String imgsrc;

    /**
     * 摘要
     */
    private String digest;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl_3w() {
        return url_3w;
    }

    public void setUrl_3w(String url_3w) {
        this.url_3w = url_3w;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
