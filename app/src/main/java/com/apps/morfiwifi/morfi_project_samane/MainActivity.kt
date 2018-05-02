package com.apps.morfiwifi.morfi_project_samane

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.apps.morfiwifi.morfi_project_samane.models.Message
import com.apps.morfiwifi.morfi_project_samane.models.User
import com.apps.morfiwifi.morfi_project_samane.ui.ReciverActivity
import com.apps.morfiwifi.morfi_project_samane.ui.TestDaoActivity
import com.apps.morfiwifi.morfi_project_samane.utility.shamsiDate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.text.SimpleDateFormat
import java.util.*




class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
    }

    fun gologin(viwe : View){
        val inte = Intent (this ,LoginActivity::class.java )
        startActivity(inte)
    }

    fun sheet (view:View){
        //todo chainge to Pre Designed List of Messages!
        val l  = ArrayList<com.apps.morfiwifi.morfi_project_samane.models.Message>()
        val mes1 = com.apps.morfiwifi.morfi_project_samane.models.Message()
        val mes2 = com.apps.morfiwifi.morfi_project_samane.models.Message()

        val currentTime = Calendar.getInstance().time


        mes1.Recive_Date = currentTime.toString()
        mes2.Recive_Date = currentTime.setTime(currentTime.time - 1000 ).toString()

        val c = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy:MM:dd:hh:mm:ss")
        val s = dateFormat.format(c.time)
        c.add(Calendar.DAY_OF_MONTH, -1)
        val ss = dateFormat.format(c.time)

        val times1 = IntArray(3)
        times1[0] = Integer.parseInt(s.split(":")[0])
        times1[1] = Integer.parseInt(s.split(":")[1])
        times1[2] = Integer.parseInt(s.split(":")[2])

        val times2 = IntArray(3)
        times2[0] = Integer.parseInt(ss.split(":")[0])
        times2[1] = Integer.parseInt(ss.split(":")[1])
        times2[2] = Integer.parseInt(ss.split(":")[2])


        val scal  = shamsiDate( )
        mes1.Recive_Date = scal.shamsiDate(times1[0] , times1[1] , times1[2])
        mes2.Recive_Date = scal.shamsiDate(times2[0] , times2[1] , times2[2])

        mes1.Matn = "   اجراي موفقيت آميز اين برجام ايران را قادر خواهد ساخت تا به طور کامل حق خود بر انرژي هسته اي جهت مقاصد صلح آميز را طبق مواد ذيربط معاهده عدم اشاعه هسته اي و همسو با تعهداتش در آن سند استيفاء نمايد و در نتيجه با برنامه هسته اي ايران همچون برنامه هر دولت ديگر غير دارنده سلاح هاي هسته اي عضو معاهده عدم اشاعه رفتار خواهد شد."
        mes2.Matn = "   اجراي موفقيت آميز اين برجام ايران را قادر خواهد ساخت تا به طور کامل حق خود بر انرژي هسته اي جهت مقاصد صلح آميز را طبق مواد ذيربط معاهده عدم اشاعه هسته اي و همسو با تعهداتش در آن سند استيفاء نمايد و در نتيجه با برنامه هسته اي ايران همچون برنامه هر دولت ديگر غير دارنده سلاح هاي هسته اي عضو معاهده عدم اشاعه رفتار خواهد شد."

        mes1.Send_Date = mes1.Recive_Date
        mes2.Send_Date = mes2.Recive_Date

        mes1.Id = "00"
        mes2.Id = "01"

        mes1.Readed = "YES"
        mes2.Readed = "YES"

        mes1.Reciver_Type = User.kind.Student
        mes2.Reciver_Type = User.kind.Student

        mes1.Tags = "اضطراری"
        mes2.Tags = "اضطراری"

        mes1.Sender_ID = "11"
        mes2.Sender_ID = "11"

        l.add(mes1)
        l.add(mes2)


    //todo go To Self Craated Message List !

        Message.arrayList = l;

        //(activity as MessageActivity).update_messages(STR(response.body()))

        val inte = Intent (this ,ReciverActivity::class.java )
        startActivity(inte)
    }


    fun message(viwe : View){
        //val inte = Intent (this ,ReciverActivity::class.java )
        val inte = Intent (this ,TestDaoActivity::class.java )
        startActivity(inte)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
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
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
