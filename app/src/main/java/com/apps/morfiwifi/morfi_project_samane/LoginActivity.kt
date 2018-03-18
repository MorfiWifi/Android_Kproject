package com.apps.morfiwifi.morfi_project_samane

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.apps.morfiwifi.morfi_project_samane.models.TokenModel
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
        TokenModel.getToken(username,pass, this)
    }
}
