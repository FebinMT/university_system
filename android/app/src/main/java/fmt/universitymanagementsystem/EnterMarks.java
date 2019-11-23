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

public class EnterMarks extends AppCompatActivity {

    private EditText M_S_ID, M_S_S1, M_S_S2, M_S_S3, M_S_S4, M_S_S5, M_S_S6, M_S_S7, M_S_S8, M_S_S9;

    TextView M_TYPE, M_BACK;

    Button M_SUBMIT;

    String enter_type, id, s1, s2, s3, s4, s5, s6, s7, s8, s9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_marks);

        M_S_ID = (EditText) findViewById(R.id.em_s_id);
        M_S_S1 = (EditText) findViewById(R.id.em_s_s1);
        M_S_S2 = (EditText) findViewById(R.id.em_s_s2);
        M_S_S3 = (EditText) findViewById(R.id.em_s_s3);
        M_S_S4 = (EditText) findViewById(R.id.em_s_s4);
        M_S_S5 = (EditText) findViewById(R.id.em_s_s5);
        M_S_S6 = (EditText) findViewById(R.id.em_s_s6);
        M_S_S7 = (EditText) findViewById(R.id.em_s_s7);
        M_S_S8 = (EditText) findViewById(R.id.em_s_s8);
        M_S_S9 = (EditText) findViewById(R.id.em_s_s9);


        M_TYPE = (TextView) findViewById(R.id.em_type);

        M_SUBMIT = (Button) findViewById(R.id.em_submit);

        M_BACK = (TextView) findViewById(R.id.em_back);


        Intent intent = getIntent();

        enter_type = intent.getStringExtra("enter_type");


        switch (enter_type) {

            case "0" :
                M_TYPE.setText("EXTERNAL MARKS");
                break;

            case "1" :
                M_TYPE.setText("INTERNAL MARKS 1");
                break;

            case "2" :
                M_TYPE.setText("INTERNAL MARKS 2");
                break;

            case "3" :
                M_TYPE.setText("INTERNAL MARKS 3");
                break;

            default:
                M_TYPE.setText("INVALID");

        }

        M_SUBMIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = M_S_ID.getText().toString();
                s1 = M_S_S1.getText().toString();
                s2 = M_S_S2.getText().toString();
                s3 = M_S_S3.getText().toString();
                s4 = M_S_S4.getText().toString();
                s5 = M_S_S5.getText().toString();
                s6 = M_S_S6.getText().toString();
                s7 = M_S_S7.getText().toString();
                s8 = M_S_S8.getText().toString();
                s9 = M_S_S9.getText().toString();

                if(TextUtils.isEmpty(id))
                    Toast.makeText(EnterMarks.this, "Type in the Student Id !", Toast.LENGTH_LONG).show();

                else if(TextUtils.isEmpty(s1))
                    Toast.makeText(EnterMarks.this, "Type in the Subject 1 Mark !", Toast.LENGTH_LONG).show();

                else if(TextUtils.isEmpty(s2))
                    Toast.makeText(EnterMarks.this, "Type in the Subject 2 Mark !", Toast.LENGTH_LONG).show();

                else if(TextUtils.isEmpty(s3))
                    Toast.makeText(EnterMarks.this, "Type in the Subject 3 Mark !", Toast.LENGTH_LONG).show();

                else if(TextUtils.isEmpty(s4))
                    Toast.makeText(EnterMarks.this, "Type in the Subject 4 Mark !", Toast.LENGTH_LONG).show();

                else if(TextUtils.isEmpty(s5))
                    Toast.makeText(EnterMarks.this, "Type in the Subject 5 Mark !", Toast.LENGTH_LONG).show();

                else if(TextUtils.isEmpty(s6))
                    Toast.makeText(EnterMarks.this, "Type in the Subject 6 Mark !", Toast.LENGTH_LONG).show();

                else if(TextUtils.isEmpty(s7))
                    Toast.makeText(EnterMarks.this, "Type in the Subject 7 Mark !", Toast.LENGTH_LONG).show();

                else if(TextUtils.isEmpty(s8))
                    Toast.makeText(EnterMarks.this, "Type in the Subject 8 Mark !", Toast.LENGTH_LONG).show();

                else if(TextUtils.isEmpty(s9))
                    Toast.makeText(EnterMarks.this, "Type in the Subject 9 Mark !", Toast.LENGTH_LONG).show();

                else {

                    EnterMarksBackgroundTask enterMarksBackgroundTask = new EnterMarksBackgroundTask(EnterMarks.this);
                    enterMarksBackgroundTask.execute(enter_type, id, s1, s2, s3, s4, s5, s6, s7, s8, s9);

                }

            }
        });

        M_BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private class EnterMarksBackgroundTask extends AsyncTask<String, Void, String> {

        ProgressDialog pDialog;

        Context ctx;

        EnterMarksBackgroundTask(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(EnterMarks.this);
            pDialog.setMessage("Entering Marks ... ");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String enter_type = params[0];
            String student_id = params[1];
            String s1 = params[2];
            String s2 = params[3];
            String s3 = params[4];
            String s4 = params[5];
            String s5 = params[6];
            String s6 = params[7];
            String s7 = params[8];
            String s8 = params[9];
            String s9 = params[10];

            try {

                URL url = new URL("http://10.0.3.2/ums/enterMarks.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));


                String data = URLEncoder.encode("enter_type", "UTF-8") + "=" + URLEncoder.encode(enter_type, "UTF-8")
                        + "&" + URLEncoder.encode("student_id", "UTF-8") + "=" + URLEncoder.encode(student_id, "UTF-8")
                        + "&" + URLEncoder.encode("s1", "UTF-8") + "=" + URLEncoder.encode(s1, "UTF-8")
                        + "&" + URLEncoder.encode("s2", "UTF-8") + "=" + URLEncoder.encode(s2, "UTF-8")
                        + "&" + URLEncoder.encode("s3", "UTF-8") + "=" + URLEncoder.encode(s3, "UTF-8")
                        + "&" + URLEncoder.encode("s4", "UTF-8") + "=" + URLEncoder.encode(s4, "UTF-8")
                        + "&" + URLEncoder.encode("s5", "UTF-8") + "=" + URLEncoder.encode(s5, "UTF-8")
                        + "&" + URLEncoder.encode("s6", "UTF-8") + "=" + URLEncoder.encode(s6, "UTF-8")
                        + "&" + URLEncoder.encode("s7", "UTF-8") + "=" + URLEncoder.encode(s7, "UTF-8")
                        + "&" + URLEncoder.encode("s8", "UTF-8") + "=" + URLEncoder.encode(s8, "UTF-8")
                        + "&" + URLEncoder.encode("s9", "UTF-8") + "=" + URLEncoder.encode(s9, "UTF-8");

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

                case "Entering Marks Successful !":

                    Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();

                    finish();

                    pDialog.dismiss();

                    break;

                case "Entering Marks Failed !":

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