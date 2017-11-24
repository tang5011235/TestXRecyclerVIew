package test.testxrecyclerview;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/11/23.
 */

public class AdapterDiffCallBack extends DiffUtil.Callback {
    private List odlList;
    private List newList;

    public AdapterDiffCallBack(List odlList, List newList) {
        this.odlList = odlList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return odlList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return odlList.get(oldItemPosition).getClass().equals(newList.get(newItemPosition).getClass());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return odlList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
