package com.apps.morfiwifi.morfi_project_samane.ui.ticket;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Result;
import com.apps.morfiwifi.morfi_project_samane.models.Role_model;
import com.apps.morfiwifi.morfi_project_samane.models.User_model;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.view.RecyclerAdapter_role;
import com.apps.morfiwifi.morfi_project_samane.view.RecyclerAdapter_user;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class ChooseUserActivity extends AppCompatActivity {
    RecyclerAdapter_role rec_roles;
    RecyclerAdapter_user rec_users;
    List<Role_model> role_models = new ArrayList<>();
    List<User_model> user_models = new ArrayList<>();
    boolean isLoadig_roles = true;
    boolean isLoadig_users = false;
    int chainge = 0 , chainge_user = 0;
    Role_model ChoosenRole = null;
    int min_role_leve = 0;
    public static final String min_role_level = "MIN_ROLE_LEVEL";



    final Handler handler = new Handler(); // update View Handler in MAIN TH;
    Runnable runnable_view = new Runnable() {
        @Override
        public void run() {
            // just fixing animation!!
            if (isLoadig_roles){
                star_loading_roles();
            }else {
                stop_loading_roles();
            }

            if (isLoadig_users){
                star_loading_users();
            }else {
                stop_loading_users();
            }

            if (chainge_user > 0){
                rec_users.Set_List(user_models , Init.notNull(ChoosenRole.parseObject.get("name")));
                chainge_user = 0;
            }
            if (chainge > 0){
                rec_roles.Set_List(role_models);
                chainge = 0;
            }
            handler.postDelayed(this , 200);
        }
    };

    Runnable runnable_getRoles = new Runnable() {
        @Override
        public void run() {
            try {
                isLoadig_roles = true;
                ParseQuery query = new ParseQuery("role");
                query.whereGreaterThan("cod" , min_role_leve - 1);
                List<ParseObject> list;
                list = query.find();
                role_models = new ArrayList<>();// first load then send !8
                for (ParseObject pa :list) {
                    Role_model t = new Role_model( pa);
                    role_models.add(t);
                }
                chainge++;
                isLoadig_roles = false;
            } catch (ParseException e) {
                Log.e(getClass().getName() , e.getMessage());
                chainge++;
                isLoadig_roles = false;
            }
        }
    };

    Runnable runnable_getUsers = new Runnable() {
        @Override
        public void run() {
            try {
                isLoadig_users = true;
                ParseQuery query = new ParseQuery("_User");
                query.whereEqualTo("role_id" , Init.notNull(ChoosenRole.parseObject.getObjectId()));
                List<ParseUser> list;
                list = query.find();
                user_models = new ArrayList<>();// first load then send !8
                for (ParseUser pa :list) {
                    User_model t = new User_model( pa);
                    user_models.add(t);
                }
                chainge_user++;
                isLoadig_users = false;
            } catch (ParseException e) {
                Log.e(getClass().getName() , e.getMessage());
                chainge_user++;
                isLoadig_users = false;
            }
        }
    };

    Thread thread = new Thread(runnable_getRoles);
    Thread thread_getUsers ;

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable_view);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        start_view_thread();
        super.onResume();
    }

    @Override
    protected void onPause() {
        handler.removeCallbacks(runnable_view);
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);

        EditText editText = findViewById(R.id.et_serch);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0){
                    if (ChoosenRole == null) return;
                    if (ChoosenRole.parseObject == null) return;
                    rec_users.Set_List(user_models , Init.notNull(ChoosenRole.parseObject.get("name")));
                    return;
                }
                if (s.length() > 2){
                    findViewById(R.id.im_forward_ticker).setVisibility(View.VISIBLE);
                }else {
                    findViewById(R.id.im_forward_ticker).setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        min_role_leve = Init.notNullInteger(getIntent().getExtras().getInt(min_role_level , 0));
        rec_roles = RecyclerAdapter_role.Init(null , this);
        rec_users = RecyclerAdapter_user.Init(null , this , Init.Empty);
        start_view_thread();
        load_roles();
    }

    public void start_view_thread(){
        handler.removeCallbacks(runnable_view);
        handler.postDelayed(runnable_view , 200);
    }
    public void load_roles(){
        if (!thread.isAlive()){
            thread = new Thread(runnable_getRoles);
            thread.start();
        }

    }
    public void load_users(){
        if (thread_getUsers == null){
            thread_getUsers = new Thread(runnable_getUsers);
            thread_getUsers.start();
            return;
        }

        if (!thread_getUsers.isAlive()){
            thread_getUsers = new Thread(runnable_getUsers);
            thread_getUsers.start();
            return;
        }

        Toast.makeText(this, "صبر کن", Toast.LENGTH_SHORT).show();
    }

    public void star_loading_roles (){
        findViewById(R.id.rec_roles).setVisibility(View.GONE);
        AVLoadingIndicatorView avi = findViewById(R.id.avi_roles);
        avi.setVisibility(View.VISIBLE);
        if (!avi.isActivated()){
            avi.show();
        }
    }

    public void stop_loading_roles (){
        findViewById(R.id.rec_roles).setVisibility(View.VISIBLE);
        AVLoadingIndicatorView avi = findViewById(R.id.avi_roles);
        avi.setVisibility(View.GONE);
        avi.hide();
    }

    public void star_loading_users (){
        findViewById(R.id.rec_users).setVisibility(View.GONE);
        AVLoadingIndicatorView avi = findViewById(R.id.avi_users);
        avi.setVisibility(View.VISIBLE);
        if (!avi.isActivated()){
            avi.show();
        }
    }

    public void stop_loading_users (){
        findViewById(R.id.rec_users).setVisibility(View.VISIBLE);
        AVLoadingIndicatorView avi = findViewById(R.id.avi_users);
        avi.setVisibility(View.GONE);
        avi.hide();
    }

    public void serch_user(View view) {
        EditText search_content = findViewById(R.id.et_serch);
        String search = search_content.getText().toString();
        if (isLoadig_users){
            Toast.makeText(this, "درحال بارگزاری", Toast.LENGTH_SHORT).show();
            return;
        }
        if (user_models == null){
            Toast.makeText(this, "کاربری نیست !", Toast.LENGTH_SHORT).show();
            return;
        }

        if (user_models.size() == 0){
            Toast.makeText(this, "کاربری نیست !", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<User_model> list2 = new ArrayList<>();
        for (User_model usert: user_models) {
            if (Init.notNull(usert.parseUser.getUsername()).contains(search)){
                list2.add(usert);
            }
        }
        rec_users.Set_List(list2 , Init.notNull(ChoosenRole.parseObject.get("name")));
    }

    public void setRole (Role_model role){
        if (role == null)return;
        ChoosenRole = role;
        TextView choosen =
        findViewById(R.id.tv_roles_message);
        choosen.setText(Init.notNull(role.parseObject.get("name")));
        load_users();
    }

    public void back_arrow(View view) {
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        onBackPressed();
    }

    public void selectUser(User_model user_model) {
        if (user_model == null) return;
        Intent returnIntent = new Intent();
        returnIntent.putExtra("hasResult" ,true);
        setResult(RESULT_OK, returnIntent);
        TicketMessageActivity.setErja_model(user_model);
        onBackPressed();
    }
}
