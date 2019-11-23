package fmt.universitymanagementsystem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Login extends AppCompatActivity {


    Button L_UNIVERSITY, L_COLLEGE, L_STUDENT, L_U_LOGIN, L_C_LOGIN, L_S_LOGIN;

    private LinearLayout L_U_LAYOUT, L_C_LAYOUT, L_S_LAYOUT;

    private EditText L_U_ID, L_U_PASSWORD, L_C_ID, L_C_PASSWORD, L_S_ID, L_S_PASSWORD;

    private String user_type, user_id, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        L_UNIVERSITY = (Button) findViewById(R.id.l_university);
        L_COLLEGE = (Button) findViewById(R.id.l_college);
        L_STUDENT = (Button) findViewById(R.id.l_student);

        L_U_LOGIN = (Button) findViewById(R.id.l_u_login);
        L_C_LOGIN = (Button) findViewById(R.id.l_c_login);
        L_S_LOGIN = (Button) findViewById(R.id.l_s_login);

        L_U_LAYOUT = (LinearLayout) findViewById(R.id.l_u_layout);
        L_C_LAYOUT = (LinearLayout) findViewById(R.id.l_c_layout);
        L_S_LAYOUT = (LinearLayout) findViewById(R.id.l_s_layout);

        L_U_ID = (EditText) findViewById(R.id.l_u_id);
        L_U_PASSWORD = (EditText) findViewById(R.id.l_u_password);
        L_C_ID = (EditText) findViewById(R.id.l_c_id);
        L_C_PASSWORD = (EditText) findViewById(R.id.l_c_password);
        L_S_ID = (EditText) findViewById(R.id.l_s_id);
        L_S_PASSWORD = (EditText) findViewById(R.id.l_s_password);


        L_UNIVERSITY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                L_U_LAYOUT.setVisibility(View.VISIBLE);
                L_C_LAYOUT.setVisibility(View.GONE);
                L_S_LAYOUT.setVisibility(View.GONE);
            }
        });

        L_COLLEGE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                L_U_LAYOUT.setVisibility(View.GONE);
                L_C_LAYOUT.setVisibility(View.VISIBLE);
                L_S_LAYOUT.setVisibility(View.GONE);
            }
        });

        L_STUDENT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                L_U_LAYOUT.setVisibility(View.GONE);
                L_C_LAYOUT.setVisibility(View.GONE);
                L_S_LAYOUT.setVisibility(View.VISIBLE);
            }
        });


        L_U_LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_type = "admin";

                user_id = L_U_ID.getText().toString();
                password = L_U_PASSWORD.getText().toString();

                if(TextUtils.isEmpty(user_id))
                    L_U_ID.setError("Type in your Id !");

                else if(TextUtils.isEmpty(password))
                    L_U_PASSWORD.setError("Type in your Password !");

                else {

                    LoginBackgroundTask loginBackgroundTask = new LoginBackgroundTask(Login.this);
                    loginBackgroundTask.execute(user_type, user_id, password);

                    L_U_LAYOUT.setVisibility(View.GONE);
                    L_U_ID.setText("");
                    L_U_PASSWORD.setText("");

                }

            }
        });

        L_C_LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_type = "college";

                user_id = L_C_ID.getText().toString();
                password = L_C_PASSWORD.getText().toString();

                if(TextUtils.isEmpty(user_id))
                    L_C_ID.setError("Type in your College Id !");

                else if(TextUtils.isEmpty(password))
                    L_C_PASSWORD.setError("Type in your Password !");

                else {

                    LoginBackgroundTask loginBackgroundTask = new LoginBackgroundTask(Login.this);
                    loginBackgroundTask.execute(user_type, user_id, password);

                    L_C_LAYOUT.setVisibility(View.GONE);
                    L_C_ID.setText("");
                    L_C_PASSWORD.setText("");

                }

            }
        });

        L_S_LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_type = "student";

                user_id = L_S_ID.getText().toString();
                password = L_S_PASSWORD.getText().toString();

                if(TextUtils.isEmpty(user_id))
                    L_S_ID.setError("Type in your Id !");

                else if(TextUtils.isEmpty(password))
                    L_S_PASSWORD.setError("Type in your Password !");

                else {

                    LoginBackgroundTask loginBackgroundTask = new LoginBackgroundTask(Login.this);
                    loginBackgroundTask.execute(user_type, user_id, password);

                    L_S_LAYOUT.setVisibility(View.GONE);
                    L_S_ID.setText("");
                    L_S_PASSWORD.setText("");

                }

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class LoginBackgroundTask extends AsyncTask<String, Void, String> {

        ProgressDialog pDialog;

        Context ctx;

        LoginBackgroundTask(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Logging In ... ");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

             String user_type = params[0];
             String user_id = params[1];
             String password = params[2];

                try {

                    URL url = new URL("http://10.0.3.2/ums/login.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));


                    String data = URLEncoder.encode("user_type", "UTF-8") + "=" + URLEncoder.encode(user_type, "UTF-8")
                            + "&" + URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8")
                            + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));
                    String response = "";
                    String line;

                    while((line = bufferedReader.readLine())!=null)  {
                        response += line;
                    }
                    bufferedReader.close();
                    IS.close();
                    httpURLConnection.disconnect();
                    return response;

                } catch (IOException e) {
                    e.printStackTrace();
                }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            switch(result){

                case "Login Successful !":

                    Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Login.this, Home.class);
                    intent.putExtra("user_type", user_type);
                    intent.putExtra("user_id", user_id);
                    startActivity(intent);

                    pDialog.dismiss();

                    break;

                case "Id and Password does not match !":

                    Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                    pDialog.dismiss();

                    break;

                default:

                    Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                    pDialog.dismiss();

                    break;

            }

        }

    }

}
