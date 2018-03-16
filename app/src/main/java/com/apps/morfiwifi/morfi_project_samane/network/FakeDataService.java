package com.apps.morfiwifi.morfi_project_samane.network;

import com.apps.morfiwifi.morfi_project_samane.models.AuthenticationResponseModel;
import com.apps.morfiwifi.morfi_project_samane.models.CallModel;
import com.apps.morfiwifi.morfi_project_samane.models.ContractModel;
import com.apps.morfiwifi.morfi_project_samane.models.CustomerModel;
import com.apps.morfiwifi.morfi_project_samane.models.LogInViewModel;
import com.apps.morfiwifi.morfi_project_samane.models.OrderModel;
import com.apps.morfiwifi.morfi_project_samane.models.Order_DetailsModel;
import com.apps.morfiwifi.morfi_project_samane.models.ProductModel;
import com.apps.morfiwifi.morfi_project_samane.models.SignInRequestModel;
import com.apps.morfiwifi.morfi_project_samane.models.SignUpRequestModel;
import com.apps.morfiwifi.morfi_project_samane.models.SupplierModel;
import com.apps.morfiwifi.morfi_project_samane.models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * the interface implements REST API routes
 */
public interface FakeDataService {
////////////////////////////call api service
    @POST("IncomingCalls")
    Call<CallModel> createNewCall(@Body CallModel callModel);

    @GET("IncomingCalls")
    Call<List<CallModel>> getCalls();
//    public void getCalls (Callback<List<CallModel>> respons);

    @GET("IncomingCalls/{id}")
    Call<CallModel> getCallById(@Path("id") String callId);

    @PUT("IncomingCalls/{id}")
    Call<CallModel> updateCallById(@Path("id") String callId, @Body CallModel callModel);

    @DELETE("tIncomingCalls/{id}")
    Call<CallModel> deleteCallById(@Path("id") String Id);
//////////////////////////////////////////////
////////////////////////////costumer api service
    @POST("Customers")
    Call<CustomerModel> createNewCustomer(@Body CustomerModel customerModel);

    @GET("Customers")
    Call<List<CustomerModel>> getCustomer();

    @GET("Customers/{id}")
    Call<CustomerModel> getCustomerById(@Path("id") String Id);

    @PUT("Customers/{id}")
    Call<CustomerModel> updatecostumerById(@Path("id") String Id, @Body CustomerModel CustomerModel);

    @DELETE("Customers/{id}")
    Call<CustomerModel> deleteCustomerById(@Path("id") String Id);
//////////////////////////////////////////////
////////////////////////////supplier api service
    @POST("Suppliers")
    Call<SupplierModel> createNewSupplier(@Body SupplierModel SupplierModel);

    @GET("Suppliers")
    Call<List<SupplierModel>> getSupplier();

    @GET("Suppliers/{id}")
    Call<SupplierModel> getSupplierById(@Path("id") String Id);

    @PUT("Suppliers/{id}")
    Call<SupplierModel> updateSupplierById(@Path("id") String Id, @Body SupplierModel SupplierModel);

    @DELETE("Suppliers/{id}")
    Call<SupplierModel> deleteSupplierById(@Path("id") String Id);
//////////////////////////////////////////////
////////////////////////////Order api service
    @POST("Orders")
    Call<OrderModel> createNewOrder(@Body OrderModel orderModel);

    @GET("Orders")
    Call<List<OrderModel>> getOrder();

    @GET("Orders/{id}")
    Call<OrderModel> getOrderById(@Path("id") String Id);

    @PUT("Orders/{id}")
    Call<OrderModel> updateSupplierById(@Path("id") String Id, @Body OrderModel orderModel);

    @DELETE("Orders/{id}")
    Call<OrderModel> deleteOrderById(@Path("id") String Id);
//////////////////////////////////////////////
////////////////////////////Product api service
    @POST("Products")
    Call<ProductModel> createNewProduct(@Body ProductModel productModel);

    @GET("Products")
    Call<List<ProductModel>> getProduct();

    @GET("Products/{id}")
    Call<ProductModel> getProductById(@Path("id") String Id);

    @PUT("Products/{id}")
    Call<ProductModel> updateProductById(@Path("id") String Id, @Body ProductModel productModel);

    @DELETE("Products/{id}")
    Call<ProductModel> deleteProductById(@Path("id") String Id);
    //////////////////////////////////////////////
    ////////////////////////////Contracts api service
    @POST("Contracts")
    Call<ContractModel> createNewContract(@Body ContractModel contractModel);

    @GET("Contracts")
    Call<List<ContractModel>> getContract();

    @GET("Contracts/{id}")
    Call<ContractModel> getContractById(@Path("id") String Id);

    @PUT("Contracts/{id}")
    Call<ContractModel> updateContractById(@Path("id") String Id, @Body ContractModel contractModel);

    @DELETE("Contracts/{id}")
    Call<ContractModel> deleteContractById(@Path("id") String Id);
    //////////////////////////////////////////////
    ////////////////////////////Order_Detail api service
    @POST("Order_Detail")
    Call<Order_DetailsModel> createNewOrder_Detail(@Body Order_DetailsModel order_DetailsModel);

    @GET("Order_Detail")
    Call<List<Order_DetailsModel>> getOrder_Detail();

    @GET("Order_Detail/{id}")
    Call<Order_DetailsModel> getOrder_DetailById(@Path("id") String Id);

    @PUT("Order_Detail/{id}")
    Call<Order_DetailsModel> updateOrder_DetailById(@Path("id") String Id, @Body Order_DetailsModel order_DetailsModel);

    @DELETE("Order_Detail/{id}")
    Call<Order_DetailsModel> deleteOrder_DetailsById(@Path("id") String Id);
    //////////////////////////////////////////////
////////////////////////////Users api service
    @POST("Users")
    Call<UserModel> createNewUser(@Body UserModel userModel);

    @GET("Users")
    Call<List<UserModel>> getUser();

    @GET("Users/{id}")
    Call<UserModel> getUserById(@Path("id") String Id);

    @PUT("Users/{id}")
    Call<UserModel> updateUserById(@Path("id") String Id, @Body UserModel userModel);

    @DELETE("Users/{id}")
    Call<UserModel> deleteuserById(@Path("id") String Id);
//////////////////////////////////////////////

    @POST("token")
    Call<LogInViewModel> LogIn(@Body LogInViewModel logInViewModel);




    @POST("signup")
    Call<AuthenticationResponseModel> signUp(@Body SignUpRequestModel signUpRequestModel);

    @POST("token")
    Call<AuthenticationResponseModel> signIn(@Body SignInRequestModel signInRequestModel);

   // @POST("user/app")
   // Call<OperationResultModel> terminateApp(@Header("Authorization") String authHeader);

}
