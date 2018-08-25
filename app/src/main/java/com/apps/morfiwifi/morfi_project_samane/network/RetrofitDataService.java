/*
package com.apps.morfiwifi.morfi_project_samane.network;

//import adapter.OperationResultModel;
import com.apps.morfiwifi.morfi_project_samane.models.AuthenticationResponseModel;
import com.apps.morfiwifi.morfi_project_samane.models.CallModel;
import com.apps.morfiwifi.morfi_project_samane.models.ContractModel;
import com.apps.morfiwifi.morfi_project_samane.models.CustomerModel;
import com.apps.morfiwifi.morfi_project_samane.models.LoginModel;
import com.apps.morfiwifi.morfi_project_samane.models.Message;
import com.apps.morfiwifi.morfi_project_samane.models.Order_DetailsModel;
import com.apps.morfiwifi.morfi_project_samane.models.SignInRequestModel;
import com.apps.morfiwifi.morfi_project_samane.models.SignUpRequestModel;
import com.apps.morfiwifi.morfi_project_samane.models.SupplierModel;
import com.apps.morfiwifi.morfi_project_samane.models.User;
import com.apps.morfiwifi.morfi_project_samane.models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

*/
/**
 * the interface implements REST API routes
 *//*

public interface RetrofitDataService {
////////////////////////////call api service


    @POST("/login")
    void basicLogin(Callback<UserModel> cb);

    //>>> TEst Token in Call Model!!!
    @POST("Token")
    Call<String> GetToken(@Body LoginModel loginModel);

    @GET("Token")
    Call<String> Tokem();

    ///  Mainly our NEW PROJECT Zero_Down!
    ///  Mainly our NEW PROJECT Zero_Down!

    @GET("Message")
    Call<List<Message>> GetMessages( @Header("Authorization") String Tokenscr );

    @POST("Message")
    Call<Message> InsertMessages (@Header("Authorization") String Tokenscr, @Body Message message);

    @GET("User")
    Call<User> GetUser(@Header("Authorization") String Tokenscr );








    /// END OF Mainly our NEW PROJECT Zero_Down!
    /// END OF Mainly our NEW PROJECT Zero_Down!











































    @POST("IncomingCalls2")
    Call<CallModel> createNewCall(@Header("Authorization") String Tokenscr, @Body CallModel callModel);

    @GET("IncomingCalls2")
    Call<List<CallModel>> getCalls(@Header("Authorization") String Tokenscr);
//    public void getCalls (Callback<List<CallModel>> respons);

    @GET("IncomingCalls2/{id}")
    Call<CallModel> getCallById(@Header("Authorization") String Tokenscr, @Path("id") String callId);

    @PUT("IncomingCalls2/{id}")
    Call<CallModel> updateCallById(@Header("Authorization") String Tokenscr, @Path("id") String callId, @Body CallModel callModel);

    @DELETE("IncomingCalls2/{id}")
    Call<CallModel> deleteCallById(@Header("Authorization") String Tokenscr, @Path("id") String Id);
//////////////////////////////////////////////
////////////////////////////costumer api service
    @POST("Customers2")
    Call<CustomerModel> createNewCustomer(@Header("Authorization") String Tokenscr, @Body CustomerModel customerModel);

    @GET("Customers2")
    Call<List<CustomerModel>> getCustomer(@Header("Authorization") String Tokenscr);

    @GET("Customers2/{id}")
    Call<CustomerModel> getCustomerById(@Header("Authorization") String Tokenscr, @Path("id") String Id);

    @PUT("Customers2/{id}")
    Call<CustomerModel> updatecostumerById(@Header("Authorization") String Tokenscr, @Path("id") String Id, @Body CustomerModel CustomerModel);

    @DELETE("Customers2/{id}")
    Call<CustomerModel> deleteCustomerById(@Header("Authorization") String Tokenscr, @Path("id") String Id);
//////////////////////////////////////////////
////////////////////////////supplier api service
    @POST("Suppliers2")
    Call<SupplierModel> createNewSupplier(@Header("Authorization") String Tokenscr, @Body SupplierModel SupplierModel);

    @GET("Suppliers2")
    Call<List<SupplierModel>> getSupplier(@Header("Authorization") String Tokenscr);

    @GET("Suppliers2/{id}")
    Call<SupplierModel> getSupplierById(@Header("Authorization") String Tokenscr, @Path("id") String Id);

    @PUT("Suppliers2/{id}")
    Call<SupplierModel> updateSupplierById(@Header("Authorization") String Tokenscr, @Path("id") String Id, @Body SupplierModel SupplierModel);

    @DELETE("Suppliers2/{id}")
    Call<SupplierModel> deleteSupplierById(@Header("Authorization") String Tokenscr, @Path("id") String Id);
//////////////////////////////////////////////
////////////////////////////Order api service
    @POST("Orders2")
    Call<OrderModel> createNewOrder(@Header("Authorization") String Tokenscr, @Body OrderModel orderModel);

    @GET("Orders2")
    Call<List<OrderModel>> getOrder(@Header("Authorization") String Tokenscr);

    @GET("Orders2/{id}")
    Call<OrderModel> getOrderById(@Header("Authorization") String Tokenscr, @Path("id") String Id);

    @PUT("Orders2/{id}")
    Call<OrderModel> updateSupplierById(@Header("Authorization") String Tokenscr, @Path("id") String Id, @Body OrderModel orderModel);

    @DELETE("Orders2/{id}")
    Call<OrderModel> deleteOrderById(@Header("Authorization") String Tokenscr, @Path("id") String Id);
//////////////////////////////////////////////
////////////////////////////Product api service
    @POST("Products2")
    Call<ProductModel> createNewProduct(@Header("Authorization") String Tokenscr, @Body ProductModel productModel);

    @GET("Products2")
    Call<List<ProductModel>> getProduct(@Header("Authorization") String Tokenscr);

    @GET("Products2/{id}")
    Call<ProductModel> getProductById(@Header("Authorization") String Tokenscr, @Path("id") String Id);

    @PUT("Products2/{id}")
    Call<ProductModel> updateProductById(@Header("Authorization") String Tokenscr, @Path("id") String Id, @Body ProductModel productModel);

    @DELETE("Products2/{id}")
    Call<ProductModel> deleteProductById(@Header("Authorization") String Tokenscr, @Path("id") String Id);
    //////////////////////////////////////////////
    ////////////////////////////Contracts api service
    @POST("Contracts2")
    Call<ContractModel> createNewContract(@Header("Authorization") String Tokenscr, @Body ContractModel contractModel);

    @GET("Contracts2")
    Call<List<ContractModel>> getContract(@Header("Authorization") String Tokenscr);

    @GET("Contracts2/{id}")
    Call<ContractModel> getContractById(@Header("Authorization") String Tokenscr, @Path("id") String Id);

    @PUT("Contracts2/{id}")
    Call<ContractModel> updateContractById(@Header("Authorization") String Tokenscr, @Path("id") String Id, @Body ContractModel contractModel);

    @DELETE("Contracts2/{id}")
    Call<ContractModel> deleteContractById(@Header("Authorization") String Tokenscr, @Path("id") String Id);
    //////////////////////////////////////////////
    ////////////////////////////Order_Detail api service
    @POST("Order_Detail2")
    Call<Order_DetailsModel> createNewOrder_Detail(@Header("Authorization") String Tokenscr, @Body Order_DetailsModel order_DetailsModel);

    @GET("Order_Detail2")
    Call<List<Order_DetailsModel>> getOrder_Detail(@Header("Authorization") String Tokenscr);

    @GET("Order_Detail2/{id}")
    Call<Order_DetailsModel> getOrder_DetailById(@Header("Authorization") String Tokenscr, @Path("id") String Id);

    @PUT("Order_Detail2/{id}")
    Call<Order_DetailsModel> updateOrder_DetailById(@Header("Authorization") String Tokenscr, @Path("id") String Id, @Body Order_DetailsModel order_DetailsModel);

    @DELETE("Order_Detail2/{id}")
    Call<Order_DetailsModel> deleteOrder_DetailsById(@Header("Authorization") String Tokenscr, @Path("id") String Id);
    //////////////////////////////////////////////
////////////////////////////Users api service
    @POST("Users2")
    Call<UserModel> createNewUser(@Header("Authorization") String Tokenscr, @Body UserModel userModel);

    @GET("Users2")
    Call<List<UserModel>> getUser(@Header("Authorization") String Tokenscr);

    @GET("Users2/{id}")
    Call<UserModel> getUserById(@Header("Authorization") String Tokenscr, @Path("id") String Id);

    @PUT("Users2/{id}")
    Call<UserModel> updateUserById(@Header("Authorization") String Tokenscr, @Path("id") String Id, @Body UserModel userModel);

    @DELETE("Users2/{id}")
    Call<UserModel> deleteuserById(@Header("Authorization") String Tokenscr, @Path("id") String Id);
//////////////////////////////////////////////

    @POST("token")
    Call<LoginModel> LogIn(@Header("Authorization") String Tokenscr, @Body LoginModel loginModel);

    //>> Extend Chck Getting Tokem as String
    @POST("Users2")
    Call<String> LoginAsync(@Header("Authorization") String Tokenscr, @Body LoginModel loginModel);


    @POST("signup")
    Call<AuthenticationResponseModel> signUp(@Header("Authorization") String Tokenscr, @Body SignUpRequestModel signUpRequestModel);

    @POST("token")
    Call<AuthenticationResponseModel> signIn(@Header("Authorization") String Tokenscr, @Body SignInRequestModel signInRequestModel);

    //@POST("user/app")
    //Call<OperationResultModel> terminateApp(@Header("Authorization") String authHeader);

}

*/
