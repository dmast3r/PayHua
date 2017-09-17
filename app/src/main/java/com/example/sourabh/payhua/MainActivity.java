package com.example.sourabh.payhua;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;
    private FragmentManager mFragmentManager;
    private Toolbar mMyToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get reference to toolbar and set it as action bar.
        mMyToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mMyToolbar);

        // get reference to the Bottom Navigation Bar.
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_bar);


        // instantiate the Fragment Manager and check if it is hosting a Fragment.
        mFragmentManager = getSupportFragmentManager();
        Fragment currentFragment = mFragmentManager.findFragmentById(R.id.container_frame);

        /* if it is hosting no Fragment then null will be returned. In that case make it host the MainFragment(where payment
        takes place). That is by default, on launch of the app the Payment Fragment is displayed.
         */
        if(currentFragment == null) {

            currentFragment = new MainFragment();
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container_frame, currentFragment).commit();
        }


        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            // one of the four navigator's reference will be passed to this method.
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                // get the ID of this item.
                switch (item.getItemId()) {

                    /* rest of the code below is self explanatory. It replaces the existing Fragment with a new one depending
                       on the clicked navigator.
                    */
                    case R.id.action_pay_now:
                        mFragmentManager.beginTransaction().replace(R.id.container_frame, new MainFragment()).commit();
                        break;

                    case R.id.action_user_profile:
                        mFragmentManager.beginTransaction().replace(R.id.container_frame, new ProfileFragment()).commit();
                        break;

                    case R.id.action_settings:
                        mFragmentManager.beginTransaction().replace(R.id.container_frame, new SettingsFragment()).commit();
                        break;

                    case R.id.action_history:
                        mFragmentManager.beginTransaction().replace(R.id.container_frame, new HistoryFragment()).commit();
                        break;
                }

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}
