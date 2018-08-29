package com.apps.morfiwifi.morfi_project_samane;

import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.models.Block;
import com.apps.morfiwifi.morfi_project_samane.models.Khabgah;
import com.apps.morfiwifi.morfi_project_samane.models.Properties;
import com.apps.morfiwifi.morfi_project_samane.models.Room;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.role;
import com.apps.morfiwifi.morfi_project_samane.util.Repository;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SignupStudentsActivity extends AppCompatActivity {
    boolean op_finished = false;
    List<Khabgah> khabgahs;
    List<Block> blocks;
    List<Room> rooms;
    boolean is_kh_loaded=false,is_block_loaded= false,is_rooms_loaded = false,is_all_loaded = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_students);

        Khabgah.load_Khabgahs(this , true , false);
        Room.load_rooms(this , true , false);
        Block.load_blocks(this , true , false);

        Switch sw = findViewById(R.id.sw_using_khab);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    findViewById(R.id.lin_expandable_khabgah).setVisibility(View.VISIBLE);
                }else {
                    findViewById(R.id.lin_expandable_khabgah).setVisibility(View.GONE);
                }
            }
        });

        final EditText kod_melli= findViewById(R.id.ti_kod_melli) ;

        Button bottom = findViewById(R.id.btn_signup_ac);
        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int err = 0;
//                kod_melli = (TextInputEditText) findViewById(R.id.ti_kod_melli);

//        design.widget.TextInputEditText
//                TextInputEditText kod_melli = (TextInputEditText) findViewById(R.id.ti_kod_melli);
//        TextInputEditText name = (TextInputEditText) findViewById(R.id.ti_name);
//        TextInputEditText last_name =(TextInputEditText)  findViewById(R.id.ti_lastname);
//        TextInputEditText user_name =(TextInputEditText)  findViewById(R.id.ti_username);
//        TextInputEditText password =(TextInputEditText)  findViewById(R.id.ti_password);
//        TextInputEditText password_re = (TextInputEditText) findViewById(R.id.ti_password_rep);
//        TextInputEditText std_code =(TextInputEditText)  findViewById(R.id.ti_student_cod);
//        SwitchCompat switc = findViewById(R.id.sw_remember);

                // ******************************************************** checking all property...
                if (kod_melli.getText().toString().trim().length() < 3){
                    kod_melli.setError("کد ملی خالی !");
                    err++;
                }

        /*
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

        if (std_code.getText().toString().trim().length() < 4){
            std_code.setError("شماره دانشجویی ؟");
            err++;
        }*/
                // ******************************************************** checking all property...


                if (err == 0){

/*


            Properties properties = new Properties();
            properties.std_cod = std_code.getText().toString();
            properties.national_cod = kod_melli.getText().toString();

            Properties.insert_properies(this , true , properties);

            User user = new User();
            user.UserName = user_name.getText().toString();
            user.Pass = password.getText().toString();
            user.Role_id = role.HARD_CODED_STUDENT_ROLE;
            user.Active = false;
            user.PreActive = false; // YET NO LEVEL OF ACTIVATION

            User.chech_student(user);
*/

                    // THING,s IN PROGRESS ....



           /* // if there is no eror do the thing ...
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


            // chek username pass exist -> kod melli & uid !


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
            }*/




                    op_finished =true;
                }


            }
        });




    }

    public void signup(View view) {
        int err = 0;

//        design.widget.TextInputEditText
        EditText kod_melli =  findViewById(R.id.ti_kod_melli);
        EditText name =  findViewById(R.id.ti_name);
        EditText last_name =  findViewById(R.id.ti_lastname);
        EditText user_name = findViewById(R.id.ti_username);
        EditText password =  findViewById(R.id.ti_password);
        EditText password_re =  findViewById(R.id.ti_password_rep);
        EditText std_code =  findViewById(R.id.ti_student_cod);
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

        if (std_code.getText().toString().trim().length() < 4){
            std_code.setError("شماره دانشجویی ؟");
            err++;
        }
        // ******************************************************** checking all property...


        if (err == 0){



            Properties properties = new Properties();
            properties.std_cod = std_code.getText().toString();
            properties.national_cod = kod_melli.getText().toString();

            Properties.insert_properies(this , true , properties);

            User user = new User();
            user.UserName = user_name.getText().toString();
            user.Pass = password.getText().toString();
            user.Role_id = role.HARD_CODED_STUDENT_ROLE;
            user.Active = false;
            user.PreActive = false; // YET NO LEVEL OF ACTIVATION



            // THING,s IN PROGRESS ....



            // if there is no eror do the thing ...
            User m = new User();
            m.Kaet_meli = kod_melli.getText().toString();
            m.set_Type(User.Kind.Student);
            m.Pass = password.getText().toString();
            m.FName = (name.getText().toString());
            m.LName = (last_name.getText().toString());
            m.UserName = (user_name.getText().toString());
            m.Active = (false);
            m.should_fill_init_forms = (true);
            m.inset_date = (Calendar.getInstance().getTime());
            m.PreActive = (false);


            // chek username pass exist -> kod melli & uid !


            if (op_finished){
                Init.Toas(this ,"لطفا خارج شوید !");
            }else {
                User.chech_student(this ,true, user);


                if (switc.isChecked()){ //todo : check what you would do ....for remeber
//                    Repository.GetInstant(this).getUserDao().insert(m);
                    Init.Toas(this ,"پس از فعال شدن مطلع خواهید شد");
                }else {
//                    Repository.GetInstant(this).getUserDao().insert(m);
                    Init.Toas(this ,"پس از فعال شدن می توانید وارد شوید");
                }
            }




            op_finished =true;
        }



    }

    public void say_finish (){
        Toast.makeText(this, "ثبت نام انجام شد", Toast.LENGTH_SHORT).show();
        clear_things();
    }

    public void say_error () {
        Toast.makeText(this, "خطایی رخ داده", Toast.LENGTH_SHORT).show();

    }

    public void say_exsists () {
        Toast.makeText(this, "مشخصات غیر مجاز است", Toast.LENGTH_SHORT).show();

    }

    private void clear_things() {
        EditText kod_melli =  findViewById(R.id.ti_kod_melli);
        EditText name =  findViewById(R.id.ti_name);
        EditText last_name =  findViewById(R.id.ti_lastname);
        EditText user_name = findViewById(R.id.ti_username);
        EditText password =  findViewById(R.id.ti_password);
        EditText password_re =  findViewById(R.id.ti_password_rep);
        EditText std_code =  findViewById(R.id.ti_student_cod);

        kod_melli.getText().clear();
        name.getText().clear();
        last_name.getText().clear();
        user_name.getText().clear();
        password.getText().clear();
        password_re.getText().clear();
        std_code.getText().clear();


    }

    public void go_login_page(View view) {
        Intent intent =  new Intent( this , LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void put_kh (List<Khabgah> khabgahs) {
        if (khabgahs == null)
            return;
        this.khabgahs = khabgahs;
        this.is_kh_loaded = true;
        chech_all_loaded();
        Log.d("Signup :" , "khabgahs loaded");
    }

    public void put_blocks (List<Block> blocks) {
        if (blocks == null)
            return;
        this.blocks = blocks;
        this.is_block_loaded = true;
        chech_all_loaded();
        Log.d("Signup :" , "block loaded");
    }

    public void put_rooms (List<Room> rooms) {
        if (rooms == null)
            return;
        this.rooms = rooms;
        this.is_rooms_loaded = true;
        chech_all_loaded();
        Log.d("Signup :" , "rooms loaded");
    }

    private void chech_all_loaded (){
        if (is_kh_loaded && is_block_loaded && is_rooms_loaded){
            is_all_loaded = true;
            FIX_ARRAYS();
            Log.d("Signup :" , "all loaded");
        }

    }

    private void FIX_ARRAYS (){
        if (rooms != null && blocks != null && khabgahs != null){
            for (int i = 0; i < khabgahs.size(); i++) {
                khabgahs.get(i).blocks = new ArrayList<Block>();
            }
            for (int i = 0; i < blocks.size(); i++) {
                blocks.get(i).rooms = new ArrayList<Room>();
            }

            for (Room r : rooms) {

                for (Block block :blocks ) {
                    if (r.blook_id.equals(block.Id)){
                        block.rooms.add(r);
                    }
                }

            }

            for (Block block : blocks) {
                for (Khabgah khabgah : khabgahs){
                    if (block.khabgah_id.equals(khabgah.Id)){
                        khabgah.blocks.add(block);
                    }
                }
            }



        }
    }
}
