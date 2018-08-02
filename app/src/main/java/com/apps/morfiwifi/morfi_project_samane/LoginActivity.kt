package com.apps.morfiwifi.morfi_project_samane

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.apps.morfiwifi.morfi_project_samane.models.DataPref
import com.apps.morfiwifi.morfi_project_samane.models.User
import com.apps.morfiwifi.morfi_project_samane.models.role
import com.apps.morfiwifi.morfi_project_samane.ui.admin.AdminMainActivity
import com.apps.morfiwifi.morfi_project_samane.ui.site_master.SiteMasterActivity
import com.apps.morfiwifi.morfi_project_samane.ui.student.SamanehaActivity
import com.apps.morfiwifi.morfi_project_samane.ui.student.StudentMainActivity
import com.apps.morfiwifi.morfi_project_samane.ui.technical.TechnicalActivity
import com.apps.morfiwifi.morfi_project_samane.utility.Init
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {


    //private val noteDao: NoteDao? = null
    //private val notesQuery: Query<com.apps.morfiwifi.morfi_project_samane.models.Note>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        //com.orm.SugarContext.init(applicationContext)
        super.onCreate(savedInstanceState)

//        Init.test_loading(this)

        val exit_code = intent.getBooleanExtra("EXIT" , false)
        if (exit_code){
            finish()
        }



/*
        tv_go_signup.setOnClickListener({
            val intent = Intent(this, SiteMasterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        })*/


       /* //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

*/
        setContentView(R.layout.activity_login)
        /*val noteDao: NoteDao;
        val daoSession = DaoMaster.newDevSession(applicationContext, "DB_DB")
        noteDao = daoSession.noteDao
        val note = com.apps.morfiwifi.morfi_project_samane.models.Note()
        note.id = 1L
        note.text = "Max"
        noteDao.insert(note)*/

//        Init.Insert_init_Users(this)
        Init.Login_Signup(this)

    }

    fun login_server (user : User){

        if (user != null){
            //Init.current_login = User
            Init.start_fresh() // for fixing other user catching problem
            when {
                user.Role.equals(User.Kind.Student.toString()) -> {
                    role.load_roles(this , false);
                    val intent = Intent(this, SamanehaActivity::class.java)
                    startActivity(intent)
                }
                user.Role.equals(User.Kind.Admin.toString()) -> {
                    role.load_roles(this , false);
                    val intent = Intent(this, AdminMainActivity::class.java)
                    startActivity(intent)
                }
                user.Role.equals(User.Kind.Site_Master.toString()) -> {
                    role.load_roles(this , false);
                    val intent = Intent(this, SiteMasterActivity::class.java)
                    startActivity(intent)
                }
                user.Role.equals(User.Kind.Technical.toString()) -> {
                    role.load_roles(this , false);
                    val intent = Intent(this, TechnicalActivity::class.java)
                    startActivity(intent)
                }
                else -> Init.Toas(this , "خطا در ورودی")
            }
        }



    }

    fun login (view : View){


        val username = txin_user_name.text.toString()
        val pass = txin_pass.text.toString()

       // TokenModel.getToken(username,pass, this)

        // YEt Disabling Notmal Log in !

        val result = DataPref.check_user(username , pass , this)

        var log_user = Init.log_in(username , pass , this)
        User.login(username , pass , this)
        log_user = null

        if (log_user != null){
            Init.current_login = log_user
            when {
                log_user.Type.equals(0) -> {
                    val intent = Intent(this, SamanehaActivity::class.java)
                    startActivity(intent)
                }
                log_user.Type.equals(5) -> {
                    val intent = Intent(this, AdminMainActivity::class.java)
                    startActivity(intent)
                }
                log_user._Type == User.Kind.Site_Master -> {
                    val intent = Intent(this, SiteMasterActivity::class.java)
                    startActivity(intent)
                }
                log_user._Type == User.Kind.Technical -> {
                    val intent = Intent(this, TechnicalActivity::class.java)
                    startActivity(intent)
                }
                else -> Init.Toas(this , "خطا در ورودی")
            }
        }




        //val maxi = User.find(User::class.java , "wher user.FName = ?" , "max" ).first()

        //val s : String
        //s = maxi.FName + maxi.LName;

       // Init.Toas(applicationContext , s )

    }

    fun save_ins_user (){
        val m = User();
        m.FName = "max"
        m.Kaet_meli = "0000"
        m.LName = "ad"

       // m.save()
    }
}
