package com.example.gulin_000.restaurantegulinelli;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NovoPedido extends Activity {

    ListView list;
    String[] nomeItem = new String[6];
    Double[] valorItem = new Double[6];
    Integer[] imageId ={
            R.drawable.lasanha,
            R.drawable.nhoque,
            R.drawable.petit_gateau,
            R.drawable.mousse,
            R.drawable.suco_laranja,
            R.drawable.coca,
    };

    List<Item> itens;
    Gson gson = new Gson();
    String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);

        Intent it = getIntent();
        if (it != null){
            Bundle params = it.getExtras();
            if(params != null){
                this.login = params.getString("login");
                System.out.println("NOVOPEDIDO AQUI ESTA O LOGIN " + this.login);
            }
        }

        new Thread(){
            public void run(){
                String url = "http://10.0.2.2:8080/Restaurante/NovoPedido";
                WebService ws = new WebService(url);
                Map params = new HashMap();

                params.put("opcao", "NovoPedido");

                String response = ws.webGet("", params);

                try {
                    JSONObject json = new JSONObject(response);
                    String out = json.getString("itens").toString();

                    ListaItens lista = gson.fromJson(out, ListaItens.class);
                    itens = lista.getItens();

                    Bundle b = new Bundle();
                    b.putString("message", String.valueOf(json));

                    Message msg = new Message();
                    msg.setData(b);

                    handler.sendMessage(msg);
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }.start();
    }

    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg != null){
                int c = 0;

                for (Item i : itens){
                    nomeItem[c] = i.getNome();
                    valorItem[c] = i.getValor();
                    c++;
                }

                ListCell adapter = new ListCell(NovoPedido.this, nomeItem, valorItem, imageId);
                list = (ListView)findViewById(R.id.list);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(NovoPedido.this, Pedido.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("nomeItem", nomeItem[+position]);
                        bundle.putString("login", login);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_novo_pedido, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
