package com.apps.morfiwifi.morfi_project_samane.models;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;

import java.util.Date;
import java.util.List;

/**
 * Created by WifiMorfi on 3/20/2018.
 */


@Entity
public class User   {
    @Transient
    public static  User current_user; // for different tasks

    public enum Kind {
        Student, Master, Technical, Site_Master, Self_Service, Admin ;


        public static Kind fromInteger(int x) {
            int count = values().length;
            return values()[x % count];
        }

        @Override
        public String toString() {
            switch (this){
                case Student:
                    return "دانشجو";
                case Master:
                    return "استاد";
                case Technical:
                    return "مسئول فنی";
                case Site_Master:
                    return "مسئول سایت";
                case Self_Service:
                    return "مسئول سلف";
                case Admin:
                    return "ادمین سیستم";
                default:
                    return "مشخص نیست";
            }

            //return super.toString();
        }
    }

    public Kind get_Type (){
        return Kind.fromInteger(this.Type);
    }

    public void set_Type (Kind kind){
        this.Type = kind.ordinal();
    }


    //private static RetrofitDataService mTService;
    @org.greenrobot.greendao.annotation.Id(autoincrement = true)
    public Long Id;
    public String UserName;
    public String FName;
    public String LName;
    public String Pass;
    public String Pass_hash;
    public String Kaet_meli;
    public String Student_id = "0" ; // from Uni
    public boolean should_fill_init_forms = true;
    public boolean Active = false;
    public boolean Deleted = false;
    public int Type;
    public Date inset_date;

    @ToMany(referencedJoinProperty = "id")
    public List<Samane> samanes;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1507654846)
    private transient UserDao myDao;





    @Generated(hash = 649569981)
    public User(Long Id, String UserName, String FName, String LName, String Pass, String Pass_hash,
            String Kaet_meli, String Student_id, boolean should_fill_init_forms, boolean Active,
            boolean Deleted, int Type, Date inset_date) {
        this.Id = Id;
        this.UserName = UserName;
        this.FName = FName;
        this.LName = LName;
        this.Pass = Pass;
        this.Pass_hash = Pass_hash;
        this.Kaet_meli = Kaet_meli;
        this.Student_id = Student_id;
        this.should_fill_init_forms = should_fill_init_forms;
        this.Active = Active;
        this.Deleted = Deleted;
        this.Type = Type;
        this.inset_date = inset_date;
    }

    @Generated(hash = 586692638)
    public User() {
    }





    /*public static User GetUser (final Context context , final AppCompatActivity activity){

        RetrofitDataProvider provider = new RetrofitDataProvider();
        mTService = provider.getTService();

        Call<User> call = mTService.GetUser(TokenModel.TokenSTR);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response == null){
                    Init.Toas(context , "Why Null IN User !");
                    Init.Println("Why Null IN User !");
                }
                else if (response.isSuccessful()){
                    if (activity instanceof MessageActivity){
                        ((MessageActivity) activity).update_user(response.body());
                    }
                }
                else {
                    Init.Println("mid error in user Get");
                    Init.Toas(context , "mid error in user Get");
                }


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Init.Println("User Really Failed!");
                Init.Toas(context , "User Really Failed!");
            }
        });

        return new User();
    }*/





    public Long getId() {
        return this.Id;
    }





    public void setId(Long Id) {
        this.Id = Id;
    }





    public String getFName() {
        return this.FName;
    }





    public void setFName(String FName) {
        this.FName = FName;
    }





    public String getLName() {
        return this.LName;
    }





    public void setLName(String LName) {
        this.LName = LName;
    }





    public String getPass() {
        return this.Pass;
    }





    public void setPass(String Pass) {
        this.Pass = Pass;
    }





    public String getPass_hash() {
        return this.Pass_hash;
    }





    public void setPass_hash(String Pass_hash) {
        this.Pass_hash = Pass_hash;
    }





    public String getKaet_meli() {
        return this.Kaet_meli;
    }





    public void setKaet_meli(String Kaet_meli) {
        this.Kaet_meli = Kaet_meli;
    }





    public int getType() {
        return this.Type;
    }





    public void setType(int Type) {
        this.Type = Type;
    }

    public Kind getKind (){
        return Kind.values()[getType()% Kind.values().length];
    }

    public void setkind(Kind kinde){
        Kind[] vals = Kind.values();
        setType(0);
        for (int i = 0; i <vals.length ; i++) {
            if (vals[i].equals(kinde)){
                setType(i);
                break;
            }
        }
    }





    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1781915660)
    public List<Samane> getSamanes() {
        if (samanes == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SamaneDao targetDao = daoSession.getSamaneDao();
            List<Samane> samanesNew = targetDao._queryUser_Samanes(Id);
            synchronized (this) {
                if (samanes == null) {
                    samanes = samanesNew;
                }
            }
        }
        return samanes;
    }





    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 917475477)
    public synchronized void resetSamanes() {
        samanes = null;
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





    public String getUserName() {
        return this.UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public boolean getActive() {
        return this.Active;
    }

    public void setActive(boolean Active) {
        this.Active = Active;
    }

    public boolean getDeleted() {
        return this.Deleted;
    }

    public void setDeleted(boolean Deleted) {
        this.Deleted = Deleted;
    }

    public boolean getShould_fill_init_forms() {
        return this.should_fill_init_forms;
    }

    public void setShould_fill_init_forms(boolean should_fill_init_forms) {
        this.should_fill_init_forms = should_fill_init_forms;
    }

    public String getStudent_id() {
        return this.Student_id;
    }

    public void setStudent_id(String Student_id) {
        this.Student_id = Student_id;
    }

    public Date getInset_date() {
        return this.inset_date;
    }

    public void setInset_date(Date inset_date) {
        this.inset_date = inset_date;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }
}
