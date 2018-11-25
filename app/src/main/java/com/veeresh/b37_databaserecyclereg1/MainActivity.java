package com.veeresh.b37_databaserecyclereg1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    //make it public so that fragments can access this variable.
    public MyDatabase m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.container, new MyFragment()).commit();
        m = new MyDatabase(this);
        m.open();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        m.close();
    }
}
