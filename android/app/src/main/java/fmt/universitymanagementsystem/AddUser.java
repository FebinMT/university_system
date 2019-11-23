package fmt.universitymanagementsystem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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

public class AddUser extends AppCompatActivity {


    String user_type;

    LinearLayout A_C, A_S;

    TextView A_TYPE;

    EditText A_C_NAME, A_C_PASSWORD, A_S_NAME, A_S_PASSWORD,
            A_S_DOB, A_S_COLLEGE, A_S_DEPT, A_S_YEAR, A_S_SEM;

    Button A_C_SUBMIT, A_S_SUBMIT;

    String a_c_name, a_c_password, a_s_name, a_s_password, a_s_dob, a_s_college_id, a_s_dept, a_s_year, a_s_sem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        A_C = (LinearLayout) findViewById(R.id.add_c);
        A_S = (LinearLayout) findViewById(R.id.add_s);

        A_TYPE = (TextView) findViewById(R.id.add_type);

        A_C_NAME = (EditText) findViewById(R.id.add_c_name);
        A_C_PASSWORD = (EditText) findViewById(R.id.add_c_password);

        A_S_NAME = (EditText) findViewById(R.id.add_s_name);
        A_S_PASSWORD = (EditText) findViewById(R.id.add_s_password);
        A_S_DOB = (EditText) findViewById(R.id.add_s_dob);
        A_S_COLLEGE = (EditText) findViewById(R.id.add_s_college);
        A_S_DEPT = (EditText) findViewById(R.id.add_s_department);
        A_S_YEAR = (EditText) findViewById(R.id.add_s_year);
        A_S_SEM = (EditText) findViewById(R.id.add_s_semester);


        A_C_SUBMIT = (Button) findViewById(R.id.add_c_submit);
        A_S_SUBMIT = (Button) findViewById(R.id.add_s_submit);

        Intent intent = getIntent();

        user_type = intent.getStringExtra("user_type");


        if(user_type.equals("college")) {

            A_TYPE.setText("ADD COLLEGE");
            A_C.setVisibility(View.VISIBLE);
            A_S.setVisibility(View.GONE);

            A_C_SUBMIT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    a_c_name = A_C_NAME.getText().toString();
                    a_c_password = A_C_PASSWORD.getText().toString();

                    if (TextUtils.isEmpty(a_c_name))
                        Toast.makeText(AddUser.this, "Type in the College Name !", Toast.LENGTH_LONG).show();

                    else if (TextUtils.isEmpty(a_c_password))
                        Toast.makeText(AddUser.this, "Type in the College Password !", Toast.LENGTH_LONG).show();

                    else {

                        AddUserBackgroundTask addUserBackgroundTask = new AddUserBackgroundTask(AddUser.this);
                        addUserBackgroundTask.execute(user_type, a_c_name, a_c_password);

                    }

                }
            });

        } else if (user_type.equals("student")) {

            A_TYPE.setText("ADD STUDENT");
            A_S.setVisibility(View.VISIBLE);
            A_C.setVisibility(View.GONE);

            A_S_SUBMIT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    a_s_name = A_S_NAME.getText().toString();
                    a_s_password = A_S_PASSWORD.getText().toString();
                    a_s_dob = A_S_DOB.getText().toString();
                    a_s_college_id = A_S_COLLEGE.getText().toString();
                    a_s_dept = A_S_DEPT.getText().toString();
                    a_s_year = A_S_YEAR.getText().toString();
                    a_s_sem = A_S_SEM.getText().toString();

                    if (TextUtils.isEmpty(a_s_name))
                        Toast.makeText(AddUser.this, "Type in the Student Name !", Toast.LENGTH_LONG).show();

                    else if (TextUtils.isEmpty(a_s_password))
                        Toast.makeText(AddUser.this, "Type in the Student Password !", Toast.LENGTH_LONG).show();

                    else if (TextUtils.isEmpty(a_s_dob))
                        Toast.makeText(AddUser.this, "Type in the Student Date of Birth !", Toast.LENGTH_LONG).show();

                    else if (TextUtils.isEmpty(a_s_college_id))
                        Toast.makeText(AddUser.this, "Type in the Student College Id !", Toast.LENGTH_LONG).show();

                    else if (TextUtils.isEmpty(a_s_dept))
                        Toast.makeText(AddUser.this, "Type in the Student Department Name !", Toast.LENGTH_LONG).show();

                    else if (TextUtils.isEmpty(a_s_year))
                        Toast.makeText(AddUser.this, "Type in the Student Year of Study !", Toast.LENGTH_LONG).show();

                    else if (TextUtils.isEmpty(a_s_sem))
                        Toast.makeText(AddUser.this, "Type in the Student Semester of Study !", Toast.LENGTH_LONG).show();

                    else {

                        AddUserBackgroundTask addUserBackgroundTask = new AddUserBackgroundTask(AddUser.this);
                        addUserBackgroundTask.execute(user_type, a_s_name, a_s_password, a_s_dob, a_s_college_id, a_s_dept, a_s_year, a_s_sem);

                    }

                }
            });

        }


    }


    private class AddUserBackgroundTask extends AsyncTask<String, Void, String> {

        ProgressDialog pDialog;

        Context ctx;

        AddUserBackgroundTask(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddUser.this);
            pDialog.setMessage("Adding User ... ");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String user_type = params[0];
            String name = "", password = "", dob = "", college_id = "", dept = "", year = "", sem = "";

            if(user_type.equals("college")) {

                name = params[1];
                password = params[2];

            }

            else if(user_type.equals("student")) {

                name = params[1];
                password = params[2];
                dob = params[3];
                college_id = params[4];
                dept = params[5];
                year = params[6];
                sem = params[7];

            }

            try {

                URL url = new URL("http://10.0.3.2/ums/addUser.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                String data = "";

                if(user_type.equals("college"))
                    data = URLEncoder.encode("user_type", "UTF-8") + "=" + URLEncoder.encode(user_type, "UTF-8")
                            + "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8")
                            + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                else if(user_type.equals("student"))
                    data = URLEncoder.encode("user_type", "UTF-8") + "=" + URLEncoder.encode(user_type, "UTF-8")
                            + "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8")
                            + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")
                            + "&" + URLEncoder.encode("dob", "UTF-8") + "=" + URLEncoder.encode(dob, "UTF-8")
                            + "&" + URLEncoder.encode("college_id", "UTF-8") + "=" + URLEncoder.encode(college_id, "UTF-8")
                            + "&" + URLEncoder.encode("dept", "UTF-8") + "=" + URLEncoder.encode(dept, "UTF-8")
                            + "&" + URLEncoder.encode("year", "UTF-8") + "=" + URLEncoder.encode(year, "UTF-8")
                            + "&" + URLEncoder.encode("sem", "UTF-8") + "=" + URLEncoder.encode(sem, "UTF-8");

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

                case "Adding User Successful !":

                    Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                    finish();
                    pDialog.dismiss();

                    break;

                case "Adding User Failed !":

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