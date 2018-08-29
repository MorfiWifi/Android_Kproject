package com.apps.morfiwifi.morfi_project_samane.ui.student

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.apps.morfiwifi.morfi_project_samane.R
import com.apps.morfiwifi.morfi_project_samane.models.Setting
import com.apps.morfiwifi.morfi_project_samane.ui.Dialogue
import kotlinx.android.synthetic.main.activity_student_main.*
import kotlinx.android.synthetic.main.app_bar_student_main.*
import kotlinx.android.synthetic.main.content_student_main.*

class StudentMainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
//        nav_view.tv_navbar_name.text = User.current_user.userName
//        nav_view.tv_navbar_rolename.text = User.current_user.Role
        title = "دانشجو"
        //fixme THis Could be std Profile instead of Main

        rel_prof.setOnClickListener {
            val intent = Intent(this , StudentProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent )
        }

        rel_request.setOnClickListener {
            val intent = Intent(this , DarkhastActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent )
        }

        rel_inbox.setOnClickListener {
            val intent = Intent(this , BroadcastActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent )
        }

        rel_cancelation.setOnClickListener {
            val intent = Intent(this , EnserafActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent )
        }

        rel_transfer.setOnClickListener {
            val intent = Intent(this , TransferActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent )
        }

        rel_report.setOnClickListener {
            val intent = Intent(this , ReportActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent )
        }

        rel_feedback.setOnClickListener {
            val intent = Intent(this , EnteghadActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent )
        }

        rel_logout.setOnClickListener {
            val dialogue = Dialogue()
            dialogue.Log_out_account(this).show()
        }

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {

            if (this.javaClass.name != StudentMainActivity::class.java.name) {
                val intent = Intent(this, StudentMainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            } else {
                val dialog = Dialogue()
                dialog.Exit_app(this).show()
                //                super.onBackPressed();
            }
//            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.student_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {

            R.id.nav_profile -> {
                val intent = Intent(this , StudentProfileActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent )
            }
            R.id.nav_exit -> {
//                onBackPressed() // YET JUST BACK PRESSED
                val dialogue = Dialogue()
                dialogue.Log_out_account(this).show()
                }
            R.id.nav_enteghad -> {
                val intent = Intent(this , EnteghadActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent )
            }
            R.id.nav_darkhast -> {
                val intent = Intent(this , DarkhastActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent )
            }
            R.id.nav_gozaresh -> {
                val intent = Intent(this , ReportActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent )
            }
            R.id.nav_manage_box->{
                val intent = Intent(this , BroadcastActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent )
            }
            R.id.nav_enseraf ->{
                val intent = Intent(this , EnserafActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent )
            }
            R.id.nav_jabeja ->{
                val intent = Intent(this , TransferActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent )
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun load_data (){
        // preload data or normal load !
        if (Setting.isPreload(this)){
            //todo  load all data - broudcastes ,

        }else{


        }
    }
}
