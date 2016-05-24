package locationtestone.app.com.locationtestone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private EditText fname, lname;
    private Button submit_but;
    private dataBaseHandler dataBaseHandler = new dataBaseHandler(Main2Activity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SharedPreferences settings = getSharedPreferences("prefs", 0);
        boolean firstRun = settings.getBoolean("firstRun", false);
        if (!firstRun && dataBaseHandler.userInformation().getFirstNam() == null)//if running for first time
        //Splash will load for first time
        {
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("firstRun", true);
            editor.commit();
            fname = (EditText) findViewById(R.id.first_name_id);
            lname = (EditText) findViewById(R.id.last_name_id);
            submit_but = (Button) findViewById(R.id.submit_id);

            submit_but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((fname.getText().toString().isEmpty()) || (lname.getText().toString().isEmpty())) {
                        Toast.makeText(getApplicationContext(), "Invalid entry", Toast.LENGTH_LONG).show();
                    } else {
                        savetoDb();
                        Intent i = new Intent(Main2Activity.this, Main3Activity.class);
                        startActivity(i);
                    }
                }
            });
        } else {

            Intent a = new Intent(Main2Activity.this, MainActivity.class);
            startActivity(a);
            finish();
        }
    }

    private void savetoDb() {
        userDetails user = new userDetails();
        user.setFirstNam(fname.getText().toString().trim());
        user.setLastNam(lname.getText().toString().trim());
        dataBaseHandler.addUserDetails(user);
        //Toast.makeText(getApplicationContext(),dataBaseHandler.userInformation().getFirstNam(),Toast.LENGTH_SHORT).show();
        dataBaseHandler.close();


    }

}
