package com.example.dell.viewpagerdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dell on 3/8/2017.
 */

public class AddFragment extends Fragment
{
    final static int counter=0;
    private ArrayList<Button> bholder = new ArrayList<>();
    private ViewPager viewPager;
    private boolean boolSuccess = false;
    private ImageButton imageButton;
    private PagerCustomAdapter pca;
    private static final String URL = "http://10.0.2.2/joffers/joffer.api.getaddData.php";
    private ArrayList<HashMap<String,String>> premData = new ArrayList<>();
    private LinearLayout l;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.pagerholder,container,false);
        Bundle b = this.getArguments();
     //   int section = b.getInt("section");
        l =(LinearLayout)v.findViewById(R.id.buttonHolder);
        imageButton = (ImageButton)v.findViewById(R.id.favButton1);

        viewPager=(ViewPager)v.findViewById(R.id.viewPagerAddSec1);
        viewPager.setPageTransformer(false,new ZoomOutPageTransformer());
        for(int i =0 ; i< 4;i++)
        {
            l.addView(buttonMaker());
        }
      //  viewPager.setClipToPadding(false); //
      //  viewPager.setPadding(50,0,50,0);// preview of next image

      //  viewPager.setPageMargin(20);//
//     /
        viewPager.setAdapter(new PagerCustomAdapter(getContext(),viewPager));
       // getAddData(section+"");







        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int pos= 0;

            }

            @Override
            public void onPageSelected(int position) {


                for(int i=0; i<bholder.size();i++)
                {

                    if(i==position)
                    {
                        Button tempButton1 = bholder.get(position);
                        tempButton1.setBackgroundResource(R.drawable.dots);
                        continue;
                    }
                    else if(i!=position)
                    {
                        Button tempButton1 = bholder.get(i);
                        tempButton1.setBackgroundResource(R.drawable.dotsmall);
                    }
                }



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return v;
    }


    Button buttonMaker()
    {
        Button b = new Button(getActivity());
        //    b.setMaxHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //  b.setMinimumWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        if(bholder.size()==0)
        {
            b.setBackgroundResource(R.drawable.dots);
        }
        else
            b.setBackgroundResource(R.drawable.dotsmall);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1.0f);
        b.setLayoutParams(params);
        bholder.add(b);


        return b;
    }


    /*
    public void getAddData(final String section){
        RequestQueue requestQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String jsonArray) {
                        Log.d("fetching filter", jsonArray);
                        parseJson(jsonArray);
                        pca = new PagerCustomAdapter(getContext(),getFragmentManager(), premData);
                        viewPager.setAdapter(pca);

                        for(int i=0 ;i<pca.getViewNumber();i++)
                        {

                            l.addView(buttonMaker());
                        }
                        //Log.d("parsed list value 1",""+mCompanyList.get(0).companyName);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Volley Error", ""+error);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("section", section);
                return params;
            }
        };

        requestQueue.add(stringRequest);
        //   requestQueue.getCache().clear();

    } */
    public void parseJson(String json) {
        JSONObject jsonObject = null;
        Log.e("parseJSON", ""+json);
        try
        {
            jsonObject = new JSONObject(json);
            int success = jsonObject.getInt("success");
            if(success == 0)
            {
                Toast.makeText(getContext(),"No matches found", Toast.LENGTH_SHORT);
                Log.d("JSONError","No response form Server");
                boolSuccess = false;
            }
            else
            {
                boolSuccess = true;
                Log.d("Success",""+success);
                JSONArray payload = jsonObject.getJSONArray("payload");

                //Log.e("parseJSON", "mCompanies size initial "+premiumAddDetailses.size());
                for(int j =0; j < payload.length();j++){
                    Log.d("premuim","data");

                    HashMap<String,String> data = new HashMap<>();

                    JSONObject jobj = payload.getJSONObject(j);
                    //Log.d("JSONError","Unable to parse response\n"+jobj.getString("company_name"));
                    String[] values = new String[7];
                    data.put("company_name",jobj.getString("company_name"));
                    data.put("company_registeration_no",jobj.getString("company_registeration_no"));
                    data.put("add_id",jobj.getString("add_id"));
                    data.put("add_url",jobj.getString("add_url"));
                    data.put("add_tncs", jobj.getString("add_tncs"));
                    data.put("discount",jobj.getString("discount"));
                    data.put("main_categories", jobj.getString("main_categories"));
                    premData.add(data);
                        /*companies[j].companyName = jobj.getString("company_name");
                        companies[j].companyRegNo = jobj.getString("company_registeration_no");
                        companies[j].mainCategory = jobj.getString("main_categories");*/
                    // premiumAddDetailses.add(new PremiumAddDetails(values));
                }

                        /*Gson gson = new Gson();
                        Company[] companies = gson.fromJson(payload.toString(), Company[].class);*/
                //mCompanies = Arrays.asList(companies);
            }
        }
        catch (JSONException je)
        {
            Log.d("JSONError","Unable to parse response\n"+je);
        }

    }


}
