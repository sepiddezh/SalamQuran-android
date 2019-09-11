package com.ermile.salamquran.Actitvty;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.duolingo.open.rtlviewpager.RtlViewPager;
import com.ermile.salamquran.Adaptor.QuranAdaptor;
import com.ermile.salamquran.Item.itemQuran.ayat;
import com.ermile.salamquran.MyDatabase;
import com.ermile.salamquran.R;

import java.util.List;

public class Quran extends AppCompatActivity {

    private TextView number_pageQuran , number_juzQuran,title_surahQuran;

    View stop,play;
    List<ayat> ayatList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);

        stop = findViewById(R.id.audio_stop);
        play = findViewById(R.id.audio_play);

        


        // Chang ID XML
        number_pageQuran = findViewById(R.id.number_pageQuran);
        number_juzQuran = findViewById(R.id.number_juzQuran);
        title_surahQuran = findViewById(R.id.title_surahQuran);
        RtlViewPager viewpager = findViewById(R.id.view_pagers); // view page in XML

        // set
        QuranAdaptor PagerAdapter = new QuranAdaptor(getApplicationContext()); // add Adapter (in line 55)
        viewpager.setAdapter(PagerAdapter); // set Adapter to View pager in XML



        viewpager.setCurrentItem(Integer.valueOf(getIntent().getStringExtra("open_page")));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            @Override
            public void onPageSelected(int position) {

                SQLiteDatabase mydb = new MyDatabase(getApplicationContext()).getWritableDatabase();
                Cursor pageData = mydb.rawQuery("SELECT * FROM quran_word WHERE page="+position, null);
                while (pageData.moveToNext()){

                }


            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

    }
}
