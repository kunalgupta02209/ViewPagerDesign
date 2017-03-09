package com.example.dell.viewpagerdesign;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dell on 3/8/2017.
 */

public class PagerCustomAdapter extends PagerAdapter {

    private int numberOfView;
    private FragmentManager fm;


    // private Picasso p;
     private int[] imageList= {R.drawable.coffee_1_4x3_small,R.drawable.coffee_1_4x3_small,R.drawable.coffee_1_4x3_small,R.drawable.coffee_1_4x3_small};

    private Context mContext;
    LayoutInflater mLayoutInflater;
    private int section;
   // private ImageLoader imageLoader;
   // private NetworkImageView netImage;

    private ArrayList<HashMap<String, String>> premium;

    int getViewNumber() {
        return imageList.length;
    }

    PagerCustomAdapter(Context context, ViewPager viewPager)
    {
       // viewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        mContext=context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /*
    PagerCustomAdapter(Context context, FragmentManager fm, ArrayList<HashMap<String, String>> hashMaps) {
        premium = hashMaps;
        Log.e("constructor", "called");
        //  getAddData(section+"");
        this.fm = fm;
        this.numberOfView = numberOfView;
        this.section = section;
       // premiumAddDetailses = new ArrayList<>();
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    } */

    @Override
    public int getCount() {


       return imageList.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }


    /*
    private void loadImage(int position) {
        //String url = editTextUrl.getText().toString().trim();


        String url = null;
        try {
            url = "http://10.0.2.2/joffers/premium_add/1671531784_techycardia/default.jpg";
            HashMap<String, String> detailsMap = premium.get(position);
            url = "http://10.0.2.2/joffers/premium_add/" + detailsMap.get("company_registeration_no") + "_" + detailsMap.get("company_name") + "/add_1.jpg";

        } catch (NullPointerException np) {
            Log.e("npe", premium.size() + "");

        } catch (IndexOutOfBoundsException ae) {

            Log.e("iobe ", premium.size() + "");
        }


        Log.e("loading image", "volley");
        imageLoader = VolleySingleton.getInstance(mContext)
                .getImageLoader();
        imageLoader.get(url, ImageLoader.getImageListener(netImage,
                R.drawable.ad1, android.R.drawable
                        .ic_dialog_alert));
        netImage.setImageUrl(url, imageLoader);

    } */

    String getUrl(int position) {
        String url = null;
        try {
            HashMap<String, String> detailsMap = premium.get(position);
            url = "http://10.0.2.2/joffers/premium_add/" + detailsMap.get("company_registeration_no") + "_" + detailsMap.get("company_name") + "/add_1.jpg";
        } catch (IndexOutOfBoundsException iobe) {
            url = "http://10.0.2.2/joffers/premium_add/1671531784_techycardia/default.jpg";
        }
        return url;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Log.e("instantiate ", " method called");
        View v = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        final ImageButton imageButton = (ImageButton) v.findViewById(R.id.favButton1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton.setBackgroundResource(R.mipmap.ic_heart_filledd);
            }
        });

        ImageView imageView =(ImageView)v.findViewById(R.id.imageView);
      //  netImage = (NetworkImageView) v.findViewById(R.id.networkImage);
       // loadImage(position);
        //.placeholder(R.id.imageView)
        // Picasso.with(mContext).load("http://10.0.2.2/flyinbuds/far.jpg").resize(640,640).into(imageView);
        imageView.setImageResource(imageList[position]);



        container.addView(v);

        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    HashMap<String, String> getMap(int position) {
        return premium.get(position);
    }


   /* void changeView(int position) {


    /*  Picasso.with(mContext)
                .load(getUrl(position))
                .placeholder(R.mipmap.ic_launcher_expand)   // optional
                .error(R.mipmap.ic_launcher)      // optional
                .resize(400,400)                        // optional
                .into(imageView);

            */
        //  netImage =(NetworkImageView)v.findViewById(R.id.networkImage);

        //  loadImage2(position);
       /* netImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddDetail ad = new AddDetail();

                ad.show(fm, "dialogue");

            }
        });
    } */


/*    private void loadImage2(int position, NetworkImageView netImage) {
        //String url = editTextUrl.getText().toString().trim();

        String url = null;
        try {
            url = "http://10.0.2.2/joffers/premium_add/1671531784_techycardia/default.jpg";
            HashMap<String, String> detailsMap = premium.get(position);
            url = "http://10.0.2.2/joffers/premium_add/" + detailsMap.get("company_registeration_no") + "_" + detailsMap.get("company_name") + "/add_1.jpg";

        } catch (NullPointerException np) {
            Log.e("npe", "");

        } catch (IndexOutOfBoundsException ae) {

            Log.e("iobe ", "");
        }


        Log.e("loading image", "volley");
        ImageLoader imageLoader = VolleySingleton.getInstance(mContext)
                .getImageLoader();
        imageLoader.get(url, ImageLoader.getImageListener(netImage,
                R.drawable.ad1, android.R.drawable
                        .ic_dialog_alert));
        netImage.setImageUrl(url, imageLoader);

     } */

}