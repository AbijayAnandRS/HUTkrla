package com.abijayana.user.hutkrla;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import static org.json.JSONObject.NULL;


/**
 * Created by user on 16-03-2017.
 */

public class singlefood extends AppCompatActivity {
LinearLayout lf;ImageView iv;TextView tv1,tv2,tv3;
    SharedPreferences sf1,sf2,sf3,key,nde;Firebase fr,gr,sr;Button df,dce,incre,numb;int numpase=0;String profod,itemname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.singlefood);
        lf=(LinearLayout)findViewById(R.id.lnyrlyt1);
        iv=(ImageView)findViewById(R.id.imageView);
        tv1=(TextView)findViewById(R.id.tvsf1);
        tv2=(TextView)findViewById(R.id.tvsf2);
        tv3=(TextView)findViewById(R.id.tvsf3);
        LinearLayout layout=(LinearLayout)findViewById(R.id.lnyrlyt1) ;

        df=(Button)findViewById(R.id.order_food1) ;
        dce=(Button)findViewById(R.id.btdecre);
        numb=(Button)findViewById(R.id.btnmbr) ;
        incre=(Button)findViewById(R.id.btincre) ;
        incdec();
        gr=new Firebase("https://lllsstfffuuddd.firebaseio.com/KERALAHUT");
        fr=gr.child("food");

        sf1=this.getSharedPreferences("FNME",MODE_PRIVATE);
        sf2=this.getSharedPreferences("FPRCE",MODE_PRIVATE);
        sf3=this.getSharedPreferences("URRRL",MODE_PRIVATE);
        key=this.getSharedPreferences("KKY",MODE_PRIVATE);
        nde=this.getSharedPreferences("NDDDEE",MODE_PRIVATE);


        String node=nde.getString("NODE","veg");
        if(node.compareTo("veg")==0){
            layout.setBackgroundResource(R.mipmap.veg);
        }
        else   if(node.compareTo("non")==0){
            layout.setBackgroundResource(R.mipmap.nonvega);
        }
        else   if(node.compareTo("others")==0){
            layout.setBackgroundResource(R.mipmap.oth);
        }
        String nmb=key.getString("KEY","0");

        df.setOnClickListener(new View.OnClickListener() {
            Button order;
            EditText edname,edroll,edphne;
            TextView prc;RelativeLayout lyh;
            @Override
            public void onClick(View v) {
                if(numpase==0)error("Add food to order");
                else {

                    final Dialog d = new Dialog(singlefood.this);
                    d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    d.setContentView(R.layout.order);
                    d.show();
                    d.setCancelable(true);
                    order = (Button) d.findViewById(R.id.buttonorder);
                    lyh=(RelativeLayout)d.findViewById(R.id.loadingPanelk);lyh.setVisibility(View.INVISIBLE);
                    edname = (EditText) d.findViewById(R.id.input_name1);
                    edroll = (EditText) d.findViewById(R.id.editTextroll);
                    edphne = (EditText) d.findViewById(R.id.editTextphonr);
                    prc=(TextView)d.findViewById(R.id.textViewprice) ;
                    prc.setText("MRP RS:"+numpase*stringtoint(profod));
                    checkedtxt(edname);
                    checkedtxt(edroll);
                    checkedtxt(edphne);
                    order.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (find_uiuy(edname) * find_uiuy(edroll) * find_uiuy(edphne) == 0) {
                                error("Fill Properly");
                            }
                            else if(stringtoint(profod)==0){
                                error("Not Availiable Now");
                            }else {
                                order.setVisibility(View.INVISIBLE);
                                lyh.setVisibility(View.VISIBLE);
                                String timestamp=String.valueOf(System.currentTimeMillis());
                                HashMap<String,Object> abi=new HashMap<String, Object>();
                                abi.put("name",edname.getText().toString());
                                abi.put("item",numpase+" "+itemname+" Total :MRP"+stringtoint(profod)*numpase);
                                abi.put("rollno",edroll.getText().toString());
                                abi.put("phone",edphne.getText().toString());
                                abi.put("confirm","No");
                                gr.child("orders").child(timestamp).updateChildren(abi, new Firebase.CompletionListener() {
                                    @Override
                                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                                     if(firebaseError==NULL)error("ERROR IN ORDER");
                                        else {
                                         order.setVisibility(View.VISIBLE);
                                         d.cancel();
                                         numpase = 0;
                                         numb.setText(""+numpase);
                                         final Dialog h = new Dialog(singlefood.this);
                                         h.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                         h.setContentView(R.layout.succesorder);
                                         h.setCancelable(true);
                                         h.show();
                                         TextView fg=(TextView)h.findViewById(R.id.textView4uuu);
                                         fg.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 Intent i=new Intent(singlefood.this,contactus.class);
                                                 startActivity(i);
                                             }
                                         });
                                         final Button bhy = (Button) h.findViewById(R.id.buttonyopgt);
                                         bhy.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {

                                                 h.cancel();

                                             }
                                         });

                                     }
                                    }
                                });




                            }

                        }
                    });
                }

            }
        });
        fr.child(node).child(nmb).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemname=String.valueOf(dataSnapshot.child("name").getValue());
                tv1.setText(itemname);
                profod=String.valueOf(dataSnapshot.child("price").getValue());
                tv2.setText(profod);
                tv3.setText(String.valueOf(dataSnapshot.child("desr").getValue()));
                Picasso.with(singlefood.this).load(String.valueOf(dataSnapshot.child("url").getValue())).into(iv);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });




    }
    public void checkedtxt(final EditText edtsd){
        edtsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtsd.getText().toString().isEmpty()) edtsd.setHint("");
            }
        });






    }
    public int find_uiuy(EditText uio){

        if(uio.getText().toString().isEmpty()){
            uio.setError("Fill properly");
            return 0;
        }
        else return 1;
    }
    public void error(String Message){

        Toast.makeText(singlefood.this,Message,Toast.LENGTH_SHORT).show();

    }
    public void incdec(){
        incre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numpase++;
                numb.setText(""+numpase);
            }
        });
        dce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numpase>0){numpase--;
                numb.setText(""+numpase);
                }

            }
        });





    }
    public int stringtoint(String s){
        String g="";int y;int i;

        for(i=0;(i<s.length()&&(s.charAt(i)!='/'));i++){

            y=(int)s.charAt(i);
            if((y>=48)&&(y<=57)){

                g=g+String.valueOf(s.charAt(i));

            }

        }



        if(g.compareTo("")==0)return 0;


        int h=Integer.parseInt(g);
        return h;


    }
}
