package fmt.universitymanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewMarks extends AppCompatActivity {

    String v_m_type, student_id;

    TextView V_M_TYPE, V_M_STUDENT_ID, V_M_BACK, V_M_S1, V_M_S2, V_M_S3,
            V_M_S4, V_M_S5, V_M_S6, V_M_S7, V_M_S8, V_M_S9, V_M_GPA;

    LinearLayout V_M_L_GPA;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_marks);


        V_M_TYPE = (TextView) findViewById(R.id.vm_type);
        V_M_STUDENT_ID = (TextView) findViewById(R.id.vm_s_id);
        V_M_BACK = (TextView) findViewById(R.id.vm_back);
        V_M_S1 = (TextView) findViewById(R.id.vm_s_s1);
        V_M_S2 = (TextView) findViewById(R.id.vm_s_s2);
        V_M_S3 = (TextView) findViewById(R.id.vm_s_s3);
        V_M_S4 = (TextView) findViewById(R.id.vm_s_s4);
        V_M_S5 = (TextView) findViewById(R.id.vm_s_s5);
        V_M_S6 = (TextView) findViewById(R.id.vm_s_s6);
        V_M_S7 = (TextView) findViewById(R.id.vm_s_s7);
        V_M_S8 = (TextView) findViewById(R.id.vm_s_s8);
        V_M_S9 = (TextView) findViewById(R.id.vm_s_s9);
        V_M_L_GPA = (LinearLayout) findViewById(R.id.vm_s_l_gpa);
        V_M_GPA = (TextView) findViewById(R.id.vm_s_gpa);


        Intent intent = getIntent();

        v_m_type = intent.getStringExtra("enter_type");
        student_id = intent.getStringExtra("student_id");

        V_M_STUDENT_ID.setText(student_id);


        switch (v_m_type) {

            case "internal":

                V_M_TYPE.setText("INTERNAL MARKS");
                V_M_L_GPA.setVisibility(View.GONE);

                getInternalData();

                break;

            case "external":

                V_M_TYPE.setText("EXTERNAL MARKS");

                getExternalData();

                break;

            default:

                break;

        }

        V_M_BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }



    private void getInternalData() {

        String url = "http://10.0.3.2/ums/getInternal.php?student_id="+student_id;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON_Internal(response);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewMarks.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        0,
                        -1,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(ViewMarks.this);
        requestQueue.add(stringRequest);
    }


    private void showJSON_Internal(String response) {

        try {

            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject internalData = result.getJSONObject(0);

            V_M_S1.setText(internalData.getString("s1"));
            V_M_S2.setText(internalData.getString("s2"));
            V_M_S3.setText(internalData.getString("s3"));
            V_M_S4.setText(internalData.getString("s4"));
            V_M_S5.setText(internalData.getString("s5"));
            V_M_S6.setText(internalData.getString("s6"));
            V_M_S7.setText(internalData.getString("s7"));
            V_M_S8.setText(internalData.getString("s8"));
            V_M_S9.setText(internalData.getString("s9"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    private void getExternalData() {

        String url = "http://10.0.3.2/ums/getExternal.php?student_id="+student_id;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON_External(response);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewMarks.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        0,
                        -1,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(ViewMarks.this);
        requestQueue.add(stringRequest);
    }


    private void showJSON_External(String response) {

        try {

            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject externalData = result.getJSONObject(0);

            V_M_S1.setText(externalData.getString("s1"));
            V_M_S2.setText(externalData.getString("s2"));
            V_M_S3.setText(externalData.getString("s3"));
            V_M_S4.setText(externalData.getString("s4"));
            V_M_S5.setText(externalData.getString("s5"));
            V_M_S6.setText(externalData.getString("s6"));
            V_M_S7.setText(externalData.getString("s7"));
            V_M_S8.setText(externalData.getString("s8"));
            V_M_S9.setText(externalData.getString("s9"));
            V_M_GPA.setText(externalData.getString("gpa"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
