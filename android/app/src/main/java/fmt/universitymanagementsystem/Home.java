package fmt.universitymanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

public class Home extends AppCompatActivity {


    LinearLayout H_U_LAYOUT, H_C_LAYOUT, H_S_LAYOUT;

    Button H_U_ADD_COLLEGE, H_U_ADD_STUDENT, H_U_ENTER_EXTERNAL,
                   H_C_ENTER_INTERNAL_1, H_C_ENTER_INTERNAL_2, H_C_ENTER_INTERNAL_3,
                   H_S_INTERNAL, H_S_REPORT, H_S_FINAL_RESULT;

    TextView H_U_NAME, H_C_NAME, H_S_NAME, H_S_DOB, H_S_COLLEGE, H_S_DEPARTMENT, H_S_YEAR_SEMESTER, H_U_LOGOUT, H_C_LOGOUT, H_S_LOGOUT;

    String user_id, user_type, user_name, user_dob, user_college, user_department, user_year, user_semester;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        H_U_LAYOUT = (LinearLayout) findViewById(R.id.h_u_layout);
        H_C_LAYOUT = (LinearLayout) findViewById(R.id.h_c_layout);
        H_S_LAYOUT = (LinearLayout) findViewById(R.id.h_s_layout);

        H_U_ADD_COLLEGE = (Button) findViewById(R.id.h_u_add_college);
        H_U_ADD_STUDENT = (Button) findViewById(R.id.h_u_add_student);
        H_U_ENTER_EXTERNAL = (Button) findViewById(R.id.h_u_enter_external);

        H_C_ENTER_INTERNAL_1 = (Button) findViewById(R.id.h_c_enter_internal_1);
        H_C_ENTER_INTERNAL_2 = (Button) findViewById(R.id.h_c_enter_internal_2);
        H_C_ENTER_INTERNAL_3 = (Button) findViewById(R.id.h_c_enter_internal_3);

        H_S_INTERNAL = (Button) findViewById(R.id.h_s_internal);
        H_S_FINAL_RESULT = (Button) findViewById(R.id.h_s_final_result);
        H_S_REPORT = (Button) findViewById(R.id.h_s_report);

        H_U_NAME = (TextView) findViewById(R.id.h_u_name);

        H_C_NAME = (TextView) findViewById(R.id.h_c_name);

        H_S_NAME = (TextView) findViewById(R.id.h_s_name);
        H_S_DOB = (TextView) findViewById(R.id.h_s_dob);
        H_S_COLLEGE = (TextView) findViewById(R.id.h_s_college);
        H_S_DEPARTMENT = (TextView) findViewById(R.id.h_s_department);
        H_S_YEAR_SEMESTER = (TextView) findViewById(R.id.h_s_year_semester);

        H_U_LOGOUT = (TextView) findViewById(R.id.h_u_logout);
        H_C_LOGOUT = (TextView) findViewById(R.id.h_c_logout);
        H_S_LOGOUT = (TextView) findViewById(R.id.h_s_logout);


        Intent intent = getIntent();
        user_type = intent.getStringExtra("user_type");
        user_id = intent.getStringExtra("user_id");

        getUserData();

    }

    private void getUserData() {

        String url = "http://10.0.3.2/ums/getUser.php?user_type="+user_type+"&user_id="+user_id;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON_User(response);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Home.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        0,
                        -1,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(Home.this);
        requestQueue.add(stringRequest);
    }


    private void showJSON_User(String response) {

        try {

            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject userData = result.getJSONObject(0);

           switch(user_type) {

               case "admin":

                    user_name = userData.getString("name");

                    H_U_NAME.setText("Welcome " + user_name);

                    H_U_LAYOUT.setVisibility(View.VISIBLE);
                    H_C_LAYOUT.setVisibility(View.GONE);
                    H_S_LAYOUT.setVisibility(View.GONE);

                    H_U_ADD_COLLEGE.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(Home.this, AddUser.class);
                            intent.putExtra("user_type", "college");
                            startActivity(intent);

                        }
                    });

                    H_U_ADD_STUDENT.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(Home.this, AddUser.class);
                            intent.putExtra("user_type", "student");
                            startActivity(intent);

                        }
                    });

                    H_U_ENTER_EXTERNAL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(Home.this, EnterMarks.class);
                            intent.putExtra("enter_type", "0");
                            startActivity(intent);
                        }
                    });


                    H_U_LOGOUT.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });

                   break;


               case "college":

                   user_name = userData.getString("name");

                   H_C_NAME.setText("Welcome " + user_name);

                   H_U_LAYOUT.setVisibility(View.GONE);
                   H_C_LAYOUT.setVisibility(View.VISIBLE);
                   H_S_LAYOUT.setVisibility(View.GONE);

                   H_C_ENTER_INTERNAL_1.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {

                           Intent intent = new Intent(Home.this, EnterMarks.class);
                           intent.putExtra("enter_type", "1");
                           startActivity(intent);
                       }
                   });

                   H_C_ENTER_INTERNAL_2.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {

                           Intent intent = new Intent(Home.this, EnterMarks.class);
                           intent.putExtra("enter_type", "2");
                           startActivity(intent);

                       }
                   });

                   H_C_ENTER_INTERNAL_3.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {

                           Intent intent = new Intent(Home.this, EnterMarks.class);
                           intent.putExtra("enter_type", "3");
                           startActivity(intent);
                       }
                   });

                   H_C_LOGOUT.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           finish();
                       }
                   });

                   break;


               case "student":

                   user_name = userData.getString("name");
                   user_dob = userData.getString("dob");
                   user_college = userData.getString("college");
                   user_department = userData.getString("department");
                   user_year = userData.getString("year");
                   user_semester = userData.getString("semester");

                   H_S_NAME.setText("Welcome " + user_name);
                   H_S_DOB.setText("Date of Birth : " + user_dob);
                   H_S_COLLEGE.setText("College : " + user_college);
                   H_S_DEPARTMENT.setText("Department : " + user_department);
                   H_S_YEAR_SEMESTER.setText("Year : " + user_year + " / Semester : " + user_semester);

                   H_U_LAYOUT.setVisibility(View.GONE);
                   H_C_LAYOUT.setVisibility(View.GONE);
                   H_S_LAYOUT.setVisibility(View.VISIBLE);

                   H_S_INTERNAL.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {

                           Intent intent = new Intent(Home.this, ViewMarks.class);
                           intent.putExtra("enter_type", "internal");
                           intent.putExtra("student_id", user_id);
                           startActivity(intent);

                       }
                   });

                   H_S_FINAL_RESULT.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {

                           Intent intent = new Intent(Home.this, ViewMarks.class);
                           intent.putExtra("enter_type", "external");
                           intent.putExtra("student_id", user_id);
                           startActivity(intent);

                       }
                   });

                   H_S_REPORT.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {

                           Intent intent = new Intent(Home.this, Report.class);
                           intent.putExtra("student_id", user_id);
                           startActivity(intent);

                       }
                   });

                   H_S_LOGOUT.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           finish();
                       }
                   });

                   break;

               default:

                   break;

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}