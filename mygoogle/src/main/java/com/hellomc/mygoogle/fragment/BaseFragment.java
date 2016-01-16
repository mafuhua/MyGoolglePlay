package com.hellomc.mygoogle.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hellomc.mygoogle.utils.UIUtils;
import com.hellomc.mygoogle.view.LoadingPage;

import java.util.List;

/**
 * Created by hellomc on 2015/12/26.
 */
public abstract class BaseFragment<T> extends Fragment {

    private LoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (loadingPage == null) {
            loadingPage = new LoadingPage(UIUtils.getContext()) {
                @Override
                public View createSucessView() {
                    return BaseFragment.this.createSucessView();
                }

                @Override
                public LoadResult load() {

                    return BaseFragment.this.load();
                }
            };
            loadingPage.initView();

        } else {
            UIUtils.removeParent(loadingPage);
        }
        loadingPage.showView();
        // loadAndView();
        return loadingPage;
    }


    public void loadAndView() {
        if (loadingPage != null) {
            loadingPage.loadAndView();
        }
    }

    public abstract View createSucessView();


    public abstract LoadResult load();

    public enum LoadResult {
        error(2), empty(3), success(4);

        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }
    public LoadResult checkAppInfos(List<T> appInfos) {
        if (appInfos == null) {
            return LoadResult.error;
        } else {
            if (appInfos.size() == 0) {
                return LoadResult.empty;
            } else {
                return LoadResult.success;
            }
        }
    }
}
