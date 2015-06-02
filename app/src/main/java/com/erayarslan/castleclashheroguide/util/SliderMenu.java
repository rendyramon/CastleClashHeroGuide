package com.erayarslan.castleclashheroguide.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.erayarslan.castleclashheroguide.CategoryActivity;
import com.erayarslan.castleclashheroguide.MainActivity;
import com.erayarslan.castleclashheroguide.R;
import com.erayarslan.castleclashheroguide.adapter.DrawerListAdapter;

import java.util.ArrayList;

public class SliderMenu {

    private TextView title;

    private Activity activity;

    private ListView mDrawerList;
    private RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private TextView mUsername;
    private RelativeLayout mProfileBox;

    private ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();

    public SliderMenu(Activity activity) {
        this.activity = activity;
    }

    public ActionBarDrawerToggle doMenu() {
        activity.getActionBar().setDisplayHomeAsUpEnabled(true);

        mNavItems.add(new NavItem(activity.getString(R.string.menu_sacrifice), activity.getString(R.string.menu_sacrifice_sub)));
        mNavItems.add(new NavItem(activity.getString(R.string.menu_ordinary), activity.getString(R.string.menu_ordinary_sub)));
        mNavItems.add(new NavItem(activity.getString(R.string.menu_elite), activity.getString(R.string.menu_elite_sub)));
        mNavItems.add(new NavItem(activity.getString(R.string.menu_legendary), activity.getString(R.string.menu_legendary_sub)));
        mDrawerLayout = (DrawerLayout) activity.findViewById(R.id.drawerLayout);
        mProfileBox = (RelativeLayout) activity.findViewById(R.id.profileBox);
        mDrawerPane = (RelativeLayout) activity.findViewById(R.id.drawerPane);
        mDrawerList = (ListView) activity.findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(activity, mNavItems);
        mDrawerList.setAdapter(adapter);

        mProfileBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                alertDialogBuilder.setTitle(activity.getString(R.string.about_title));
                alertDialogBuilder.setMessage(activity.getString(R.string.about_content)).setCancelable(true);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                TextView textView = (TextView) alertDialog.findViewById(android.R.id.message);
                textView.setTextSize(16);
            }
        });

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mNavItems.get(position).mTitle.equalsIgnoreCase(activity.getString(R.string.menu_sacrifice))) {
                    doIt(activity.getString(R.string.menu_sacrifice));
                } else if (mNavItems.get(position).mTitle.equalsIgnoreCase(activity.getString(R.string.menu_ordinary))) {
                    doIt(activity.getString(R.string.menu_ordinary));
                } else if (mNavItems.get(position).mTitle.equalsIgnoreCase(activity.getString(R.string.menu_elite))) {
                    doIt(activity.getString(R.string.menu_elite));
                } else if (mNavItems.get(position).mTitle.equalsIgnoreCase(activity.getString(R.string.menu_legendary))) {
                    doIt(activity.getString(R.string.menu_legendary));
                }
                mDrawerLayout.closeDrawer(mDrawerPane);
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(activity, mDrawerLayout, R.drawable.ic_navigation_drawer, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                activity.invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                activity.invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        return mDrawerToggle;
    }

    private void doIt(String text) {
        Intent intent = new Intent(activity, CategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("category", text);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.finish();
    }
}
