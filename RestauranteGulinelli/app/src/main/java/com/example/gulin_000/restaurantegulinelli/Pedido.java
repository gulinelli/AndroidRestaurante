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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Pedido extends Activity {

    private TextView textViewItem;
    private String login;
    private String nomeItem;
    private EditText editTextQuantidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        textViewItem = (TextView)findViewById(R.id.textViewItem);
        editTextQuantidade = (EditText)findViewById(R.id.editTextQuantidade);

        Intent it = getIntent();
        if (it != null){
            Bundle params = it.getExtras();
            if (params != null){
               this.login = params.getString("login");
                System.out.println("PEDIDO aqui esta o login " + login);
                this.nomeItem = params.getString("nomeItem");
            }
        }

        textViewItem.setText(nomeItem);

    }

    public void pedir(View view){
        final String quantidade = editTextQuantidade.getText().toString();

        new Thread(){
            public void run(){
                String url = "http://10.0.2.2:8080/Restaurante/Pedido";
                WebService ws = new WebService(url);
                Map params = new HashMap();
                params.put("loginUsuario", login);
                params.put("nomeItem", nomeItem);
                params.put("quantidade", quantidade);

                String response = ws.webGet("", params);

                try{
                    JSONObject json = new JSONObject(response);
                    String out = json.getString("message").toString();

                    Bundle b = new Bundle();
                    b.putString("message", out);

                    Message msg = new Message();
                    msg.setData(b);
                    handler.sendMessage(msg);

                }catch (JSONException e1){
                    e1.printStackTrace();
                }
            }
        }.start();

    }

    private Handler handler = new Handler(){

        public void handleMessage(Message msg){
            String out = (String)msg.getData().getString("message");
            if (out.equals("correto")){
                Intent intent = new Intent(Pedido.this, MenuOpcoes.class);
                startActivity(intent);
            }else{
                Toast msgErro = Toast.makeText(getApplicationContext(), "Erro com o pedido", Toast.LENGTH_LONG);
                msgErro.show();
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pedido, menu);
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
