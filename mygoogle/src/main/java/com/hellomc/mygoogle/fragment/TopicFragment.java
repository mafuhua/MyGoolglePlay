package com.hellomc.mygoogle.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hellomc.mygoogle.R;
import com.hellomc.mygoogle.adapter.DefaultAdapter;
import com.hellomc.mygoogle.bean.TopicInfo;
import com.hellomc.mygoogle.holder.BaseHolder;
import com.hellomc.mygoogle.http.HttpHelper;
import com.hellomc.mygoogle.protocol.TopicProtocol;
import com.hellomc.mygoogle.utils.BitmapHelp;
import com.hellomc.mygoogle.utils.UIUtils;
import com.hellomc.mygoogle.view.BaseListView;

import java.util.List;

/**
 * Created by hellomc on 2015/12/25.
 */
public class TopicFragment extends BaseFragment {


    private List<TopicInfo> datas;
    private BaseListView listView;

    @Override
    public View createSucessView() {
        /*TextView textView = new TextView(getActivity());
        textView.setText(load.toString());*/
        listView = new BaseListView(UIUtils.getContext());
        listView.setAdapter(new MyAdapter(datas,listView));
        return listView;
    }

    @Override
    public LoadResult load() {
        TopicProtocol topicProtocol = new TopicProtocol();
        datas = topicProtocol.load(0);
        return checkAppInfos(datas);
    }

    class MyAdapter extends DefaultAdapter<TopicInfo> {

        public MyAdapter(List<TopicInfo> datas,ListView listView) {
            super(datas,listView);
        }


        @Override
        public BaseHolder getHolder() {
            return new ViewHolder();
        }

        @Override
        protected List<TopicInfo> onLoadMore() {
            return null;
        }
    }

    class ViewHolder extends BaseHolder<TopicInfo>{

        public ImageView itemIcon;
        public TextView itemText;

        @Override
        public View initView() {
            View view = View.inflate(UIUtils.getContext(), R.layout.topic_item, null);
            this.itemIcon = (ImageView) view.findViewById(R.id.item_icon);
            this.itemText = (TextView) view.findViewById(R.id.item_txt);
            return view;
        }

        @Override
        public void refreshView(TopicInfo topicInfo) {
            this.itemText.setText(topicInfo.getDes());
            String imagerUrl = HttpHelper.getImageUrl(topicInfo.getUrl());
            BitmapHelp.getBitmapUtils(UIUtils.getContext()).display(this.itemIcon,imagerUrl);
        }


    }
}
