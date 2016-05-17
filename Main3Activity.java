package locationtestone.app.com.locationtestone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    private EditText fnumber, snumber, tnumber;
    private Button submit;
    private dataBaseHandler dataBaseHandler = new dataBaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        fnumber = (EditText) findViewById(R.id.first_mobile_id);
        snumber = (EditText) findViewById(R.id.second_mobile);
        tnumber = (EditText) findViewById(R.id.third_mobile);
        submit = (Button) findViewById(R.id.submit_mobile);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((fnumber.getText().toString().isEmpty()) || (snumber.getText().toString().isEmpty())
                        || (tnumber.getText().toString().isEmpty())){
                    Toast.makeText(getApplicationContext(), "Enter valid number", Toast.LENGTH_LONG).show();
                }
                else{
                    saveNumbers();
                    Intent i = new Intent(Main3Activity.this, MainActivity.class);
                    startActivity(i);

                }
            }
        });



    }

    private void saveNumbers() {
        userDetails user = new userDetails();
        user.setFirstNum("+91"+fnumber.getText().toString().trim());
        user.setSecondNum("+91"+snumber.getText().toString().trim());
        user.setThirdNum("+91"+tnumber.getText().toString().trim());
        dataBaseHandler.addUserDetails(user);
        dataBaseHandler.close();

    }
}
