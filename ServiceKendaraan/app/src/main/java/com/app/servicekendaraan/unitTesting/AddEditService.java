package com.app.servicekendaraan.unitTesting;

import com.app.servicekendaraan.register.RegisterActivity;
import com.app.servicekendaraan.api.ApiClient;
import com.app.servicekendaraan.api.ApiInterface;
import com.app.servicekendaraan.models.User;
import com.app.servicekendaraan.models.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditService {

    //    TODO 6: silahkan salin code ProfilService

    /*public void survey(final com.app.servicekendaraan.unitTesting.AddEditView view, RegisterActivity registerActivity, final
    AddEditCallback callback) {
        UserResponse apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> surveyDAOCall =
                apiService.(apiService);
        surveyDAOCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call,
                                   Response<UserResponse> response) {

                if (response.body().getMessage().equalsIgnoreCase("berhasil mendaftar"
                )) {
                    callback.onSuccess(true,
                            response.body().getMessage().equalsIgnoreCase());
                } else {
                    callback.onError();
                    view.showSurveyError(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                view.showErrorSurveyResponse(t.getMessage());
                callback.onError();
            }
        });
    }

    public Boolean getValid(final com.app.servicekendaraan.unitTesting.AddEditView view, User user) {
        final Boolean[] bool = new Boolean[1];
        survey(view, user, new AddEditCallback() {
            @Override
            public void onSuccess(boolean value, User user) {
                bool[0] = true;
            }

            @Override
            public void onError() {
                bool[0] = false;
            }
        });
        return bool[0];
    }*/
}