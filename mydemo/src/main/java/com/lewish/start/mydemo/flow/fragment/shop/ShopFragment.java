package com.lewish.start.mydemo.flow.fragment.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.lewish.start.mydemo.R;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2016/11/16 14:03.
 */

public class ShopFragment extends SupportFragment {
    private static final String TAG = "ShopFragment";
    public static ShopFragment newInstance(Bundle bundle){
        ShopFragment shopFragment = new ShopFragment();
        if(bundle!=null) {
            shopFragment.setArguments(bundle);
        }
        return shopFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flow_shop, container, false);
        initView(view,savedInstanceState);
        return view;
    }

    private void initView(View view,Bundle savedInstanceState) {
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        initToolbar(mToolbar);

        if(savedInstanceState==null) {
            ArrayList<String> listMenus = new ArrayList<>();
            listMenus.add("销量排行");
            listMenus.add("当季特选");
            listMenus.add("炒菜");
            listMenus.add("汤面类");
            listMenus.add("煲类");
            listMenus.add("汤");
            listMenus.add("小菜");
            listMenus.add("酒水饮料");
            listMenus.add("盖浇饭类");
            listMenus.add("炒面类");
            listMenus.add("拉面类");
            listMenus.add("盖浇面类");
            listMenus.add("特色菜");
            listMenus.add("加料");
            listMenus.add("馄饨类");
            listMenus.add("其他");
            MenuListFragment menuListFragment = MenuListFragment.newInstance(listMenus);
            replaceLoadRootFragment(R.id.fl_menu,menuListFragment,false);
            replaceLoadRootFragment(R.id.fl_content,ContentFragment.newInstance(listMenus.get(0)),false);
        }

    }
    private void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("商店");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        toolbar.inflateMenu(R.menu.hierarchy);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_hierarchy:
                        _mActivity.showFragmentStackHierarchyView();
                        _mActivity.logFragmentStackHierarchy(TAG);
                        break;
                }
                return true;
            }
        });
    }
    public void switchContentFragment(ContentFragment fragment){
        ContentFragment contentFragment = findChildFragment(ContentFragment.class);
        if(contentFragment!=null) {
            contentFragment.replaceFragment(fragment,false);
        }else {
            replaceLoadRootFragment(R.id.fl_content,fragment,false);
        }
    }
}
