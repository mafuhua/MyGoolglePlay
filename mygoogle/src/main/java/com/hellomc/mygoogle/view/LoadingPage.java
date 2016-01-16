package com.hellomc.mygoogle.view;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.hellomc.mygoogle.R;
import com.hellomc.mygoogle.fragment.BaseFragment;
import com.hellomc.mygoogle.utils.ThreadPoolManager;
import com.hellomc.mygoogle.utils.UIUtils;

/**
 * Created by hellomc on 2015/12/26.
 */
public abstract class LoadingPage extends FrameLayout {
    private final static int loading_state = 0;
    private final static int unknown_state = 1;
    private final static int error_state = 2;
    private final static int empty_state = 3;
    private final static int sucess_state = 4;
    private int current_state = unknown_state;
    private View errorView;
    private View loadingView;
    private View emptyView;
    private View sucessView;

    public LoadingPage(Context context) {
        super(context);
    }


    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
    }

    public LoadingPage(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public void initView() {
        if (loadingView == null) {
            loadingView = createLoadingView();
            this.addView(loadingView);
            loadingView.setVisibility(View.GONE);
        }
        if (errorView == null) {
            errorView = createErrorView();
            this.addView(errorView);
            errorView.setVisibility(View.GONE);
        }
        if (emptyView == null) {
            emptyView = createEmptyView();
            this.addView(emptyView);
            emptyView.setVisibility(View.GONE);
        }

    }

    private View createEmptyView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.view_empty, null);
        return view;
    }

    private View createErrorView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.view_error, null);
        Button reload = (Button) view.findViewById(R.id.reload);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAndView();
            }
        });
        return view;

    }

    private View createLoadingView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.view_load, null);
        return view;
    }

    public void showView() {
        if (loadingView != null) {
            loadingView.setVisibility(current_state == unknown_state || current_state == loading_state ? View.VISIBLE : View.GONE);
        }
        if (errorView != null) {
            errorView.setVisibility(current_state == error_state ? View.VISIBLE : View.GONE);
        }
        if (emptyView != null) {
            emptyView.setVisibility(current_state == empty_state ? View.VISIBLE : View.GONE);
        }
        if (sucessView == null && current_state == sucess_state) {
            sucessView = createSucessView();
            this.addView(sucessView);
            sucessView.setVisibility(View.GONE);
        }
        if (sucessView != null) {
            sucessView.setVisibility(current_state == sucess_state ? View.VISIBLE : View.GONE);
        }
    }

    public abstract View createSucessView();

    public void loadAndView() {
        if (current_state == error_state || current_state == empty_state) {
            current_state = loading_state;
        }
        showView();
        ThreadPoolManager.ThreadPoolPoxy loaclThreadPool = ThreadPoolManager.getInstance().createLoaclThreadPool();
        loaclThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                BaseFragment.LoadResult loadResult = load();
                int loadResultValue = loadResult.getValue();
                current_state = loadResultValue;
                //if (getActivity() != null) {
                UIUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showView();
                    }
                });
            }
        });
        /*new Thread() {
            @Override
            public void run() {

              //  }
            }
        }.start();*/
    }

    public abstract BaseFragment.LoadResult load();


}
