package com.example.dell.viewpagerdesign;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.nightonke.boommenu.Util;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        bmb.setButtonEnum(ButtonEnum.TextInsideCircle);
        bmb.setRippleEffect(true);
       // bmb.setBackgroundResource(R.mipmap.ic_heart_empty);
      bmb.setNormalColor(Color.argb(00,00,00,00));
     //   bmb.setAlpha(.5f);
       // bmb.setHighlightedColor(Color.GRAY);
       // bmb.setUnableColor(Color.WHITE);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_3_4);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_3_4);

        bmb.addBuilder(new TextInsideCircleButton.Builder()
                .normalImageRes(R.drawable.icon_home)
                .maxLines(1)
                .imagePadding(new Rect(0,0,0,40))
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        Toast.makeText(getApplicationContext(),"boom clicked",Toast.LENGTH_SHORT).show();
                    }
                })
                .textGravity(Gravity.CENTER)
                .normalText("Home"));
        bmb.addBuilder(new TextInsideCircleButton.Builder()
                .normalImageRes(R.drawable.coffee)
                .maxLines(1).imageRect(new Rect(Util.dp2px(10), Util.dp2px(10), Util.dp2px(70), Util.dp2px(70)))
                .imagePadding(new Rect(0,0,0,40))
                .textGravity(Gravity.CENTER)

                .normalText("Coffee"));
        bmb.addBuilder(new TextInsideCircleButton.Builder()
                .normalImageRes(R.drawable.coffee)
                .imagePadding(new Rect(0,0,0,40))
                .maxLines(1)
                .normalText("Coffee"));

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        AddFragment add = new AddFragment();
        ft.add(R.id.addHolder,add);
        ft.commit();

    }
}
