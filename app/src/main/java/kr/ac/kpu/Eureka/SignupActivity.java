package kr.ac.kpu.Eureka;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by namjung on 2017. 5. 23..
 */

public class SignupActivity extends AppCompatActivity {
    EditText id,password,name,email,phone;
    Button okbtn,cancelbtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        id = (EditText)findViewById(R.id.id);
        password = (EditText)findViewById(R.id.password);
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        phone = (EditText)findViewById(R.id.phone);
        okbtn = (Button)findViewById(R.id.ok);
        cancelbtn = (Button)findViewById(R.id.cancel);

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
