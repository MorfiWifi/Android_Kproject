package com.apps.morfiwifi.morfi_project_samane

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.apps.morfiwifi.morfi_project_samane.models.DataPref
import com.apps.morfiwifi.morfi_project_samane.models.User
import com.apps.morfiwifi.morfi_project_samane.ui.admin.AdminMainActivity
import com.apps.morfiwifi.morfi_project_samane.ui.student.StudentMainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

*/
        setContentView(R.layout.activity_login)
    }

    fun login (view : View){
        val username = txin_user_name.text.toString()
        val pass = txin_pass.text.toString();

       // TokenModel.getToken(username,pass, this)

        // YEt Disabling Notmal Log in !

        val result = DataPref.check_user(username , pass , this)

        if (result.res){
            if (result.user.Type.equals(User.kind.Student)){
                val intent = Intent(this, StudentMainActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, AdminMainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
