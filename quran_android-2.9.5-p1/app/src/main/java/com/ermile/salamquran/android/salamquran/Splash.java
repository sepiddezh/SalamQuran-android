package com.ermile.salamquran.android.salamquran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ermile.salamquran.android.QuranApplication;
import com.ermile.salamquran.android.QuranDataActivity;
import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.salamquran.Intro.IntroActivity;
import com.ermile.salamquran.android.salamquran.Intro.IntroApi;
import com.ermile.salamquran.android.salamquran.Language.LanguageActivity;
import com.ermile.salamquran.android.salamquran.Utility.SaveManager;
import com.ermile.salamquran.android.salamquran.Utility.TempLoginUtil;
import com.ermile.salamquran.android.salamquran.Utility.UserInfo;

import java.util.Locale;

public class Splash extends AppCompatActivity {

  @Override
  protected void onResume() {
    super.onResume();
    switch (UserInfo.getSplash(getApplication())){
      case 0:
        changeLanguage();
        break;

      case 1:
        new IntroApi(getApplication());
        new Handler().postDelayed(this::goIntro,1500);
        break;
      case 2:
        quranActivity();
        break;
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
  }

  private void changeLanguage() {
    SaveManager.get(getApplication()).save_splash(1);
    String deviceLanguage = Locale.getDefault().getLanguage();
    switch (deviceLanguage){
      case "fa":
      case "ar":
        SaveManager.get(getApplication()).save_app_language(deviceLanguage);
        ((QuranApplication) getApplication()).refreshLocale(this, true);
        break;
      default:
        Intent intent = new Intent(this, LanguageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        break;
    }
  }
  private void goIntro() {
    SaveManager.get(getApplication()).save_splash(2);
    Intent intent = new Intent(this, IntroActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    startActivity(intent);
    finish();
  }
  private void quranActivity() {
    Intent intent = new Intent(this, QuranDataActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    startActivity(intent);
    finish();
  }

}
