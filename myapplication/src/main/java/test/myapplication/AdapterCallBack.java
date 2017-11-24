package test.myapplication;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/11/23.
 */

public class AdapterCallBack extends DiffUtil.Callback {
    private List oldList;
    private List newList;

    public AdapterCallBack(List oldList, List newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }

}
