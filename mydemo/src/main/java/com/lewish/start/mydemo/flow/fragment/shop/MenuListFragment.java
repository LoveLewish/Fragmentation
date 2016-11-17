package com.lewish.start.mydemo.flow.fragment.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lewish.start.mydemo.R;
import com.lewish.start.mydemo.flow.adapter.ShopMenuAdapter;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2016/11/16 14:06.
 */

public class MenuListFragment extends SupportFragment {
    private static final String TAG = "MenuListFragment";

    private static final String ARG_MENUS = "arg_menus";
    private static final String SAVE_STATE_POSITION = "save_state_position";

    private ArrayList<String> mMenusList;
    private RecyclerView mRecy;
    private int mCheckedItemPosition;
    private ShopMenuAdapter mShopMenuAdapter;

    public static MenuListFragment newInstance(ArrayList<String> mMenus){
        MenuListFragment instance = new MenuListFragment();
        Bundle bundle = new Bundle();
        if(mMenus == null) {
            throw new RuntimeException("传入菜单数据不能为空！");
        }
        bundle.putStringArrayList(ARG_MENUS,mMenus);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mMenusList = arguments.getStringArrayList(ARG_MENUS);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecy = (RecyclerView) inflater.inflate(R.layout.fragment_menulist, container, false);
        return mRecy;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null) {
            mCheckedItemPosition=savedInstanceState.getInt(SAVE_STATE_POSITION);
        }
        mShopMenuAdapter = new ShopMenuAdapter(_mActivity, mMenusList);
        mShopMenuAdapter.setOnItemClickListener(new ShopMenuAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mShopMenuAdapter.setItemChecked(position);
                switchContentFragment(mMenusList.get(position));
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);//设置布局管理器
        manager.setOrientation(LinearLayoutManager.VERTICAL);//设置布局方向
        mRecy.setLayoutManager(manager);//设置布局管理器
        //mRecy.addItemDecoration( new DividerGridItemDecoration(this ));//设置分隔线
        mRecy.setItemAnimator( new DefaultItemAnimator());//设置增加或删除条目的动画
        mRecy.setAdapter(mShopMenuAdapter);
        mShopMenuAdapter.setItemChecked(mCheckedItemPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_STATE_POSITION,mCheckedItemPosition);
    }
    public void switchContentFragment(String content){
        ContentFragment contentFragment = ContentFragment.newInstance(content);
        ShopFragment parentFragment = (ShopFragment)getParentFragment();
        parentFragment.switchContentFragment(contentFragment);
    }
}
