package letingoo.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.List;

import letingoo.entities.Article;
import letingoo.my_tech_front.R;
import letingoo.presenter.ArticlePresenter;
import letingoo.ui.DetailActivity;
import letingoo.ui.adapters.ArticlesAdapter;
import letingoo.ui.interfaces.ArticlesViewInterface;
import letingoo.ui.interfaces.BaseViewInterface;
import letingoo.ui.listener.OnItemClickListener;

/**
 * Created by barcelona on 2015/9/26.
 */
public class ArticlesFragment extends Fragment implements ArticlesViewInterface {

    private ArticlePresenter presenter;


    /*
    UI控件
     */
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private View footerLayout;


    private TextView textMore;
    private ProgressBar progressBar;


    private ArticlesAdapter articlesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new ArticlePresenter(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_articles, container, false);

        initViews(view);

        //setupFooterView();
        setupSwipeRefreshLayout();
        setupRecylerView();

        fetchData();
        swipeRefreshLayout.setRefreshing(true);

        return view;
    }



    private void initViews(View view) {

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyler_articles);
        footerLayout = getActivity().getLayoutInflater().inflate(R.layout.recyclerview_footer, null);


    }



    private void setupFooterView() {

        textMore = (TextView) footerLayout.findViewById(R.id.text_more);
        progressBar = (ProgressBar) footerLayout.findViewById(R.id.load_progress_bar);

    }


    private void setupSwipeRefreshLayout() {


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.fetchArticles(Article.ALL);
            }
        });

    }



    private void setupRecylerView() {

        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()) );
        articlesAdapter = new ArticlesAdapter( presenter.getArticles() );
        articlesAdapter.setOnClickListener(new OnItemClickListener<Article>() {
            @Override
            public void onClick(Article article) {
                if ( article != null )
                    loadArticle(article);
            }
        });

        recyclerView.setAdapter(articlesAdapter);

        // 设置上拉加载更多
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            private int currentStatus = RecyclerView.SCROLL_STATE_IDLE;
            private int last_dy = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                currentStatus = newState;
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                if (firstVisibleItemPosition == 0 && (dy > last_dy))
                    last_dy = dy;
                super.onScrolled(recyclerView, dx, dy);

                presenter.loadMoreArticles(Article.ALL);

            }
        });
    }



    private void fetchData() {

        presenter.fetchArticles(Article.ALL);
    }

    @Override
    public void showData(List<Article> articles) {

        articlesAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);

    }


    @Override
    public void addData(List<Article> moreArticles) {

        articlesAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }



    private void loadArticle(Article article) {

        Intent intent = new Intent( getActivity(), DetailActivity.class );
        intent.putExtra( "post_id", article.getPost_id() );
        intent.putExtra( "title", article.getTitle() );
        startActivity(intent);
    }
}
