package com.apps.morfiwifi.morfi_project_samane.ui.ticket;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Result;
import com.apps.morfiwifi.morfi_project_samane.models.Ticket;
import com.apps.morfiwifi.morfi_project_samane.models.Ticket_Message;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.User_model;
import com.apps.morfiwifi.morfi_project_samane.ui.student.StudentTicketActivity;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.apps.morfiwifi.morfi_project_samane.view.RecyclerAdapter_ticket;
import com.apps.morfiwifi.morfi_project_samane.view.RecyclerAdapter_tickmessage;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TicketMessageActivity extends AppCompatActivity {
    private static User_model erja_model = null;
    private final  int requestCod = 2056;
    private static Ticket ticket = null;
    RecyclerAdapter_tickmessage recycler;
    HashMap< Ticket_Message , Integer> map = new HashMap<>();
    List<Ticket_Message> ticket_messages = new ArrayList<>();
    int chainge = 0 , closing_cjainge = 0;
    String message = "";
    boolean isLoadig = true, isSaving = false , isOpen = false , isErjaed = false;
    final Handler handler = new Handler(); // update View Handler in MAIN TH;
    Runnable runnable_view = new Runnable() {
        @Override
        public void run() {
            if (isLoadig){
                star_loading();
            }else {
                stop_loading();
                swipeContainer.setRefreshing(false);
            }
            if (isErjaed){
                onBackPressed();
            }


            validateTicketStatus();
            if (closing_cjainge > 0){
                setToolbarVisibility(isOpen);
                closing_cjainge =0;
            }

            if (chainge > 0){
                recycler.Set_List(ticket_messages);
                chainge = 0;
            }
            handler.postDelayed(this , 200);
        }
    };

    Runnable runnable_getTickets = new Runnable() {
        @Override
        public void run() {
//            boolean isloaded = false;

            if (ticket == null)
                return;
            if (ticket.parseObject== null)
                return;

            try {
                isLoadig = true;
                HashMap<String , Object> hashMap = new HashMap<>();
                hashMap.put("ticket" , ticket.parseObject.getObjectId());
                List<ParseObject> list_pre =
                        (List<ParseObject>) ParseCloud.callFunction("ticket_message" , hashMap );



                List<ParseObject> list =new  ArrayList();

                for (int i = list_pre.size()-1; i > -1 ; i--) {
                    list.add(list_pre.get(i));
                }

                ticket_messages = new ArrayList<>();// first load then send !
                for (ParseObject pa :list) {
                    Ticket_Message t = new Ticket_Message(false , pa);
                    ticket_messages.add(t);
                }
                chainge++;
                isLoadig = false;
            } catch (ParseException e) {
                Log.e(getClass().getName() , e.getMessage());
                chainge++;
                isLoadig = false;
            }
        }
    };
    Runnable runnable_sendTickets = new Runnable() {
        @Override
        public void run() {

            ParseObject tic_message = new ParseObject("ticket_message");
            tic_message.put("mess" , message);
            tic_message.put("CreatedBy" , ParseUser.getCurrentUser());
            tic_message.put("SENDER_USERNAME" , ParseUser.getCurrentUser().getUsername()); // this won't chainge
            tic_message.put("ATTACHED" , "NON"); // file attach (server adress)
            tic_message.put("ERJA" , false);
            tic_message.put("HAS_ATTACHED" , false);
            tic_message.put("role_name" , User.current_user.Role);

            ticket.parseObject.add("ticket_message" , tic_message); // Add message to ticket..

            try {
                Ticket_Message messagei = new Ticket_Message(true , tic_message);
                ticket_messages.add(0 ,messagei);
                map.put(  messagei , ticket_messages.indexOf(messagei));
                chainge++;
                isSaving = true;
                ticket.parseObject.save();
                isSaving = false;
                chainge++;
                message = "";
                if (map.containsKey(messagei)){
                    int p = map.get(messagei);
                    ticket_messages.get(p).isLoading = false;
                }
            } catch (ParseException e) {
                Log.e(getClass().getName() , e.getMessage());
                isSaving = false;
                message = "";
                chainge++;
            }
        }
    };
    Runnable runnable_closeTicket = new Runnable() {
        @Override
        public void run() {
            try {
                isLoadig = true;
                ticket.parseObject.put("isopen" , false);
                ticket.parseObject.save();
                isOpen = false;
                isLoadig = false;
                chainge++;
                closing_cjainge++;
            } catch (ParseException e) {
                Log.e(getClass().getName() , e.getMessage());
                isLoadig = false;
            }
        }
    };

    Runnable runnable_erjaTicket = new Runnable() {
        @Override
        public void run() {
            try {
                if (erja_model == null) return;
                HashMap<String , Object> hashMap2 = new HashMap<>();
                hashMap2.put("userId" , erja_model.parseUser.getObjectId());
                String reciver_role_name = ((ParseObject) ParseCloud.callFunction("get_role" ,hashMap2 )).get("name").toString();


                ParseObject tic_message = new ParseObject("ticket_message");
//                tic_message.put("mess" , message);
                tic_message.put("CreatedBy" , ParseUser.getCurrentUser());
                tic_message.put("SENDER_USERNAME" , ParseUser.getCurrentUser().getUsername()); // this won't chainge
                tic_message.put("ATTACHED" , "NON"); // file attach (server adress)
                tic_message.put("ERJA" , true);
                tic_message.put("ERJATO" ,erja_model.parseUser );
                tic_message.put("HAS_ATTACHED" , false);
                tic_message.put("ROLE_NAME" , User.current_user.Role);
                tic_message.put("ERJA_ROLE_NAME" , reciver_role_name);
                tic_message.put("ERJATO_USERNAME" , erja_model.parseUser.getUsername());


                ticket.parseObject.put("LASTERJA" , erja_model.parseUser);
                ticket.parseObject.put("ERJA_ROLE_NAME" , reciver_role_name);
                ticket.parseObject.put("LASTERJA_USERNAEM" , erja_model.parseUser.getUsername());
                ticket.parseObject.add("ticket_message" , tic_message); // Add message to ticket..
                isLoadig = true;
                ticket.parseObject.save();
                isErjaed = true;
                isLoadig = false;
                chainge++;
                closing_cjainge++;
            } catch (ParseException e) {
                Log.e(getClass().getName() , e.getMessage());
                isLoadig = false;
            }
        }
    };
    Thread thread = new Thread(runnable_getTickets);
    Thread thread_send ;
    Thread thread_closeIt ;
    Thread thread_erjaIt ;
    private SwipeRefreshLayout swipeContainer;

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

    public static void setTicket(Ticket ticket) {
            TicketMessageActivity.ticket = ticket;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_message);

        boolean haveTicket =
        getIntent().getExtras().getBoolean("TIVKET_ARRIVED" , false);
        String sender =
        getIntent().getExtras().getString("SENDER" , Init.Empty);

        if (sender.equals(StudentTicketActivity.STUDENT)){
            setToolbarVisibility(false);
        }else {
            if (!ticket.parseObject.getBoolean("isopen")){
                setToolbarVisibility(false);
            }else {
                setToolbarVisibility(true);
            }
        }


        if (haveTicket && ticket != null){
            validateTicketStatus();

            isOpen = ticket.parseObject.getBoolean("isopen");
        }

        swipeContainer =  findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeContainer.setRefreshing(true);
               load_ticket_messages();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        recycler = RecyclerAdapter_tickmessage.Init(null , this);
        load_ticket_messages();
        start_view_thread();


        TextInputEditText m = findViewById(R.id.ti_tick_mess);
        m.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 1){
                    findViewById(R.id.im_sed_tickmess).setVisibility(View.VISIBLE);
                }else if (s.length() <= 1){
                    findViewById(R.id.im_sed_tickmess).setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void validateTicketStatus() {
        TextView status =
                findViewById(R.id.tv_ticket_status);

        String head = Init.notNull(ticket.parseObject.get("header")) +
                (ticket.parseObject.getBoolean("isopen")?": باز" : ": بسته") ;
        status.setText(head);
        if (!ticket.parseObject.getBoolean("isopen")){
            findViewById(R.id.lin_sending_aria).setVisibility(View.GONE);
        }else {
            findViewById(R.id.lin_sending_aria).setVisibility(View.VISIBLE);
        }
    }

    public void start_view_thread(){
        handler.removeCallbacks(runnable_view);
        handler.postDelayed(runnable_view , 200);
    }
    public void load_ticket_messages(){
        if (!thread.isAlive()){
            thread = new Thread(runnable_getTickets);
            thread.start();
        }

    }

    public void star_loading (){
        findViewById(R.id.tv_please_bepashent).setVisibility(View.VISIBLE);
        findViewById(R.id.rec_tick_message).setVisibility(View.GONE);
        AVLoadingIndicatorView avi = findViewById(R.id.avi_loading);
        avi.setVisibility(View.VISIBLE);
        if (!avi.isActivated()){
            avi.show();
        }
    }

    public void stop_loading (){
        findViewById(R.id.tv_please_bepashent).setVisibility(View.GONE);
        findViewById(R.id.rec_tick_message).setVisibility(View.VISIBLE);
        AVLoadingIndicatorView avi = findViewById(R.id.avi_loading);
        avi.setVisibility(View.GONE);
        avi.hide();
    }

    public void click_send(View view) {
        TextInputEditText editText =
        findViewById(R.id.ti_tick_mess);
        message = editText.getText().toString();
        editText.getText().clear();

        if (!isOpen){
            Toast.makeText(this, "بسته شده!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isSaving){
            Toast.makeText(this, "صبر ...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (thread_send == null){
            thread_send = new Thread(runnable_sendTickets);
            thread_send.start();
            return;
        }

        if (!thread_send.isAlive()){
            thread_send = new Thread(runnable_sendTickets);
            thread_send.start();
        }else {
            Toast.makeText(this, "مشکلی برای ارسال وجود دارد", Toast.LENGTH_SHORT).show();
        }
    }

    public void setToolbarVisibility (boolean b){
        if (b){
            findViewById(R.id.im_forward_ticker).setVisibility(View.VISIBLE);
            findViewById(R.id.im_close_ticket).setVisibility(View.VISIBLE);
            findViewById(R.id.tv_close_text).setVisibility(View.VISIBLE);
            findViewById(R.id.tv_erja_text).setVisibility(View.VISIBLE);
        }else {
            findViewById(R.id.im_forward_ticker).setVisibility(View.GONE);
            findViewById(R.id.im_close_ticket).setVisibility(View.GONE);
            findViewById(R.id.tv_close_text).setVisibility(View.GONE);
            findViewById(R.id.tv_erja_text).setVisibility(View.GONE);
        }

    }

    public void CloseTicket(View view) {
        if (ticket == null) return;
        if (ticket.parseObject == null) return;
        if (thread_closeIt == null){
            thread_closeIt = new Thread(runnable_closeTicket);
            thread_closeIt.start();
            return;
        }
        if (thread_closeIt.isAlive()){
            Toast.makeText(this, "درحال بستن!", Toast.LENGTH_SHORT).show();
        }else {
            if (!isOpen){
                Toast.makeText(this, "بسته است!", Toast.LENGTH_SHORT).show();
            }else {
                thread_closeIt = new Thread(runnable_closeTicket);
                thread_closeIt.start();
            }
        }
    }

    public void ForwardTicket(View view) {
        // TODO: 11/16/2018 FORWARD THE TICKET & BLOCK VIEW...
        Intent intent = new Intent(this , ChooseUserActivity.class);
        intent.putExtra(ChooseUserActivity.min_role_level , 1 ); // NO ERJA TO STD!!
        startActivityForResult(intent , requestCod);
    }

    public static void setErja_model(User_model erja_model) {
        if (erja_model == null)return;
        TicketMessageActivity.erja_model = erja_model;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == requestCod){
            if (resultCode == RESULT_CANCELED ){
                // DINDT ERJA ! > canceled
//                Toast.makeText(this, "WAS CANCLED", Toast.LENGTH_SHORT).show();
            }else if (resultCode == RESULT_OK){
                // 1. add message [erja] 2.load 3.block ui
//                Toast.makeText(this, "WAS OK", Toast.LENGTH_SHORT).show();
                if (data.getExtras() == null)return; // go hell out .....
                boolean has_data =
                data.getExtras().getBoolean("hasResult" , false);
                if (has_data){
                    ErjaTicket();
                }
            }
        }
    }

    private void ErjaTicket() {
        if (thread_erjaIt == null){
            thread_erjaIt = new Thread(runnable_erjaTicket);
            thread_erjaIt.start();
            return;
        }
        if (!thread_erjaIt.isAlive()){
            thread_erjaIt = new Thread(runnable_erjaTicket);
            thread_erjaIt.start();
            return;
        }else {
            Toast.makeText(this, "درحال ارجاء دادن", Toast.LENGTH_SHORT).show();
        }
    }
}
