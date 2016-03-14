package letingoo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.util.List;

import letingoo.entities.Article;
import letingoo.entities.ArticleDetail;
import letingoo.my_tech_front.R;
import letingoo.presenter.ArticleDetailPresenter;
import letingoo.presenter.ArticlePresenter;
import letingoo.ui.interfaces.ArticleDetailInterface;
import letingoo.ui.interfaces.BaseViewInterface;
import letingoo.util.HtmlTemplate;

public class DetailActivity extends AppCompatActivity implements ArticleDetailInterface{



    private ArticleDetailPresenter presenter;

    private String postId;
    private String targetURL;
    private String title;


    /*
    UI控件
     */

    private WebView webView;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        presenter = new ArticleDetailPresenter(this);

        initView();
        initArticleURL();

        presenter.fetchArticleContent(postId);

    }



    private void initView() {

        progressBar = (ProgressBar) findViewById(R.id.loading_progressBar);
        webView = (WebView) findViewById(R.id.detail_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                WebSettings webSettings = webView.getSettings();
                webSettings.setBuiltInZoomControls(true);
                webView.loadUrl(url);
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(newProgress);
                if (100 == newProgress)
                    progressBar.setVisibility(View.GONE);
            }
        });
    }



    private void initArticleURL() {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            postId = bundle.getString("post_id");
            title = bundle.getString("title");

        }
    }




    @Override
    public void showData(ArticleDetail data) {
        webView.loadDataWithBaseURL("", HtmlTemplate.wrap( title, data.getContent() ),
                "text/html", "utf8", "404");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
