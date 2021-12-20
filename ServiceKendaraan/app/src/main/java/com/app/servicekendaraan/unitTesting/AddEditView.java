package com.app.servicekendaraan.unitTesting;

public interface AddEditView {

    //    TODO 5: silahkan salin code ProfilView

    String getNama_lengkap();
    void showNamaDestinasiError(String message);

    String getUmur();
    void showUmurError(String message);

    String getNo_Telp();
    void showNo_TelpError(String message);

    String getEmail();
    void showEmailError(String message);

    void startMainAddEdit();

    void showSurveyError(String message);
    void showErrorSurveyResponse(String message);

}
