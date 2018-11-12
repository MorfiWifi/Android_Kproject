package com.apps.morfiwifi.morfi_project_samane.advance;

import android.content.Context;
//import android.test.InstrumentationTestCase;
//import android.test.mock.MockContext;

import com.apps.morfiwifi.morfi_project_samane.App;
import com.apps.morfiwifi.morfi_project_samane.LoginActivity;
import com.apps.morfiwifi.morfi_project_samane.TApplication;
import com.parse.Parse;
import com.parse.ParseConfig;
import com.parse.ParseObject;
import com.parse.ParseUser;

//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static org.junit.Assert.assertThat;

//@RunWith(RobolectricTestRunner.class)
public class Tester //extends InstrumentationTestCase
{
//    Context context;
//    public void setUp() throws Exception {
//        super.setUp();
//        context = new MockContext();
//        assertNotNull(context);
//    }

//    @Test
    public void test1() {
//        System.out.println("ADSADAD");
//        assertNotNull("sadsa" , "as");
        print("test1_started");
        try {
//            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        assertThat("asdas" ,"asdaddsad").isTrue();
//        InstrumentationRegistry.getTargetContext();
        print("init parse");
//        init_parse(context);
        ParseUser user = null;
        try {
            print("getting user");
            user =  ParseUser.logIn("morteza" , "1990");
        }catch (Exception e ){
            print("user ex");
            print(e.getMessage());
        }

        print("after all");
        print(user.getUsername());
        print(user.getSessionToken());


//        assertNotNull(context);
//        assertNotNull(user);
//        assertNull(context);
        print("sadsad");
    }


    public  static void  main (String[] arg){
//        Parse.getApplicationContext();
        TApplication tApplication = new TApplication();
        Context context = tApplication.getApplicationContext();




//        App.Parse_init();
//        ParseObject object = new ParseObject("");
        print("FINISHED >>>>");

    }
    static void print(String s){
        System.out.println(s);
    }

    static void init_parse( Context context){
            Parse.initialize(new Parse.Configuration.Builder(context)
//                .applicationId(Security.Decript_STR(getNativeKey1()))
                            .applicationId("z8ABZI7P44AzqQHSShS09qtKpcVjx8Dk8F9WipRB")
                            // .applicationId("first")
//                .clientKey(Security.Decript_STR(getNativeKey2()))
                            .clientKey("fmbYAhCnAHlXvk9p8B2UBPK8vaeyTm1ZOZ1zPxq9")

//                .clientKey("QE5Pwhr60WAfd24VtGcFIV3KHDStpK1Q2WgtP4yr") // MASTER
                            .server("https://parseapi.back4app.com/")
//                .server(Security.Decript_STR(getserver()))

                            //.server("http://192.168.1.110:1337/parse/")
                            .build()
            );

    }
}
