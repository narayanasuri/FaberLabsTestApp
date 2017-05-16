package koolkat.faberlabstestapp;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import me.riddhimanadib.library.BottomBarHolderActivity;
import me.riddhimanadib.library.NavigationPage;

public class MainActivity extends BottomBarHolderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavigationPage page1 = new NavigationPage("Performance", ContextCompat.getDrawable(this, R.drawable.ic_home_black_24dp), TilesFragment.newInstance());
        NavigationPage page2 = new NavigationPage("Training", ContextCompat.getDrawable(this, R.drawable.ic_dashboard_black_24dp), Fragment2.newInstance());
        NavigationPage page3 = new NavigationPage("Weight Monitor", ContextCompat.getDrawable(this, R.drawable.ic_notifications_black_24dp), Fragment3.newInstance());
        NavigationPage page4 = new NavigationPage("Setting", ContextCompat.getDrawable(this, R.drawable.ic_settings_black_18dp), Fragment4.newInstance());

        List<NavigationPage> navigationPages = new ArrayList<>();
        navigationPages.add(page1);
        navigationPages.add(page2);
        navigationPages.add(page3);
        navigationPages.add(page4);

        super.setupBottomBarHolderActivity(navigationPages);
    }

}
