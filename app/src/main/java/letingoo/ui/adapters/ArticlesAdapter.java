package letingoo.ui.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.List;

import letingoo.entities.Article;
import letingoo.entities.NewsModule;
import letingoo.my_tech_front.R;
import letingoo.net.mgr.RequestQueueMgr;
import letingoo.ui.DetailActivity;
import letingoo.ui.listener.OnItemClickListener;
import letingoo.util.LruBitmapCache;

/**
 * Created by barcelona on 2015/9/27.
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleHolder> {

    // Volley框架用于加载图片的
    private ImageLoader imageLoader;


    /*
    文章列表
     */
    private List<NewsModule> news_list;

    private LayoutInflater inflater;


    private OnItemClickListener<NewsModule> mClickListener;


    public void setOnClickListener(OnItemClickListener<NewsModule> onClickListener) {
        this.mClickListener = onClickListener;
    }

    public ArticlesAdapter (List<NewsModule> news_list) {

        this.news_list = news_list;

        imageLoader = new ImageLoader(RequestQueueMgr.getRequestQueue(), new LruBitmapCache());

    }



    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate(R.layout.item_article, parent, false);
        return new ArticleHolder(view);
    }


    @Override
    public void onBindViewHolder(ArticleHolder holder, int position) {

        bindArticleToItemView(holder, position);
        setupItemClickListener(holder, news_list.get(position));
    }


    private void setupItemClickListener(ArticleHolder holder, final NewsModule news) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( mClickListener != null )
                    mClickListener.onClick(news);
            }
        });
    }


    @Override
    public int getItemCount() {
        return (news_list == null) ? 0 : news_list.size();
    }



    private void bindArticleToItemView(ArticleHolder holder, int position) {

        NewsModule news = news_list.get(position);

        // 图片怎么弄还在研究
        holder.textView_title.setText(news.getTitle());
        holder.textView_digest.setText(news.getDigest());

        holder.image.setImageUrl( news.getImgsrc(), imageLoader );

    }





    public static class ArticleHolder extends RecyclerView.ViewHolder {

        private TextView textView_title;
        private TextView textView_digest;
        private NetworkImageView image;

        public ArticleHolder(View view) {
            super(view);

            textView_title = (TextView) view.findViewById(R.id.title_text);
            textView_digest = (TextView) view.findViewById(R.id.digest_text);
            image = (NetworkImageView)view.findViewById(R.id.image);
        }
    }


}
