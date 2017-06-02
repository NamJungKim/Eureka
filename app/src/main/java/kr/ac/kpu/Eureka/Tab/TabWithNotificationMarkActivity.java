package kr.ac.kpu.Eureka.Tab;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.Random;

import kr.ac.kpu.Eureka.Data.Global;
import kr.ac.kpu.Eureka.Data.MyInfo;
import kr.ac.kpu.Eureka.HomeFragment;
import kr.ac.kpu.Eureka.InRoom;
import kr.ac.kpu.Eureka.R;
import kr.ac.kpu.Eureka.RoomFragment;

public class TabWithNotificationMarkActivity extends AppCompatActivity implements
    SmartTabLayout.TabProvider {

  private static final String KEY_TABBAR = "tabbar";
  int cnt=0;

  public static void startActivity(Context context, Tabbar tabbar) {
    Intent intent = new Intent(context, TabWithNotificationMarkActivity.class);
    intent.putExtra(KEY_TABBAR, tabbar.name());
    context.startActivity(intent);
  }

  private Random random = new Random(System.currentTimeMillis());

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tab_with_notification_mark);
    final Tabbar tabbar = getTabbar();

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(tabbar.titleResId);
    toolbar.setTitleTextColor(Color.BLACK);

    final Drawable upArrow = getResources().getDrawable(R.drawable.back);
    upArrow.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);

    setSupportActionBar(toolbar);
    getSupportActionBar().setHomeAsUpIndicator(upArrow);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    ViewGroup tab = (ViewGroup) findViewById(R.id.tab);
    tab.addView(LayoutInflater.from(this).inflate(tabbar.layoutResId, tab, false));

    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
    final SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
    tabbar.setup(viewPagerTab);

    FragmentPagerItems pages = new FragmentPagerItems(this);
    for (int titleResId : tabbar.tabs()) {
      if(cnt == 0) {
        pages.add(FragmentPagerItem.of(getString(titleResId), HomeFragment.class));
        cnt++;
      }else if(cnt == 1){
          MyInfo myInfo = new MyInfo();
          if(myInfo.getIsgroup()) {
              pages.add(FragmentPagerItem.of(getString(titleResId), InRoom.class));
          }else{
              pages.add(FragmentPagerItem.of(getString(titleResId), RoomFragment.class));
          }
        cnt++;
      }else if(cnt == 2){
        pages.add(FragmentPagerItem.of(getString(titleResId), RoomFragment.class));
        cnt++;
      }
    }


    FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
            getSupportFragmentManager(), pages);
    viewPagerTab.setCustomTabView(this);
    viewPager.setAdapter(adapter);
    viewPagerTab.setViewPager(viewPager);
    //---------------------------------------------

    viewPagerTab.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override
      public void onPageSelected(int position) {
        super.onPageSelected(position);
        View tab = viewPagerTab.getTabAt(position);
        View mark = tab.findViewById(R.id.custom_tab_notification_mark);
        mark.setVisibility(View.GONE);
      }
    });

    findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        int position = Math.abs(random.nextInt()) % tabbar.tabs().length;
        View tab = viewPagerTab.getTabAt(position);
        View mark = tab.findViewById(R.id.custom_tab_notification_mark);
        mark.setVisibility(View.VISIBLE);
      }
    });

  }

  @Override
  public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
    LayoutInflater inflater = LayoutInflater.from(container.getContext());
    Resources res = container.getContext().getResources();
    View tab = inflater.inflate(R.layout.custom_tab_icon_and_notification_mark, container, false);

    View mark = tab.findViewById(R.id.custom_tab_notification_mark);
    mark.setVisibility(View.GONE);
    ImageView icon = (ImageView) tab.findViewById(R.id.custom_tab_icon);
    TextView title = (TextView) tab.findViewById(R.id.custom_tab_text);
    switch (position) {
      case 0:
        icon.setImageDrawable(res.getDrawable(R.drawable.ic_home_white_24dp));
        title.setText(res.getText(R.string.home));
        break;
      case 1:
        icon.setImageDrawable(res.getDrawable(R.drawable.ic_search_white_24dp));
        title.setText(res.getText(R.string.room));
        break;
      case 2:
        icon.setImageDrawable(res.getDrawable(R.drawable.setting));
        title.setText(res.getText(R.string.setting));
        break;
      default:
        throw new IllegalStateException("Invalid position: " + position);
    }
    return tab;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private Tabbar getTabbar() {
    return Tabbar.valueOf(getIntent().getStringExtra(KEY_TABBAR));
  }

  @Override
  protected void onStart() {
    super.onStart();
    MyInfo myInfo = Global.myinfo;
    if (myInfo.getIsgroup() && myInfo.getFlag()==1) {
      Intent intent = new Intent();
      setIntent(intent);
      finish();
    }
  }
}
