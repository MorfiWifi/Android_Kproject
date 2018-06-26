package com.apps.morfiwifi.morfi_project_samane;

import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.text.InputType;
import android.view.View;

import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.util.Repository;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;

import java.util.Calendar;

public class SignupStudentsActivity extends AppCompatActivity {
    boolean op_finished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_students);

        TextInputEditText kod_melli = findViewById(R.id.ti_kod_melli);
        kod_melli.setInputType(InputType.TYPE_CLASS_NUMBER);

    }

    public void signup(View view) {
        int err = 0;
        TextInputEditText kod_melli = findViewById(R.id.ti_kod_melli);
        TextInputEditText name = findViewById(R.id.ti_name);
        TextInputEditText last_name = findViewById(R.id.ti_lastname);
        TextInputEditText user_name = findViewById(R.id.ti_username);
        TextInputEditText password = findViewById(R.id.ti_password);
        TextInputEditText password_re = findViewById(R.id.ti_password_rep);
        SwitchCompat switc = findViewById(R.id.sw_remember);

        // ******************************************************** checking all property...
        if (kod_melli.getText().toString().trim().length() < 3){
            kod_melli.setError("کد ملی خالی !");
            err++;
        }
        if (name.getText().toString().trim().length() < 3){
            name.setError("نام خالی !");
            err++;
        }
        if (last_name.getText().toString().trim().length() < 3){
            last_name.setError("نام خانوادگی خالی !");
            err++;
        }
        if (user_name.getText().toString().trim().length() < 3){
            user_name.setError("نام کاربری !");
            err++;
        }
        if (! password_re.getText().toString().equals(password.getText().toString()) ){
            password_re.setError("تکرار رمز صحیح نیست");
            err++;
        }

        if (password.getText().toString().trim().length() < 4){
            password.setError("رمز کوتاه است");
            err++;
        }
        if (password_re.getText().toString().trim().length() < 4){
            password_re.setError("تکرار رمز کوتاه است");
            err++;
        }

        // ******************************************************** checking all property...


        if (err == 0){
            // if there is no eror do the thing ...
            User m = new User();
            m.setKaet_meli(kod_melli.getText().toString());
            m.set_Type(User.Kind.Student);
            m.setPass(password.getText().toString());
            m.setFName(name.getText().toString());
            m.setLName(last_name.getText().toString());
            m.setUserName(user_name.getText().toString());
            m.setActive(false);
            m.setShould_fill_init_forms(true);
            m.setInset_date(Calendar.getInstance().getTime());
            m.setPreActive(false);

            if (op_finished){
                Init.Toas(this ,"لطفا خارج شوید !");
            }else {
                if (switc.isChecked()){ //todo : check what you would do ....for remeber
                    Repository.GetInstant(this).getUserDao().insert(m);
                    Init.Toas(this ,"پس از فعال شدن مطلع خواهید شد");
                }else {
                    Repository.GetInstant(this).getUserDao().insert(m);
                    Init.Toas(this ,"پس از فعال شدن می توانید وارد شوید");
                }
            }




            op_finished =true;
        }



    }

    public void go_login_page(View view) {
        Intent intent =  new Intent( this , LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
