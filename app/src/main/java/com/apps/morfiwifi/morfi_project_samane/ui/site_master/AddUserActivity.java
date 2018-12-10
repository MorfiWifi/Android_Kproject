package com.apps.morfiwifi.morfi_project_samane.ui.site_master;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Block;
import com.apps.morfiwifi.morfi_project_samane.models.Khabgah;
import com.apps.morfiwifi.morfi_project_samane.models.MUSER;
import com.apps.morfiwifi.morfi_project_samane.models.Properties;
import com.apps.morfiwifi.morfi_project_samane.models.Room;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.role;

import java.util.ArrayList;
import java.util.List;

public class AddUserActivity extends SiteMasterActivity {
    static final  String LOG_CODE = "ADD USER SITE";
    List<role> roles;
    List<Khabgah> khabgahs;
    List<Block> blocks;
    List<Room> rooms;
    boolean dorm_loaded  =false;
    boolean isroleloaded = false;
    private User user = null;
    private Properties properties = null;
    private MUSER muser = null;
    role role = null;
    private boolean isprop_ok = false;
    private boolean isuserloaded = false;
    boolean add_kh = true;
    boolean add_full_prop = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        role.load_roles_accesasables(this , true , false);
        Khabgah.load_Khabgahs(this , true , false);
        Block.load_blocks(this  , true , false);
        Room.load_rooms(this , true , false);



        final Switch sw1 = findViewById(R.id.sw_first);
        final Switch sw2 = findViewById(R.id.sw_second);


        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    findViewById(R.id.lin_expandable_info1).setVisibility(View.VISIBLE);
                else
                    findViewById(R.id.lin_expandable_info1).setVisibility(View.GONE);

            }
        });


        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    findViewById(R.id.lin_expandable_info2).setVisibility(View.VISIBLE);
                else
                    findViewById(R.id.lin_expandable_info2).setVisibility(View.GONE);
            }
        });

         final Spinner spinner = findViewById(R.id.sp_user_types);
         spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 if (spinner.getSelectedItem().toString().equals("دانشجو")){
                     sw1.setChecked(true);
                     sw2.setChecked(true);
                     sw1.setEnabled(false);
                     sw2.setEnabled(false);

                 }else {
                     sw1.setEnabled(true);
                     sw2.setEnabled(true);
                 }
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });



        setTitle("اضافه کردن کاربر");
    }


    public void set_roles (List<role> roles){
        if (roles != null){
            this.roles = roles;
            isroleloaded = true;
            update_view();
        }

    }

    private void update_view() {
        if (roles== null)
            return;
        ArrayAdapter<role> spinnerArrayAdapter = new ArrayAdapter<>(this,   android.R.layout.simple_spinner_item, roles);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        Spinner spinner = findViewById(R.id.sp_user_types);
        spinner.setAdapter(spinnerArrayAdapter);

    }

    @Override
    public void refresh_view() {
        role.load_roles_accesasables(this , true , true);
    }

    public  int check_propertyes (){

        return 0;
    }

    private void log_it (String s){
        Log.d(LOG_CODE , s);
    }
    public void add_user(View view) {
        // lets DO some thing big ...
        log_it("add user started");

        int Errors = 0;
        Switch pr1 = findViewById(R.id.sw_first);
        Switch pr2 = findViewById(R.id.sw_second);
        Spinner spinner = findViewById(R.id.sp_user_types);

         add_kh = pr1.isChecked();
         add_full_prop = pr2.isChecked();

        role = (role) spinner.getSelectedItem();
        if (role == null){
            Toast.makeText(this, "نوع کاربر مشخص نیست", Toast.LENGTH_SHORT).show();
            return;
        }

        // first check USER NAME PASS
        Errors = validate_user_account_info(Errors);

        if (Errors > 0){
            // FIRST CHECK THE GENERAL ACCOUNT THING
            return;
        }

         // role of selected
        
        if (role.cod == 0){
            if (!add_full_prop){ // STUDENT WITH NO PROPETEY !!!
                Toast.makeText(this, "مشخصات کامل دانشچو ضروزی است", Toast.LENGTH_SHORT).show();
                return;
            }
        }


        if (add_kh){
            Errors = validate_khabgah_info (Errors);
        }

        if (add_full_prop){
            Errors = validate_propetie_info (Errors);
        }

        if (Errors >0){
            // SHOULD FIX THESE FIRST
            return;
        }


        add_user_to_system ();
        log_it("add user ended");
    }

    private void add_user_to_system() {
        log_it("add user method  started");
        if (user == null || role == null ){
            Toast.makeText(this, "خطای دخلی رخداده!", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (properties == null){
            Toast.makeText(this, "کاربر بدون مشخصات تکمیلی در حال اضافه شدن", Toast.LENGTH_SHORT).show();
        }

        muser = new MUSER();
        muser.activate = true;
        muser.preactive =true;

        if (role.cod == 0){
            log_it("chek user method  started std");
            User.chech_student(this , true ,user );
            Properties.check_properties(this , true , properties);
            log_it("add user method  ended std");
        }else {
            log_it("chek user method  started");
            User.chech_student(this , true , user);
            if (properties != null){
                Properties.check_properties(this , true , properties);
            }
            log_it("add user method  ended");
        }



        log_it("add user method  ended");
    }

    public void prop_isok() {
//        isprop_checked  =true;
        log_it("prop wa ok");
        isprop_ok = true;

        if ( isuserloaded){
            // then try uploading things ....
            log_it("trying insert prop");
            User.insert_user(this , true   , user , properties , muser);
//            Properties.insert_properties(this , true , properties_glob);

            Log.d("SIGN UP :" , "USER DATA & PROP SENT");
        }

        all_don();
    }

    public void user_isok() {
//        use  =true;
        isuserloaded = true;
        log_it("user was ok");
        if ( isprop_ok || ( (!add_full_prop) && (!add_kh) )){
            // then try uploading things ....
            log_it("trying insert user");
            User.insert_user(this , true   , user , properties , muser);
//            Properties.insert_properties(this , true , properties_glob);
        }
        all_don();

    }
    public void all_don (){
        log_it("all don is running");
        if (isuserloaded && isprop_ok){
            if (!isuserloaded && !isprop_ok){
                Toast.makeText(this, "مشخصات فردی و دانشجویی نا معتبر است", Toast.LENGTH_SHORT).show();
            }else if (!isprop_ok){
                Toast.makeText(this, "مشخصات کاربر نا معتبر است (شماره دانشجویی/کاربری)", Toast.LENGTH_SHORT).show();
            }else if (!isuserloaded){
                Toast.makeText(this, "مشخصات فردی نا معتبر است (کدملی/نام کاربری)", Toast.LENGTH_SHORT).show();
            }else if (isuserloaded && isprop_ok){
//                Toast.makeText(this, "کاربر ثبت شد", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void user_inserted (){
        Toast.makeText(this, "مشخصات کابری با موففقیت ثبت شد", Toast.LENGTH_SHORT).show();
//        Init.FIX_PROP_ID(user_glob,properties_glob);
        clear();
    }

    public  void prop_inserted (){
        Toast.makeText(this, "مشخصات پرسنلی با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
//        Init.FIX_PROP_ID(user_glob,properties_glob);
    }

    private int validate_khabgah_info(int errors) {
        log_it("validate khabgah started");
        Spinner khab = findViewById(R.id.sp_kh);
        Spinner block = findViewById(R.id.sp_block);
        Spinner room = findViewById(R.id.sp_room);

        Object kh = khab.getSelectedItem();
        Object bl = block.getSelectedItem();
        Object ro = room.getSelectedItem();

        if (kh == null || bl == null || ro == null){
            Toast.makeText(this, "خطا در بخش خابگاه/مشخصات تکمیلی 1", Toast.LENGTH_SHORT).show();
            errors++;
            return errors;
        }

        properties = new Properties();
        properties.kh_id = ((Khabgah)kh).Id;
        properties.blook_id = ((Block)bl).Id;
        properties.room_id = ((Room)ro).Id;
        properties.use_khabgah = true;
        properties.isMail = ((Switch) findViewById(R.id.sw_is_maile)).isChecked();


        log_it("validate khabgah ended");
        return errors;
    }

    private int validate_propetie_info(int errors) {
        // YA BOOYAH!
        log_it("validate properties started");
        EditText kod_meli = findViewById(R.id.ti_kod_melli);
        EditText cod = findViewById(R.id.ti_student_cod);
        EditText real_name = findViewById(R.id.ti_name);
        EditText real_lastname = findViewById(R.id.ti_lastname);
        EditText phone_number = findViewById(R.id.ti_phone_number);
        EditText father_name = findViewById(R.id.ti_father_name);
        EditText address = findViewById(R.id.ti_address);

        if (kod_meli.getText().toString().length() < 1){
            kod_meli.setError("کد ملی خالی است");
            errors++;
        }else {

            int kp = 200;
            int a  = 200;

            try {
                String melli =  kod_meli.getText().toString();

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
                boolean iscorrect;
                if (kp == 0 && kp == a){
                    iscorrect = true;
                    Log.d("NATIONAL :" , " IS CORRECT 1");
                }else if(kp == 1 && a == 1){
                    Log.d("NATIONAL :" , " IS CORRECT 2");
                    iscorrect = true;
                }else if(kp > 1 && a == (11 - kp)){
                    Log.d("NATIONAL :" , " IS CORRECT 3");
                    iscorrect = true;
                }else {
                    iscorrect = false;
                    Log.d("NATIONAL :" , " IS NOT CORRECT");
                }

                if (!iscorrect){
                    kod_meli.setError("فرمت کد ملی صحیح نیست");
                    errors ++;
                }

            }catch (Exception e){
                    kod_meli.setError("فرمت کد ملی صحیح نیست");
                    errors ++;
                Log.d("Sign UP Page :" , "EXCEP " + e.getMessage());
            }
        }
        if (cod.getText().toString().length() < 1){
            cod.setError("شماره دانشجویی/پرسنلی خالی است");
            errors++;
        }
        if (real_name.getText().toString().length() < 1){
            real_name.setError("نام خالی است");
            errors++;
        }
        if (real_lastname.getText().toString().length() < 1){
            real_lastname.setError("نام خانوادگی خالی است");
            errors++;
        }
        if (phone_number.getText().toString().length() < 1){
            phone_number.setError("شماره تماس خالی است");
            errors++;
        }
        if (father_name.getText().toString().length() < 1){
            father_name.setError("نام پدر خالی است");
            errors++;
        }
        if (address.getText().toString().length() < 1){
            address.setError("آدرس خالی است");
            errors++;
        }

        if (errors > 0 ){
            //fix them first
            return errors;
        }

        if (properties != null){
            properties.father_name = father_name.getText().toString();
            properties.national_cod = kod_meli.getText().toString();
            properties.real_name = real_name.getText().toString();
            properties.real_lastname = real_lastname.getText().toString();
            properties.std_cod = cod.getText().toString();

            // ADRESS & PHONE ? fixme property full load&UPLOAD

            properties.phone = phone_number.getText().toString();
            properties.adress = address.getText().toString();
        }
        log_it("validate properties ended");
        return errors;
    }

    private int validate_user_account_info(int errors) {
        log_it("validate user account started");
        EditText username =  findViewById(R.id.et_usernmae);
        EditText pass = findViewById(R.id.et_pass);
        EditText repass = findViewById(R.id.et_repass);

//        username.setError("");
//        pass.setError("");
//        repass.setError("");

        if (username.getText().length() == 0){
            username.setError("نام کاربری خالی!");
            errors++;
        }else if (username.getText().length() < 3){
            username.setError("نام کاربری کتاه است");
            errors++;
        }

        if (pass.getText().length() == 0){
            pass.setError("رمز عبور خالی!");
            errors++;
        }else if (pass.getText().length() < 3){
            pass.setError("رمز عبور کتاه است");
            errors++;
        }

        if (!pass.getText().toString().equals(repass.getText().toString())){
            repass.setError("عدم تطابق رمز عبور");
            errors++;
        }

        if (errors == 0){
            user = new User();
            user.UserName = username.getText().toString();
            user.Pass = pass.getText().toString();
            user.Active = true;
            user.Role_id = role.id;
        }

        log_it("validate user account ended");
        return errors;
    }

    private void FIX_ARRAYS (){
        Log.d("ADD USER :" , "FIX ARRAYS CALLED");
        if (khabgahs == null || rooms == null || blocks == null){
            return;
        }

        // EVEY MAN HAS A Weakness find the ....
        Log.d("ADD USER :" , "FIX ARRAYS RUNNING");

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

        Spinner khs1 =  findViewById(R.id.sp_kh);
        final Spinner bls1 =  findViewById(R.id.sp_block);
        final Spinner rs1 =  findViewById(R.id.sp_room);


        ArrayAdapter<Khabgah> spinnerArrayAdapter = new ArrayAdapter<Khabgah>(this,   android.R.layout.simple_spinner_item, khabgahs);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        khs1.setAdapter(spinnerArrayAdapter);

        khs1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0){
                    // TODO: 6/1/2018 Check some out of bound Things
                    bls1.setEnabled(true);
                    if (khabgahs.get(i).blocks.size() == 0){
                        bls1.setEnabled(false);
                        rs1.setEnabled(false);
                        return;
                    }
                    ArrayAdapter<Block> spinnerArrayAdapter = new ArrayAdapter<Block>(getApplicationContext() ,   android.R.layout.simple_spinner_item, khabgahs.get(i).blocks);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    bls1.setAdapter(spinnerArrayAdapter);

                }else {
                    bls1.setEnabled(false);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                bls1.setEnabled(false);
                rs1.setEnabled(false);

            }

        });


        bls1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0){
                    rs1.setEnabled(true);

                    bls1.setEnabled(true);
                    if (blocks.get(i).rooms.size() == 0){
//                        bls1.setEnabled(false);
                        rs1.setEnabled(false);
                        return;
                    }

                    ArrayAdapter<Room> spinnerArrayAdapter = new ArrayAdapter<Room>(getApplicationContext() ,   android.R.layout.simple_spinner_item, blocks.get(i).rooms);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    rs1.setAdapter(spinnerArrayAdapter);

                }else {
                    rs1.setEnabled(false);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                rs1.setEnabled(false);
            }

        });



    }

    public void set_kh (List<Khabgah> ka    ){
        Log.d("ADD USER :" , "KHABGAH LOADED");
        khabgahs = ka;
        FIX_ARRAYS();
    }

    public void set_bls (List<Block> bls    ){
        Log.d("ADD USER :" , "BLOOCKS LOADED");
        blocks = bls;
        FIX_ARRAYS();
    }

    public void set_rooms (List<Room> roos    ){
        Log.d("ADD USER :" , "ROOMS LOADED");
        rooms = roos;
        FIX_ARRAYS();
    }

    private void clear(){
        properties = null;
        user= null;
        isuserloaded = false;
        isprop_ok = false;

        EditText kod_meli = findViewById(R.id.ti_kod_melli);
        EditText cod = findViewById(R.id.ti_student_cod);
        EditText real_name = findViewById(R.id.ti_name);
        EditText real_lastname = findViewById(R.id.ti_lastname);
        EditText phone_number = findViewById(R.id.ti_phone_number);
        EditText father_name = findViewById(R.id.ti_father_name);
        EditText address = findViewById(R.id.ti_address);

        EditText username =  findViewById(R.id.et_usernmae);
        EditText pass = findViewById(R.id.et_pass);
        EditText repass = findViewById(R.id.et_repass);

        username.getText().clear();
        pass.getText().clear();
        repass.getText().clear();

        kod_meli.getText().clear();
         cod.getText().clear();
        real_lastname.getText().clear();
        real_name.getText().clear();
        phone_number.getText().clear();
        father_name.getText().clear();
        address.getText().clear();
        // clear user name - pass ...


    }

    @Override
    protected void onDestroy() {
        clear();
        super.onDestroy();
    }

    public void prp_exists() {
        log_it("prop exists");
        Toast.makeText(this, "متاسفاته مشخصات تکمیلی 1/2 نا معتبر است", Toast.LENGTH_SHORT).show();
        
    }

    public void say_exsists_user() {
        log_it("user existts");
        Toast.makeText(this, "متاسفاته مشخصات حساب کاربر نا معتبر است", Toast.LENGTH_SHORT).show();
    }
}
