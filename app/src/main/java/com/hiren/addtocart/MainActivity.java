package com.hiren.addtocart;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hiren.addtocart.adapter.MyProductAdapter;
import com.hiren.addtocart.adapter.MySearchAdapter;
import com.hiren.addtocart.cartapi.Myretrofit;
import com.hiren.addtocart.model.MyProductData;
import com.hiren.addtocart.roomdatabase.MyDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

RecyclerView rv;
ImageView cartbtn;
TextView cartcount, count;
MyProductAdapter myProductAdapter;
MySearchAdapter mySearchAdapter;

    public static MyDatabase myDatabase;

    List<MyProductData>myProductData = new ArrayList<MyProductData>();
    List<MyProductData>searchlist = new ArrayList<MyProductData>();
    List<MyProductData>finallist = new ArrayList<MyProductData>();

 CircleImageView img;
SearchView sea;
public static int totalprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv=(RecyclerView)findViewById(R.id.res);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(this,2));

        cartbtn=(ImageView)findViewById(R.id.cart_btn);
        cartcount=(TextView)findViewById(R.id.cartcount);
        count=(TextView)findViewById(R.id.count);
        img=(CircleImageView)findViewById(R.id.search);
        sea=(SearchView)findViewById(R.id.searchbar);
        sea.setImeOptions(EditorInfo.IME_ACTION_SEARCH);

        sea.setOnQueryTextListener(this);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sea.getVisibility()==View.VISIBLE){
                    sea.setVisibility(View.GONE);

                }
                else {
                    sea.setVisibility(View.VISIBLE);
                }
            }
        });
        //updatacartcount();
        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MyCartActivity.class));
            }
        });


        myDatabase= Room.databaseBuilder(getApplicationContext(),
                MyDatabase.class,"My_Cart").allowMainThreadQueries().build();

        getdata2();


    }

    private void updatacartcount() {
        if (cartcount==null)return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (myDatabase.cartDao().countCart()==0)
                    cartcount.setVisibility(View.INVISIBLE);
                else {
                    cartcount.setVisibility(View.VISIBLE);
                    cartcount.setText(String.valueOf(myDatabase.cartDao().countCart()));
                }
            }
        });

    }



    private void getdata() {

        Call<List<MyProductData>>call= Myretrofit.getInstance().getMyApi().getProductData();
        call.enqueue(new Callback<List<MyProductData>>() {
            @Override
            public void onResponse(Call<List<MyProductData>> call, Response<List<MyProductData>> response) {
                myProductData=response.body();
                myProductAdapter=new MyProductAdapter(myProductData,MainActivity.this);
                rv.setAdapter(myProductAdapter);
            }

            @Override
            public void onFailure(Call<List<MyProductData>> call, Throwable t) {

            }
        });
    }


    private void getdata2() {
//File file = new File(Environment.getExternalStorageDirectory() + "\\drawable\\emtrim.png");
Uri url=null;
String name="";
String sub="";
String spec="";


        int pr=34;
        String pr2;
        MyProductData data;
for(int a=0; a<=7; a++){
    pr+=a;
    pr2=a + "";

    if(a==0){
         url =Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                 "://" + getResources().getResourcePackageName(R.drawable.emtrim)
                 + '/' + getResources().getResourceTypeName(R.drawable.emtrim) + '/' +
                 getResources().getResourceEntryName(R.drawable.emtrim) );
         name="Entrim";
         sub="Emtrim capsule";
         spec="emtrim 500ml";
    }
    else if(a==1){
        url = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getResources().getResourcePackageName(R.drawable.emmox)
                + '/' + getResources().getResourceTypeName(R.drawable.emmox) + '/' +
                getResources().getResourceEntryName(R.drawable.emmox) );
        name="Emmox";
        sub="Emmox capsule";
        spec="Emmox 100mg";
    } else if(a==2){
        url = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getResources().getResourcePackageName(R.drawable.emtrim_capsule)
                + '/' + getResources().getResourceTypeName(R.drawable.emtrim_capsule) + '/' +
                getResources().getResourceEntryName(R.drawable.emtrim_capsule) );
        name="Entrim Capsule";
        sub="Emtrim capsule";
        spec="emtrim 500ml";
    } else if(a==3){
        url =Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getResources().getResourcePackageName(R.drawable.kez)
                + '/' + getResources().getResourceTypeName(R.drawable.kez) + '/' +
                getResources().getResourceEntryName(R.drawable.kez) );
        name="Kezitil";
        sub="Kel ";
        spec="Kel 500ml";
    } else if(a==4){
        url = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getResources().getResourcePackageName(R.drawable.emyz)
                + '/' + getResources().getResourceTypeName(R.drawable.emyz) + '/' +
                getResources().getResourceEntryName(R.drawable.emyz) );
        name="Emytil";
        sub="Emytil capsule";
        spec="emitl 500ml";
    } else if(a==5){
        url = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getResources().getResourcePackageName(R.drawable.kezitil_500mg)
                + '/' + getResources().getResourceTypeName(R.drawable.kezitil_500mg) + '/' +
                getResources().getResourceEntryName(R.drawable.kezitil_500mg) );
        name="Kezitil";
        sub="kezitil capsule";
        spec="kezitil 500ml";
    }else if(a==6){
        url = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getResources().getResourcePackageName(R.drawable.kezitil_suspension_100ml)
                + '/' + getResources().getResourceTypeName(R.drawable.kezitil_suspension_100ml) + '/' +
                getResources().getResourceEntryName(R.drawable.kezitil_suspension_100ml) );
        name="Kezitil Suspension";
        sub="kezitil Oral";
        spec="kezitil 300ml";
    }
    else if(a==7){
        url = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getResources().getResourcePackageName(R.drawable.kezitil_suspension_100ml)
                + '/' + getResources().getResourceTypeName(R.drawable.kezitil_suspension_100ml) + '/' +
                getResources().getResourceEntryName(R.drawable.kezitil_suspension_100ml) );
        name="Kezitil Suspension";
        sub="kezitil Oral";
        spec="kezitil 300ml";
    }

   data= new MyProductData(a, name,url,pr2,sub, spec, 1);

myProductData.add(data);
}


       // Toast.makeText(this,""+ myProductData.size(), Toast.LENGTH_SHORT).show();
count.setText(myProductData.size() + " " + "Item(s)");
        myProductAdapter=new MyProductAdapter(myProductData,MainActivity.this);
        rv.setAdapter(myProductAdapter);
    }




    @Override
    protected void onResume() {
        super.onResume();
        updatacartcount();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {


        return false;
    }



    @Override
    public boolean onQueryTextChange(String newText) {
//myProductAdapter.re

        if(newText.isEmpty()){
           // myProductAdapter=new MyProductAdapter(myProductData,MainActivity.this);
            myProductAdapter.setFilter(myProductData);
           // myProductAdapter.notifyDataSetChanged();
            count.setText(myProductData.size() + " " + "Item(s)");
        }
ArrayList<MyProductData>  nlist= new ArrayList<>();

      for(MyProductData data : myProductData){
         if(data.getPrname().toLowerCase().contains(newText.toLowerCase())){
             nlist.add(data);
         }
      }

        count.setText(nlist.size() + " " + "Item(s) Found");
        myProductAdapter.setFilter(nlist);
        return true;
    }
}
