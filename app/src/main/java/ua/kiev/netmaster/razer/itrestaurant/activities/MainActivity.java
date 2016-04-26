package ua.kiev.netmaster.razer.itrestaurant.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Collections;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.adapters.RecyclingRequestFragment;
import ua.kiev.netmaster.razer.itrestaurant.fragments.OneMoreTableMapFragment;
import ua.kiev.netmaster.razer.itrestaurant.fragments.RequestDescriptionFragment;
import ua.kiev.netmaster.razer.itrestaurant.fragments.SecondPageFragmentListener;
import ua.kiev.netmaster.razer.itrestaurant.fragments.TableDetailsFragment;
import ua.kiev.netmaster.razer.itrestaurant.loger.L;


public class MainActivity extends AppCompatActivity {


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    private MyApplication myApplication;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.l("onCreate", this);
        setContentView(R.layout.activity_main);
        myApplication = (MyApplication) getApplication();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        L.l("toolbar.getHeight()) = " +toolbar.getHeight(), this);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        L.l("onResume()", this);
        if(myApplication.isBackToRequesqList()){
            switchToReqList();
        }//else gotoMap();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */


    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        private class ReqestPageListener implements SecondPageFragmentListener {

            @Override
            public void onSwitchToChildFragment() {
                L.l("onSwitchToChildFragment()", this);
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                //transaction.addToBackStack(null);
                transaction.remove(mFragmentAtPos1).commit();
                if(mFragmentAtPos1 instanceof RecyclingRequestFragment){
                    mFragmentAtPos1 = RequestDescriptionFragment.newInstance(this);
                }else {
                    mFragmentAtPos1 = RecyclingRequestFragment.newInstance(this);
                }
                notifyDataSetChanged();
            }
        }


        private final FragmentManager mFragmentManager;
        private Fragment mFragmentAtPos1;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragmentManager = fm;
        }

        @Override
        public Fragment getItem(int position) {
            L.l("getItem()", this);
            if(position ==1){
                if(mFragmentAtPos1 ==null){
                    mFragmentAtPos1 = RecyclingRequestFragment.newInstance(new ReqestPageListener());
                } return mFragmentAtPos1;
            }  else return new OneMoreTableMapFragment();
        }

        @Override
        public int getCount() {
            L.l("getCount()", this);
            // Show 2 total pages.
            return 2;
        }


        @Override
        public int getItemPosition(Object object) {
            L.l("getItemPosition()", this);
            if(object instanceof RecyclingRequestFragment && mFragmentAtPos1 instanceof RequestDescriptionFragment)
                return POSITION_NONE;
            if(object instanceof RequestDescriptionFragment && mFragmentAtPos1 instanceof RecyclingRequestFragment)
                return POSITION_NONE;
            return POSITION_UNCHANGED;
        }
    }

    @Override
    public void onBackPressed() {
        L.l("onBackPressed()");
        if(!switchToReqList()) super.onBackPressed();
    }

    private boolean switchToReqList(){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.container + ":"+mViewPager.getCurrentItem());
        Collections.sort(myApplication.getRequestList());
        if(fragment instanceof RequestDescriptionFragment){
            RequestDescriptionFragment.getSecondPageFragmentListener().onSwitchToChildFragment();
            return true;
        } else return false;
    }

    private boolean gotoMap(){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.container + ":"+mViewPager.getCurrentItem());
        Collections.sort(myApplication.getRequestList());
        if(fragment instanceof TableDetailsFragment){
            RequestDescriptionFragment.getSecondPageFragmentListener().onSwitchToChildFragment();
            return true;
        } else return false;
    }


}
