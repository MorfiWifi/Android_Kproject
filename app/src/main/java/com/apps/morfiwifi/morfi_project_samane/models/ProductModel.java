package com.apps.morfiwifi.morfi_project_samane.models;

import android.content.Context;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.network.FakeDataProvider;
import com.apps.morfiwifi.morfi_project_samane.network.FakeDataService;
import com.apps.morfiwifi.morfi_project_samane.utility.ErrorUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by m.hosein on 11/10/2017.
 */

public class ProductModel {
    public int Id;
    public String Name;
    public int UnitPrice;
    public int UnitsInStock;
    private FakeDataService mTService;
    public int SupplierId;
    public SupplierModel Supplier;
    public List<Order_DetailsModel> Order_Details;

    public static void getProductFromServer(final Context context) {
        FakeDataProvider provider = new FakeDataProvider();
        ProductModel pm  = new ProductModel();
        pm.mTService = provider.getTService();
        Call<List<ProductModel>> call = pm.mTService.getProduct();
        call.enqueue(new Callback<List<ProductModel>>() {
            TempFill_Model temp = new TempFill_Model();
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {

                if (response.isSuccessful()) {
                    temp.list = response.body();
                    temp.res = true;
                } else {
                    temp.list = null;
                    temp.res = false;
                    ErrorModel errorModel = ErrorUtils.parseError(response);
                    Toast.makeText(context, "Error type is " + errorModel.Message + " , description " + errorModel.MessageDetail, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                //occur when fail to deserialize || no network connection || server unavailable
                Toast.makeText(context, "Fail it >> " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public String getTitle(){
        return this.Name;
    }

}
