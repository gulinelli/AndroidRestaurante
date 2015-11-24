package com.example.gulin_000.restaurantegulinelli;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText loginEditText;
    private EditText senhaEditText;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginEditText = (EditText)findViewById(R.id.editTextUsuario);
        senhaEditText = (EditText)findViewById(R.id.editTextSenha);

        intent = new Intent(this, MenuOpcoes.class);
    }

    public void login(View view){
        final String login = loginEditText.getText().toString();
        final String senha = senhaEditText.getText().toString();

        new Thread(){
            public void run(){
                String url = "http://10.0.2.2:8080/Restaurante/Login";
                WebService ws = new WebService(url);
                Map params = new HashMap();
                params.put("login", login);
                params.put("senha", senha);

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
                Bundle bundle = new Bundle();
                bundle.putString("login", loginEditText.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }else{
                Toast msgErro = Toast.makeText(getApplicationContext(), "Usuário/senha incorretos", Toast.LENGTH_LONG);
                msgErro.show();
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
