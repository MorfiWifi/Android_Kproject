package com.apps.morfiwifi.morfi_project_samane;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.models.Block;
import com.apps.morfiwifi.morfi_project_samane.models.Khabgah;
import com.apps.morfiwifi.morfi_project_samane.models.Properties;
import com.apps.morfiwifi.morfi_project_samane.models.Room;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.role;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SignupStudentsActivity extends AppCompatActivity {
    boolean op_finished = false , isprop_ok = false , isuser_ok = false , isuser_checked = false , isprop_checked  =false , first_loading = true;
    List<Khabgah> khabgahs;
    List<Block> blocks;
    List<Room> rooms;
    boolean is_kh_loaded=false,is_block_loaded= false,is_rooms_loaded = false,is_all_loaded = false;
    static boolean first_time = true;
    User user_glob ;
    Properties properties_glob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_students);

        Khabgah.load_Khabgahs(this , true , false);
        Room.load_rooms(this , true , false);
        Block.load_blocks(this , true , false);





        final Switch sw = findViewById(R.id.sw_using_khab);
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

        final AppCompatActivity activity = this;
        final EditText kod_melli= findViewById(R.id.ti_kod_melli) ;

        Button bottom = findViewById(R.id.btn_signup_ac);

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                boolean check_nat = true;
                boolean iserror = false;
                boolean iscorrect = false;
                int kp = 200;
                int a  = 200;

                int err = 0;
//                kod_melli = (TextInputEditText) findViewById(R.id.ti_kod_melli);

//        design.widget.TextInputEditText
//                TextInputEditText kod_melli = (TextInputEditText) findViewById(R.id.ti_kod_melli);
                EditText name =  findViewById(R.id.ti_name);
                EditText last_name = findViewById(R.id.ti_lastname);
                EditText user_name = findViewById(R.id.ti_username);
                EditText password =findViewById(R.id.ti_password);
                EditText password_re =  findViewById(R.id.ti_password_rep);
                EditText std_code =  findViewById(R.id.ti_student_cod);

                EditText std_phone =  findViewById(R.id.ti_phone_number);
                EditText std_father_name =  findViewById(R.id.ti_father_name);
                EditText std_adress =  findViewById(R.id.ti_address);
                SwitchCompat switc = findViewById(R.id.sw_remember);

                // ******************************************************** checking all property...


                if (kod_melli.getText().toString().trim().length() < 10){
                    kod_melli.setError("کد ملی کوتاه است!");
                    err++;
                    check_nat = false;
                }

                if (kod_melli.getText().toString().trim().length() < 1){
                    kod_melli.setError("کد ملی خالی!");
                    err++;
                    check_nat = false;
                }


                // CHECK NATIONAL CODE >>>>>>>>>>>>>>>>>>>>>..
                if (check_nat){
                    try {
                        String melli =  kod_melli.getText().toString();

                        long melli_int =  Long.parseLong(melli);
                        a = Integer.parseInt(String.valueOf(melli.charAt(9))) ;
                        int b = Integer.parseInt(String.valueOf(melli.charAt(8))) ;
                        int c = Integer.parseInt(String.valueOf(melli.charAt(7))) ;
                        int d = Integer.parseInt(String.valueOf(melli.charAt(6))) ;
                        int e = Integer.parseInt(String.valueOf(melli.charAt(5))) ;
                        int f = Integer.parseInt(String.valueOf(melli.charAt(4))) ;
                        int g = Integer.parseInt(String.valueOf(melli.charAt(3))) ;
                        int h = Integer.parseInt(String.valueOf(melli.charAt(2))) ;
                        int i = Integer.parseInt(String.valueOf(melli.charAt(1))) ;
                        int j = Integer.parseInt(String.valueOf(melli.charAt(0))) ;
                        // a is current a

                        int  k  =  b *2+ c*3 + d*4 + e*5 + f*6+
                                g*7 + h*8 + i *9 + j *10;

                        System.out.println(k);
                        kp = k % 11;


                        Log.d("NATIONAL :" , "KP = "+kp + " , A = " + a) ;
                        if (kp == 0 && kp == a){
                            iscorrect = true;
                            Log.d("NATIONAL :" , " IS CORRECT 1");
                        }else if(kp == 1 && a == 1){
                            Log.d("NATIONAL :" , " IS CORRECT 2");
                            iscorrect = true;
                        }else if(kp > 1 && a == (11 - kp)){
                            Log.d("NATIONAL :" , " IS CORRECT 3");
                            iscorrect = true;
                        }else if (iserror){
                            Log.d("NATIONAL :" , " IS NOT CORRECT");
                        }

                    }catch (Exception e){
                        Log.d("Sign UP Page :" , "EXCEP " + e.getMessage());
                    }
                }

                if (check_nat && !iscorrect){
                    kod_melli.setError("کد ملی صحیح نمی باشد");
                    err ++;
                }
                // CHECK NATIONAL CODE >>>>>>>>>>>>>>>>>>>>>..



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

                if (std_phone.getText().toString().trim().length() < 4){
                    std_phone.setError("شماره تماس ؟");
                    err++;
                }

                if (std_father_name.getText().toString().trim().length() < 1){
                    std_father_name.setError("نام پدر ؟");
                    err++;
                }
                if (std_adress.getText().toString().trim().length() < 4){
                    std_adress.setError("نشانی ؟");
                    err++;
                }

                if (sw.isChecked()){
                    err =  chech_user_kh(err); // get and sets errors count ! USE TOAST FOR ADVISE
                }
                // ******************************************************** checking all property...


                if (err == 0){



            Properties properties = new Properties();
            properties.std_cod = std_code.getText().toString();
            properties.national_cod = kod_melli.getText().toString();
            properties.use_khabgah = false;
            properties.real_lastname = last_name.getText().toString();
            properties.real_name = name.getText().toString();
            properties.father_name = std_father_name.getText().toString();
            //bug user ID SHOULD BE IN PROPERTY !!!!! (SEQUENCE )
            properties.user_id ="";
            if (sw.isChecked()){
                Spinner kh = findViewById(R.id.sp_kh);
                Spinner block = findViewById(R.id.sp_block);
                Spinner room = findViewById(R.id.sp_room);

                Khabgah khabgah  = (Khabgah) kh.getSelectedItem();
                Block block1  = (Block) block.getSelectedItem();
                Room room1 = (Room) room.getSelectedItem();

                properties.is_studying = true;
                properties.use_khabgah = true;
                properties.kh_id = khabgah.Id;
                properties.room_id = room1.Id;
                properties.blook_id =block1.Id;
            }


            Properties.check_properties(activity , true , properties);

            User user = new User();
            user.UserName = user_name.getText().toString();
            user.Pass = password.getText().toString();
            user.Role_id = role.HARD_CODED_STUDENT_ROLE;
            user.Active = false;
            user.PreActive = false; // YET NO LEVEL OF ACTIVATION


                    user_glob = user;
                    properties_glob = properties;

            User.chech_student(activity , true , user);

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

    private int chech_user_kh(int err) {
        Spinner kh = findViewById(R.id.sp_kh);
        Spinner block = findViewById(R.id.sp_block);
        Spinner room = findViewById(R.id.sp_room);

        Khabgah khabgah  = (Khabgah) kh.getSelectedItem();
        Block block1  = (Block) block.getSelectedItem();
        Room room1 = (Room) room.getSelectedItem();

        if (khabgah == null || block1 == null || room1 == null){
            err ++;
            Toast.makeText(this, "خطا در بخش خوابگاه", Toast.LENGTH_SHORT).show();
        }

        return err;
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

            Properties.check_properties(this , true , properties);

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
        Log.d("SIGN UP :" , "SOME ERROR -no feed here");

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
        EditText std_phone =  findViewById(R.id.ti_phone_number);
        EditText std_father_name =  findViewById(R.id.ti_father_name);
        EditText std_adress =  findViewById(R.id.ti_address);

        kod_melli.getText().clear();
        name.getText().clear();
        last_name.getText().clear();
        user_name.getText().clear();
        password.getText().clear();
        password_re.getText().clear();
        std_code.getText().clear();
        std_phone.getText().clear();
        std_father_name.getText().clear();
        std_adress.getText().clear();
        op_finished = false ;
        isprop_ok = false ;
        isuser_ok = false ;
        isuser_checked = false ;
        isprop_checked  =false ;
        first_loading = true;
        is_kh_loaded=false;
        is_block_loaded= false;
        is_rooms_loaded = false;
        is_all_loaded = false;
         first_time = true;

    }

    public void go_login_page(View view) {
        clear_things();
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

//            int index1 = 0,index2 = 0,index3 = 0;

            first_time = true;
            first_loading = false;
            final Spinner kh = findViewById(R.id.sp_kh);
            final Spinner block = findViewById(R.id.sp_block);
            final Spinner room = findViewById(R.id.sp_room);

            ArrayAdapter<Khabgah> kh_adapter = new ArrayAdapter<>(this,   android.R.layout.simple_spinner_item, khabgahs);
            kh.setAdapter(kh_adapter);

            ArrayAdapter<Block> bl_adapter = new ArrayAdapter<>(this,   android.R.layout.simple_spinner_item, blocks);
            block.setAdapter(bl_adapter);

            ArrayAdapter<Room> r_adapter = new ArrayAdapter<>(this,   android.R.layout.simple_spinner_item, rooms);
            room.setAdapter(r_adapter);

            final Context activity = getApplicationContext();

            kh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i >= 0){
                        // TODO: 6/1/2018 Check some out of bound Things
                        block.setEnabled(true);
                        ArrayAdapter<Block> spinnerArrayAdapter = new ArrayAdapter<Block>(activity ,   android.R.layout.simple_spinner_item, khabgahs.get(i).blocks);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        block.setAdapter(spinnerArrayAdapter);

                        if (first_time){
                            if (blocks.size() > 0){
                                block.setSelection(0 %blocks.size());
                            }
                        }

                        if (khabgahs.get(i).blocks.size() < 1){
                            room.setEnabled(false);
                        }else {
                            room.setEnabled(true);
                        }

                    }else {
                        block.setEnabled(false);
                        room.setEnabled(false);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    block.setEnabled(false);
                    room.setEnabled(false);
                }

            });


//            final int finalIndex1 = index3;
            block.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i >= 0){
                        room.setEnabled(true);

                        ArrayAdapter<Room> spinnerArrayAdapter = new ArrayAdapter<Room>(activity ,   android.R.layout.simple_spinner_item, blocks.get(i).rooms);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        room.setAdapter(spinnerArrayAdapter);

                        if (first_time){
                            if (rooms.size() > 0){
                                room.setSelection(0 %rooms.size());
                            }
                            first_time = false;
                        }

                    }else {
                        room.setEnabled(false);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    room.setEnabled(false);
                }

            });

        }
    }

    public boolean isloaded (){
        return (isuser_checked && isprop_checked);
    }

    public boolean isuserloaded () {
        return isuser_checked;
    }

    public boolean isproploaded(){
        return isprop_checked;
    }

    public boolean isfirst (){
        return first_loading;
    }

    public void all_don (){
        if (isprop_checked && isuser_checked){
            if (!isuser_ok && !isprop_ok){
                Toast.makeText(this, "مشخصات فردی و دانشجویی نا معتبر است", Toast.LENGTH_SHORT).show();
            }else if (!isprop_ok){
                Toast.makeText(this, "مشخصات دانشجویی نا معتبر است (شماره دانشجویی)", Toast.LENGTH_SHORT).show();
            }else if (!isuser_ok){
                Toast.makeText(this, "مشخصات فردی نا معتبر است (کدملی/نام کاربری)", Toast.LENGTH_SHORT).show();
            }else if (isuser_ok && isprop_ok){
                Toast.makeText(this, "کاربر ثبت شد", Toast.LENGTH_SHORT).show();
            }
        }

    }


    public void prop_isok() {
        isprop_checked  =true;
        isprop_ok = true;

        if ( isuserloaded()){
            // then try uploading things ....
            User.insert_user(this , true   , user_glob , properties_glob);
//            Properties.insert_properties(this , true , properties_glob);

            Log.d("SIGN UP :" , "USER DATA & PROP SENT");
        }

        all_don();
    }

    public void say_exsists_prop() {
        isprop_checked  =true;
        isprop_ok = false;
        all_don();
    }

    public void say_exsists_user() {
        isuser_checked  =true;
        isuser_ok = false;
        all_don();
    }

    public void user_isok() {
        isuser_checked  =true;
        isuser_ok = true;

        if ( isprop_ok){
            // then try uploading things ....
            User.insert_user(this , true   , user_glob , properties_glob);
//            Properties.insert_properties(this , true , properties_glob);

            Log.d("SIGN UP :" , "USER DATA & PROP SENT");
        }
        all_don();

    }

    public void user_inserted (){
        Toast.makeText(this, "مشخصات کابری با موففقیت ثبت شد", Toast.LENGTH_SHORT).show();
//        Init.FIX_PROP_ID(user_glob,properties_glob);
    }

    public  void prop_inserted (){
        Toast.makeText(this, "مشخصات پرسنلی با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
//        Init.FIX_PROP_ID(user_glob,properties_glob);
    }

    public void  user_insert_filed (){
        Toast.makeText(this, "خطا در ثبت کاربر", Toast.LENGTH_SHORT).show();
    }

    public void  prop_insert_faild (){
        Toast.makeText(this, "خطا در ثبت مشخصات پرسنلی", Toast.LENGTH_SHORT).show();
    }
}
