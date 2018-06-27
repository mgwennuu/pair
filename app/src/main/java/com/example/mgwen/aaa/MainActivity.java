package com.example.mgwen.aaa;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {
    Button[] btA;
    Button btx,bty;
    CharSequence s;
    private int dataset[]=new int[16];
    Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linear=(LinearLayout)findViewById(R.id.linear);
        TableRow row[]=new TableRow[4];
        row[0]=(TableRow)findViewById(R.id.row1);
        row[1]=(TableRow)findViewById(R.id.row2);
        row[2]=(TableRow)findViewById(R.id.row3);
        row[3]=(TableRow)findViewById(R.id.row4);
        btA= new Button[16];
        btx=bty=null;
        for(int i=0;i<16;i++){
            dataset[i]=1+i/2;
            btA[i]=new Button(this);
            btA[i].setText("test");
            btA[i].setHint("" +i);
            btA[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(btx==null) {
                        btx = (Button) view;
                        s = ""+dataset[Integer.parseInt(btx.getHint().toString())];
                        btx.setText(s);
                        btx.setEnabled(false);
                    }else if(bty==null){
                        bty=(Button)view;
                        CharSequence s2=""+dataset[Integer.parseInt(bty.getHint().toString())];
                        bty.setText(s2);
                        if(s.equals(s2)) {
                            bty.setEnabled(false);
                            btx=bty=null;
                        }else{
                            handler.postDelayed(updateTimer, 1000);
                        }
                    }
                }
            });
            row[i/4].addView(btA[i]);
        }
        for(int i=0;i<100;i++){
            for(int j=0;j<16;j++){
                int t,idx;
                idx= (int) (Math.random()*16);
                t=dataset[j]; dataset[j]=dataset[idx]; dataset[idx]=t;
            }
        }

    }
    private Runnable updateTimer = new Runnable() {
        public void run() {
            btx.setText("test");
            btx.setEnabled(true);
            bty.setText("test");
            btx=bty=null;
            //handler.postDelayed(this, 1000);
        }
    };
}
