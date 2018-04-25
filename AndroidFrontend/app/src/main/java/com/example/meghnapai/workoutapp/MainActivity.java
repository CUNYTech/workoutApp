package com.example.meghnapai.workoutapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.APICaller.base.WorkoutAPI;
import com.ChatStuff.ChatMainActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Session session = new Session(this);

        final TextView WelcomTextView= (TextView) findViewById(R.id.WelcomeTextView);
        final TextView IntroductionTextView= (TextView) findViewById(R.id.IntroductionTextView);
        final  EditText usernameTextEdit= (EditText) findViewById(R.id.usernameTextEdit);
        final EditText passwordTextEdit= (EditText) findViewById(R.id.passwordTextEdit);

        final Button infoBtn = (Button) findViewById(R.id.infoBtn);
        final Button SignUpBtn = (Button) findViewById(R.id.SignUpBtn);
        final Button LogInBtn = (Button) findViewById(R.id.LogInBtn);

        if(session.getUsername() != null){
            Intent homePage = new Intent(MainActivity.this, Homepage.class);
            MainActivity.this.startActivity(homePage);
        }


        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignInIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(SignInIntent);
            }}
        );

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Intent SignInIntent = new Intent(MainActivity.this, ListViewActivity1.class);
<<<<<<< HEAD
                Intent SignInIntent = new Intent(MainActivity.this, ChatMainActivity.class);
=======
                Intent SignInIntent = new Intent(MainActivity.this, Homepage.class);
>>>>>>> origin/master
                MainActivity.this.startActivity(SignInIntent);
            }}
        );

        LogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final Session loginInfo=new Session(MainActivity.this);
                final  EditText usernameTextEdit= (EditText) findViewById(R.id.usernameTextEdit);
                final EditText passwordTextEdit= (EditText) findViewById(R.id.passwordTextEdit);

                final String user= usernameTextEdit.getText().toString();
                final String pass=passwordTextEdit.getText().toString();


                OkHttpClient httpClient = new OkHttpClient();
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(WorkoutAPI.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient);

                Retrofit retrofit = builder.build();
                WorkoutAPI requests = retrofit.create(WorkoutAPI.class);

                Call<ResponseBody> call = requests.login(user,pass);
                call.enqueue(new Callback<ResponseBody>() {
                                 @Override
                                 public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                     if(response.code() == 202){
                                         loginInfo.setPassword(pass);
                                         loginInfo.setUsername(user);
                                         Intent homepage = new Intent(MainActivity.this, CalendarActivity.class);
                                         MainActivity.this.startActivity(homepage);
                                     }else{
                                         throwInavlidLogin();
                                     }
                                 }

                                 @Override
                                 public void onFailure(Call<ResponseBody> call, Throwable t) {
                                     System.out.println("Throws: " + t);
                                 }

            });




            }

        });
    }
    public void throwInavlidLogin(){
        Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT).show();
    }
}






