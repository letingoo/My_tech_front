package letingoo.ui.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import letingoo.entities.Article;
import letingoo.my_tech_front.R;
import letingoo.ui.DetailActivity;
import letingoo.ui.listener.OnItemClickListener;

/**
 * Created by barcelona on 2015/9/27.
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleHolder> {


    /*
    文章列表
     */
    private List<Article> articles;

    private LayoutInflater inflater;


    private OnItemClickListener<Article> mClickListener;


    public void setOnClickListener(OnItemClickListener<Article> onClickListener) {
        this.mClickListener = onClickListener;


    }

    public ArticlesAdapter (List<Article> articles) {


        this.articles = articles;
    }



    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate(R.layout.item_article, parent, false);
        return new ArticleHolder(view);
    }


    @Override
    public void onBindViewHolder(ArticleHolder holder, int position) {

        bindArticleToItemView(holder, position);
        setupItemClickListener( holder, articles.get(position) );
    }


    private void setupItemClickListener(ArticleHolder holder, final Article article) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( mClickListener != null )
                    mClickListener.onClick(article);
            }
        });
    }


    @Override
    public int getItemCount() {
        return (articles == null) ? 0 : articles.size();
    }



    private void bindArticleToItemView(ArticleHolder holder, int position) {

        Article article = articles.get(position);

        holder.textView_title.setText( article.getTitle() );
        holder.textView_date.setText( article.getDate() );
        holder.textView_author.setText(article.getAuthor());

    }





    public static class ArticleHolder extends RecyclerView.ViewHolder {

        private TextView textView_title;
        private TextView textView_author;
        private TextView textView_date;

        public ArticleHolder(View view) {
            super(view);

            textView_title = (TextView) view.findViewById(R.id.text_view_title);
            textView_author = (TextView) view.findViewById(R.id.text_view_author);
            textView_date = (TextView) view.findViewById(R.id.text_view_date);
        }
    }


}
