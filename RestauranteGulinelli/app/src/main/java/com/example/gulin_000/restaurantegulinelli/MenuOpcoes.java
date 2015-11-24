package com.example.gulin_000.restaurantegulinelli;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MenuOpcoes extends Activity {

    String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opcoes);

        Intent it = getIntent();
        if (it != null){
            Bundle params = it.getExtras();
            if (params != null){
                this.login = params.getString("login");
                System.out.println("MENUOPÇOES AQUI ESTA O LOGIN" + this.login);
            }
        }

    }

    public void novoPedido(View view){
        Intent intent = new Intent(this, NovoPedido.class);
        Bundle bundle = new Bundle();
        bundle.putString("login", login);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_opcoes, menu);
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
