package com.apps.morfiwifi.morfi_project_samane.models;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WifiMorfi on 3/20/2018.
 */

@Entity
public class Message {
    public  static ArrayList<Message> arrayList = new ArrayList<>();

   // private static RetrofitDataService mTService;

    public enum  State{
        notSeen , seen , answered , finished , workingon , don;

        public static State fromInteger(int x) {
            int count = values().length;
            return values()[x % count];
        }

        @Override
        public String toString() {
            switch (this){
                case don:
                    return "خاتمه یافته";
                case seen:
                    return "دیده شده";
                case notSeen:
                    return "دیده نشده";
                case answered:
                    return "جواب داده شده";
                case finished:
                    return "تمام شده";
                case workingon:
                    return "درحال پیگیری";
                    default:
                        return "مشخص نیست";
            }

            //return super.toString();
        }
    }

    public State get_State (){
        return State.fromInteger(this.State_message);
    }

    public void set_State (State state){
        this.State_message = state.ordinal();
    }


    // Use Sendre ID as Outer Value ( Auto mated in server -Complexity here rises!)
    // Chaingigng in ID
    @org.greenrobot.greendao.annotation.Id (autoincrement = true)
    public Long Id;
    public String Send_Date;
    public String Recive_Date;
    public String Tags;
    public String Matn;
    public String Sender_ID;
    public String Reciver_ID;
    public String Readed;
    public int Reciver_Type;
    public int State_message;
    public boolean CanAnsewr;
    public Long answer_id;


    @ToOne
    private User sender; // Also Reciver !!

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 859287859)
    private transient MessageDao myDao;

    @Generated(hash = 2105518247)
    private transient boolean sender__refreshed;


    //public User.Kind Reciver_Type;

    public Message(){
        Id = 0l;
        Send_Date = "";
        Recive_Date = "";
        Tags = "";
        Matn = "";
        Sender_ID = "";
        Reciver_ID = "";
        Readed = "";
        Reciver_Type = 0;
    }

    @Generated(hash = 628032992)
    public Message(Long Id, String Send_Date, String Recive_Date, String Tags, String Matn, String Sender_ID,
            String Reciver_ID, String Readed, int Reciver_Type, int State_message, boolean CanAnsewr, Long answer_id) {
        this.Id = Id;
        this.Send_Date = Send_Date;
        this.Recive_Date = Recive_Date;
        this.Tags = Tags;
        this.Matn = Matn;
        this.Sender_ID = Sender_ID;
        this.Reciver_ID = Reciver_ID;
        this.Readed = Readed;
        this.Reciver_Type = Reciver_Type;
        this.State_message = State_message;
        this.CanAnsewr = CanAnsewr;
        this.answer_id = answer_id;
    }

    /*public static String GetMessages (final Context context , final AppCompatActivity activity){
        RetrofitDataProvider provider = new RetrofitDataProvider();
        mTService = provider.getTService();

        Call<List<Message>> call = mTService.GetMessages(TokenModel.TokenSTR);

        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response == null){
                    Init.Toas(context , "Why Null IN Message !");
                    Init.Println("Why Null IN Message !");
                }
                else if (response.isSuccessful()){
                    if (activity instanceof MessageActivity){
                        ((MessageActivity) activity).update_messages(STR(response.body()));
                    }
                    if (activity instanceof ReciverActivity){
                        message_RecyclerAdapter.Init(response.body() , activity);
                        //((MessageActivity) activity).update_messages(STR(response.body()));
                    }
                }
                else {
                    Init.Println("mid error in Message Get");
                    Init.Toas(context , "mid error in Message Get");
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Init.Println("Real error in Message Get");
                Init.Toas(context , "Real error in Message Get");
            }
        });


        return "no its Async";
    }*/

   /* public static String InsertMessages (final Context context , final AppCompatActivity activity , Message message){
        RetrofitDataProvider provider = new RetrofitDataProvider();
        mTService = provider.getTService();

        Call<Message> call = mTService.InsertMessages(TokenModel.TokenSTR ,message);

        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response == null){
                    Init.Toas(context , "Why Null IN Message Put !");
                    Init.Println("Why Null IN Message Put !");
                }
                else if (response.isSuccessful()){
                    if (activity instanceof MessageActivity){
                        Init.Toas(context , "Message Sent (Sucsess)");
                        //((MessageActivity) activity).update_messages(STR(response.body()));
                    }
                }
                else {
                    Init.Println("mid error in Message put");
                    Init.Toas(context , "mid error in Message put");
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Init.Println("Real error in Message put");
                Init.Toas(context , "Real error in Message put");
            }


        });


        return "no its Async";
    }*/


    private static String STR (List<Message> messages){
        String res = "";
        for (int i = 0; i < messages.size(); i++) {
            res = res + messages.get(i).Matn;
        }



        return res;
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getSend_Date() {
        return this.Send_Date;
    }

    public void setSend_Date(String Send_Date) {
        this.Send_Date = Send_Date;
    }

    public String getRecive_Date() {
        return this.Recive_Date;
    }

    public void setRecive_Date(String Recive_Date) {
        this.Recive_Date = Recive_Date;
    }

    public String getTags() {
        return this.Tags;
    }

    public void setTags(String Tags) {
        this.Tags = Tags;
    }

    public String getMatn() {
        return this.Matn;
    }

    public void setMatn(String Matn) {
        this.Matn = Matn;
    }

    public String getSender_ID() {
        return this.Sender_ID;
    }

    public void setSender_ID(String Sender_ID) {
        this.Sender_ID = Sender_ID;
    }

    public String getReciver_ID() {
        return this.Reciver_ID;
    }

    public void setReciver_ID(String Reciver_ID) {
        this.Reciver_ID = Reciver_ID;
    }

    public String getReaded() {
        return this.Readed;
    }

    public void setReaded(String Readed) {
        this.Readed = Readed;
    }

    public int getReciver_Type() {
        return this.Reciver_Type;
    }

    public void setReciver_Type(int Reciver_Type) {
        this.Reciver_Type = Reciver_Type;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 677842067)
    public User getSender() {
        if (sender != null || !sender__refreshed) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            targetDao.refresh(sender);
            sender__refreshed = true;
        }
        return sender;
    }

    /** To-one relationship, returned entity is not refreshed and may carry only the PK property. */
    @Generated(hash = 306704630)
    public User peakSender() {
        return sender;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 122650586)
    public void setSender(User sender) {
        synchronized (this) {
            this.sender = sender;
            sender__refreshed = true;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public int getState_message() {
        return this.State_message;
    }

    public void setState_message(int State_message) {
        this.State_message = State_message;
    }

    public boolean getCanAnsewr() {
        return this.CanAnsewr;
    }

    public void setCanAnsewr(boolean CanAnsewr) {
        this.CanAnsewr = CanAnsewr;
    }

    public Long getAnswer_id() {
        return this.answer_id;
    }

    public void setAnswer_id(Long answer_id) {
        this.answer_id = answer_id;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 747015224)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMessageDao() : null;
    }


}
