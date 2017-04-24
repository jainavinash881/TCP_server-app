package com.jain.avinash.server;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public abstract class MainActivity extends Activity implements  DataDisplay {

    TextView serverMessage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serverMessage = (TextView)findViewById(R.id.textView);

    }

    public void connect(View view) {

        MyServer server= new MyServer();

        server.setEventListner(this);

        server.startListening();
    }

    public void display(String message) {


        serverMessage.setText(""+message);



    }

}
