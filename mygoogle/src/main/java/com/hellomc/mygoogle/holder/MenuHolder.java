package com.hellomc.mygoogle.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hellomc.mygoogle.MainActivity;
import com.hellomc.mygoogle.R;
import com.hellomc.mygoogle.bean.UserInfo;
import com.hellomc.mygoogle.http.HttpHelper;
import com.hellomc.mygoogle.protocol.UserProtocol;
import com.hellomc.mygoogle.utils.BitmapHelp;
import com.hellomc.mygoogle.utils.ThreadPoolManager;
import com.hellomc.mygoogle.utils.UIUtils;

/**
 * Created by hellomc on 2015/12/28.
 */
public class MenuHolder extends BaseHolder<UserInfo> {
    private ImageView image_photo;
    private TextView user_name;
    private TextView user_email;
    private MainActivity mainActivity;

    @Override
    public View initView() {
        final View view = View.inflate(UIUtils.getContext(), R.layout.menu_holder, null);
        image_photo = (ImageView) view.findViewById(R.id.image_photo);
        user_name = (TextView) view.findViewById(R.id.user_name);
        user_email = (TextView) view.findViewById(R.id.user_email);
        RelativeLayout photo_layout = (RelativeLayout) view.findViewById(R.id.photo_layout);
        photo_layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ThreadPoolManager.getInstance().createNetThreadPool().execute(new Runnable() {

                    @Override
                    public void run() {
                        //登陆
                        UserProtocol userProtocol = new UserProtocol();
                        final UserInfo userInfo = (UserInfo) userProtocol.load(0);
                        UIUtils.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                setDatas(userInfo);
                            }
                        });

                    }
                });

            }

        });
        RelativeLayout exitLayout = (RelativeLayout) view.findViewById(R.id.exit_layout);
        exitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.finish();
            }
        });
        return view;
    }

    @Override
    public void refreshView(UserInfo data) {
        //刷新界面
        //姓名
        user_name.setText(data.getName());
        //邮箱
        user_email.setText(data.getEmail());
        String imageUrl = HttpHelper.getImageUrl(data.getUrl());
        BitmapHelp.getBitmapUtils(UIUtils.getContext()).display(image_photo, imageUrl);

    }



    public void setActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}