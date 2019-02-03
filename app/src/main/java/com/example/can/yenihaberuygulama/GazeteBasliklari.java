package com.example.can.yenihaberuygulama;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GazeteBasliklari extends AppCompatActivity {

    public static String cumhuriyetGazetesiRss = "https://www.futbolsayfasi.net/feed";
    public static String anayurtGazetesi = "http://gazetekarinca.com/feed/";
    public static String balkanGunluguGazetesi = "http://www.girisimhaber.com/rss.xml";
    public static String dunyaGazetesi = "http://www.diken.com.tr/feed/";
    public static String trtGazetesi = "http://www.trthaber.com/sondakika.rss";
    public static String cnnTurkGazetesi = "http://www.cnnturk.com/feed/rss/news";
    public static String tv5Gazetesi = "http://www.tv5haber.com/rss.php";
    public static String acikGazetesi = "https://www.acikgazete.com/feed/";
    public static String amkSporGazetesi = "http://amkspor.sozcu.com.tr/feed";
    public static String evrenselGazetesi = "https://www.evrensel.net/rss/haber.xml";
    public static String starGazetesi = "http://www.gazeteduvar.com.tr/feed/";
    public static String kursunGazetesi = "http://www.ilk-kursun.com/feed/";


    String[] veriler = {"Cumhuriyet Gazetesi","Anayurt Gazetesi","Balkan Gunlugu gazetesi","Dünya gazetesi",
            "TRT Gazetesi","CnnTurk Gazetesi","Tv5 Gazetesi","Acik Gazetesi","Amk Gazetesi","Evrensel Gazete",
            "Star Gazetesi","Kursun Gazetesi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gazete_basliklari);
        MedipolArrayAdapter adapter = new MedipolArrayAdapter(this,veriler);
        final ListView listem = (ListView)findViewById(R.id.listem);
        listem.setAdapter(adapter);

        listem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                    i1.putExtra("i1",cumhuriyetGazetesiRss);
                    startActivity(i1);
                }else if(i==1){
                    Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                    i1.putExtra("i1",anayurtGazetesi);
                    startActivity(i1);
                }else if(i==2){
                    Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                    i1.putExtra("i1",balkanGunluguGazetesi);
                    startActivity(i1);
                }else if(i==3){
                    Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                    i1.putExtra("i1",dunyaGazetesi);
                    startActivity(i1);
                }else if(i==4){
                    Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                    i1.putExtra("i1",trtGazetesi);
                    startActivity(i1);
                }else if(i==5){
                    Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                    i1.putExtra("i1",cnnTurkGazetesi);
                    startActivity(i1);
                }else if(i==6){
                    Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                    i1.putExtra("i1",tv5Gazetesi);
                    startActivity(i1);
                }else if(i==7){
                    Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                    i1.putExtra("i1",acikGazetesi);
                    startActivity(i1);
                }else if(i==8){
                    Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                    i1.putExtra("i1",amkSporGazetesi);
                    startActivity(i1);
                }
                else if(i==9){
                    Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                    i1.putExtra("i1",evrenselGazetesi);
                    startActivity(i1);
                }else if(i==10){
                    Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                    i1.putExtra("i1",starGazetesi);
                    startActivity(i1);
                }else if(i==11){
                    Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                    i1.putExtra("i1",kursunGazetesi);
                    startActivity(i1);
                }

            }
        });

    }

    public class MedipolArrayAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public MedipolArrayAdapter(Context context, String[] values) {
            super(context, -1, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
            TextView txt = (TextView)rowView.findViewById(R.id.row_textview);
            txt.setText(values[position]);

            return rowView;
        }
    }
}
