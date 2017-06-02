package kr.ac.kpu.Eureka.Tab;

import android.content.Context;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import kr.ac.kpu.Eureka.R;

public enum Tabbar {


  CUSTOM_TAB_ICON_AND_TEXT(R.string.app_title,
      R.layout.demo_custom_tab_icon_and_text) {
    @Override
    public int[] tabs() {
      return tabMenu();
    }
  },
  CUSTOM_TAB_ICON_AND_NOTIFICATION_MARK(R.string.app_title,
          R.layout.demo_custom_tab_icon_and_text) {
    @Override
    public int[] tabs() {
      return tabMenu();
    }

    @Override
    public void startActivity(Context context) {
      TabWithNotificationMarkActivity.startActivity(context, this);
    }
  };

  public final int titleResId;
  public final int layoutResId;

  Tabbar(int titleResId, int layoutResId) {
    this.titleResId = titleResId;
    this.layoutResId = layoutResId;
  }

  public static int[] tabMenu() {
    return new int[] {
        R.string.home,
        R.string.room,
            R.string.setting
    };
  }

  public void startActivity(Context context) {
    TabbarActivity.startActivity(context, this);//context는 mainactivity, this는 Demo의 Basic
  }

  public void setup(final SmartTabLayout layout) {
    //Do nothing.
  }

  public int[] tabs() {
    return tabMenu();
  }

}
