package test.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<String> list = new ArrayList();
        for (int i = 0; i < 100; i++) {
            list.add("asdfsadfasdf" + i);
        }
        final RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(this,1));
        mAdapter = new MyAdapter(list);
        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                /*list.remove(position);
                mAdapter.notifyItemRemoved(position);
                if (position != mAdapter.getItemCount()) {
                    mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount() - position);
                }*/
                rv.smoothScrollToPosition(10);
            }
        });
        rv.setAdapter(mAdapter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rv);
    }
}
