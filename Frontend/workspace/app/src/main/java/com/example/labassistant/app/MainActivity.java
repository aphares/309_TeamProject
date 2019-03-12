package com.example.labassistant.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.support.v7.widget.Toolbar;

import com.example.labassistant.R;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class MainActivity extends AppCompatActivity {

    //Variables to hold the four main fragments
    private ChatFragment chatFragment;
    private ClassworkFragment classworkFragment;
    private QueueFragment queueFragment;
    private SettingsFragment settingsFragment;

    //Holds Main frame and bottom Navigation bar
    private BottomNavigationView mainNavBar;
    private FrameLayout mainFrame;

    /**
     * @param savedInstanceState
     *      Given savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Sets the current activity view when the app is first opened
        setContentView(R.layout.activity_main);

        //Init fragments
        chatFragment = new ChatFragment();
        classworkFragment = new ClassworkFragment();
        queueFragment = new QueueFragment();
        settingsFragment = new SettingsFragment();

        //Sets the fragment to be displayed in the current activity
        setFragment(chatFragment);

        //Init the bottom navigation bar and the listener
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.mainNavBar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Sets the toolbar as the action bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Keyboard Listener to determine when the keyboard is open or closed
        KeyboardVisibilityEvent.setEventListener(this, new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                if (isOpen)
                {
                    //Hides the bottom navigation and scrolls the list to the bottom
                    navigation.setVisibility(View.GONE);
                    ChatFragment.scrollMyListViewToBottom();
                }
                else {
                    //Shows the bottom navigation and scrolls the list to the bottom
                    navigation.setVisibility(View.VISIBLE);
                    ChatFragment.scrollMyListViewToBottom();
                }
            }
        });

    }

    /**
     * @param item
     *      Given MenuItem
     * @return
     *      True if the MenuItem was clicked,
     *      false otherwise
     *
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.profileIcon:
                return true;
            case (R.id.home):
                finish();
                return true;
            default:
              return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Listener for the bottom Navigation bar
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigationChat:
                    setFragment(chatFragment);
                    return true;
                case R.id.navigationQueue:
                    setFragment(queueFragment);
                    return true;
                case R.id.navigationClasswork:
                    setFragment(classworkFragment);
                    return true;
                case R.id.navigationSettings:
                    setFragment(settingsFragment);
                    return true;
            }
            return false;
        }
    };


    /**
     * @param frag
     *      Given fragment to change to
     */
    private void setFragment(Fragment frag){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, frag);
        fragmentTransaction.commit();
    }

    /**
     * @param menu
     *      given menu
     * @return
     *      true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}
