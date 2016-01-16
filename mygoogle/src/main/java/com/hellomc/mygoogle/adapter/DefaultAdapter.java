package com.hellomc.mygoogle.adapter;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.hellomc.mygoogle.holder.BaseHolder;
import com.hellomc.mygoogle.holder.MoreHolder;
import com.hellomc.mygoogle.utils.ThreadPoolManager;
import com.hellomc.mygoogle.utils.UIUtils;

import java.util.List;

/**
 * Created by hellomc on 2015/12/27.
 */
public abstract class DefaultAdapter<T> extends BaseAdapter {

    public static final int default_item = 0;
    public static final int more_item = 1;
    private List<T> datas;
    private MoreHolder moreHolder;

    public DefaultAdapter(List<T> datas,ListView listView) {

        this.datas = datas;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OnInnerItemClickListener(position);
            }
        });
    }

    public void OnInnerItemClickListener(int position) {

    }

    @Override
    public int getCount() {
        return datas.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder baseHolder = null;
        switch (getItemViewType(position)) {//判断当前位置应该显示哪种类型的子条目
            case default_item:
                if (convertView == null) {
                    baseHolder = getHolder();// 无参构造
                } else {
                    baseHolder = (BaseHolder) convertView.getTag();
                }
                // 获取position对应的数据
                T data = datas.get(position);
                // 将数据传递到holder内
                baseHolder.setDatas(data);
                break;
            case more_item:
                //显示加载更多，MoreHolder
                if (convertView == null) {
                    baseHolder = getMoreHolder();
                } else {
                    baseHolder = (BaseHolder) convertView.getTag();
                }
                //不需要传递数据，应该加载更多，显示的内容是定死的
                break;

            default:
                break;
        }
        return baseHolder.getContenView();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == datas.size()) {
            return more_item;
        } else {
            return default_item;
        }
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    private BaseHolder getMoreHolder() {
        if (moreHolder == null) {
            moreHolder = new MoreHolder(this);
        }
        return moreHolder;
    }

    public abstract BaseHolder getHolder();

    public void loadMore() {
        ThreadPoolManager.getInstance().createNetThreadPool().execute(new Runnable() {

            @Override
            public void run() {
                SystemClock.sleep(1000);
                //真正请求下一页逻辑，父类处理不了
                final List<T> newDatas = onLoadMore();
                UIUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (newDatas == null) {
                            //未请求到数据，应该通知MoreHolder，显示加载失败界面
                            moreHolder.setDatas(MoreHolder.load_error);
                        } else {
                            if (newDatas.size() > 0) {
                                datas.addAll(newDatas);
                                DefaultAdapter.this.notifyDataSetChanged();
                                //请求到数据，应该通知MoreHolder，显示加载中界面
                                moreHolder.setDatas(MoreHolder.has_more);
                            } else {
                                //已经没有下一页数据,通知MoreHolder，隐藏

                            }
                        }
                    }
                });

            }


        });

    }

    protected abstract List<T> onLoadMore();
}
