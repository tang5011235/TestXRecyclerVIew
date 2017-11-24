package test.testxrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import test.testxrecyclerview.adapter.XRVAdapter;

public class MainActivity extends AppCompatActivity implements XRVAdapter.OnItemClickListener {

    private XRecyclerView mXRecyclerView;
    private XRVAdapter mAdapter;
    private Bean mBean;
    private int page;
    private List newList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newList = new ArrayList();
        initView();
    }

    private void initView() {
        mXRecyclerView = (XRecyclerView) findViewById(R.id.xrv);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mXRecyclerView);
        mXRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadData();
            }

            @Override
            public void onLoadMore() {
                page += 1;
                loadData();
            }
        });
        mAdapter = new XRVAdapter(newList);
        mAdapter.setOnItemClickListener(this);
        mXRecyclerView.setAdapter(mAdapter);
        mXRecyclerView.refresh();
    }

    private void loadData() {
        OkHttpUtils
                .get()
                .url("http://gank.io/api/data/福利/10/" + String.valueOf(page))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getApplicationContext(), "请求失败", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        mBean = gson.fromJson(response, Bean.class);
                        if (page == 1) {
                            newList.clear();
                            newList.addAll(mBean.getResults());
                            mXRecyclerView.refreshComplete();
                        } else if (page > 1 && newList.size() > 0) {
                            newList.addAll(mBean.getResults());
                            if (mBean.getResults().size() == 0) {
                                mXRecyclerView.loadMoreComplete();
                                mXRecyclerView.setNoMore(true);
                            } else {
                                mXRecyclerView.loadMoreComplete();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onClick(View view, int position) {
       /* mAdapter.notifyItemRemoved(position);
        newList.remove(position);
        mAdapter.notifyItemRangeChanged(position,mAdapter.getItemCount()-position);*/
       /*newList.remove(position);
       mAdapter.notifyDataSetChanged();*/
    }


}
