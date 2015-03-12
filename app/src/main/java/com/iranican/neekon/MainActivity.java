package com.iranican.neekon;

import java.util.Locale;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.util.DisplayMetrics;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.title_layout);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText("")
                            .setTabListener(this)
                            .setCustomView(R.layout.tab_icon));
        }

        setTabImage(0, R.drawable.home_icon_active);
        setTabImage(1, R.drawable.schedule_icon);
        setTabImage(2, R.drawable.location_icon);


        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        final View tabView = actionBar.getTabAt(0).getCustomView();
        final View tabContainerView = (View) tabView.getParent();
        final int tabPadding = tabContainerView.getPaddingLeft() + tabContainerView.getPaddingRight();
        final int tabs = actionBar.getTabCount();
        for(int i=0 ; i < tabs ; i++) {
            View tab = actionBar.getTabAt(i).getCustomView();
            ImageView imageView =
                    ((ImageView) tab.findViewById(R.id.tabicon));
            //showMessage(imageView.getParent().getParent().getClass().toString());
            imageView.setMaxWidth(screenWidth/tabs-tabPadding-1);
//            ((LinearLayout)imageView.getParent())
        }
    }

    private void showMessage(String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void setTabImage(int i, int imageId) {
        final ActionBar actionBar = getSupportActionBar();
        ImageView imageView =
            ((ImageView) actionBar.getTabAt(i).getCustomView().findViewById(R.id.tabicon));
        imageView.setImageResource(imageId);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar.getTabCount() == 3) {
            setTabImage(0, R.drawable.home_icon);
            setTabImage(1, R.drawable.schedule_icon);
            setTabImage(2, R.drawable.location_icon);
            switch (tab.getPosition()) {
                case 0:
                    setTabImage(0, R.drawable.home_icon_active);
                    break;
                case 1:
                    setTabImage(1, R.drawable.schedule_icon_active);
                    break;
                case 2:
                    setTabImage(2, R.drawable.location_icon_active);
                    break;
            }
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1: {
                    ImageView image = new ImageView(getActivity().getApplicationContext());
                    image.setImageResource(R.drawable.coming_soon);
                    image.setPadding(-50, -50, -50, -50);
                    image.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    image.setScaleX(1.25f);
                    image.setScaleY(1.25f);
                    image.setBackgroundColor(0xFF70B7C5);
                    return image;
                }
                case 2: {
                    ImageView image = new ImageView(getActivity().getApplicationContext());
                    image.setImageResource(R.drawable.schedule_with_background);
                    image.setPadding(-50, -50, -50, -50);
                    image.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    image.setScaleX(1.25f);
                    image.setScaleY(1.25f);
                    image.setBackgroundColor(0xFF725672);
                    return image;
                }
                case 3: {
                    ImageView image = new ImageView(getActivity().getApplicationContext());
                    image.setImageResource(R.drawable.location_image);
                    image.setPadding(-50, -50, -50, -50);
                    image.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    image.setScaleX(1.25f);
                    image.setScaleY(1.25f);
                    image.setBackgroundColor(0xFF000000);
                    return image;
                }
            }
            return rootView;
        }
    }

}
