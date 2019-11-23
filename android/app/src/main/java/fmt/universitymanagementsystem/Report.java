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

public class Report extends AppCompatActivity {

    EditText R_GRIEVANCE;

    Button R_SUBMIT;

    TextView R_BACK;

    String r_student_id, r_grievance;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        R_GRIEVANCE = (EditText) findViewById(R.id.r_grievance);
        R_SUBMIT = (Button) findViewById(R.id.r_submit);
        R_BACK = (TextView) findViewById(R.id.r_back);

        Intent intent = getIntent();

        r_student_id = intent.getStringExtra("student_id");


        R_SUBMIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                r_grievance = R_GRIEVANCE.getText().toString();

                if(TextUtils.isEmpty(r_grievance))
                    Toast.makeText(Report.this, "Type in your Grievance !", Toast.LENGTH_LONG).show();

                else {

                    ReportBackgroundTask reportBackgroundTask = new ReportBackgroundTask(Report.this);
                    reportBackgroundTask.execute(r_student_id, r_grievance);

                }

            }
        });

        R_BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private class ReportBackgroundTask extends AsyncTask<String, Void, String> {

        ProgressDialog pDialog;

        Context ctx;

        ReportBackgroundTask(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Report.this);
            pDialog.setMessage("Reporting ... ");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String student_id = params[0];
            String message = params[1];

            try {

                URL url = new URL("http://10.0.3.2/ums/report.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));


                String data = URLEncoder.encode("student_id", "UTF-8") + "=" + URLEncoder.encode(student_id, "UTF-8")
                        + "&" + URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");

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

                case "Reporting Successful !":

                    Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();

                    finish();

                    pDialog.dismiss();

                    break;

                case "Reporting Failed !":

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
