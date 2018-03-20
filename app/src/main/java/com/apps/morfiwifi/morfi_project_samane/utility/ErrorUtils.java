package com.apps.morfiwifi.morfi_project_samane.utility;

import com.apps.morfiwifi.morfi_project_samane.models.ErrorModel;
import com.apps.morfiwifi.morfi_project_samane.network.FakeDataProvider;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;


public class ErrorUtils {
    public static final String OperationFaild = "OPE_FAILD_Check_THINGS";

    public static ErrorModel parseError(Response<?> response) {
        FakeDataProvider fakeDataProvider = new FakeDataProvider();

        //using Converter for convert error response body into Error Model
        Converter<ResponseBody, ErrorModel> converter = fakeDataProvider.getRetrofitClient().responseBodyConverter(ErrorModel.class, new Annotation[0]);

        ErrorModel errorModel;

        try {
            errorModel = converter.convert(response.errorBody());

        } catch (IOException e) {
            return new ErrorModel();
        }

        return errorModel;
    }
}
