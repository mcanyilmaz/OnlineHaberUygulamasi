package com.example.can.yenihaberuygulama;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class HaberRssOkuyucu extends AsyncTask<Void,Void,Void>  {

    // http://www.trthaber.com/sondakika.rss
    //String rssEkle = "http://www.cnnturk.com/feed/rss/news";
    String rssEkle="";
    URL url;
    Context context;
    ProgressDialog progressDialog;
    ArrayList<HaberVerisi> haberVerisi;
    RecyclerView recyclerView;


    public HaberRssOkuyucu(Context context,RecyclerView recyclerView,String rssEkle) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.rssEkle = rssEkle;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Haber Yükleniyor, Bekleyin..");

    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        Adapter adapter = new Adapter(context,haberVerisi);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    protected Void doInBackground(Void... voids) {
        try {
            ProcessXml(VeriyiAl());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void ProcessXml(Document data) {
        //gelen veri boş değilse
        if (data != null) {
            haberVerisi = new ArrayList<>();
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();
            for(int i = 0; i<items.getLength();i++){
                Node cureentChild=items.item(i);
                if(cureentChild.getNodeName().equalsIgnoreCase("item")){
                    HaberVerisi haberVerisi1 = new HaberVerisi();
                    NodeList itemChilds = cureentChild.getChildNodes();
                    for(int k = 0 ; k<itemChilds.getLength(); k++){
                        Node cureent = itemChilds.item(k);
                        if(cureent.getNodeName().equalsIgnoreCase("title")){
                            haberVerisi1.setBaslik("Haber Başlığı : " + cureent.getTextContent());
                        }else if(cureent.getNodeName().equalsIgnoreCase("link")){
                            haberVerisi1.setLink(cureent.getTextContent());
                        }
                    }
                    haberVerisi.add(haberVerisi1);

                }
            }
        }
    }

    public Document VeriyiAl() throws ParserConfigurationException, SAXException {
        try {
            url = new URL(rssEkle);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document xmlDocumenti = documentBuilder.parse(inputStream);
            return xmlDocumenti;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
