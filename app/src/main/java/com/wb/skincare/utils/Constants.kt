package com.wb.skincare.utils

object Constants {


    const val TAG = "UserRepository"
    const val BASE_URL = "https://chamberin.com/"

    const val USER_INFO_ENDPOINT = "api/profile"
    const val USER_INFO_UPDATE_ENDPOINT = "api/profile/update"
    const val LOGIN_ENDPOINT = "api/login"
    //CLIENT
    const val CLIENT_ENDPOINT = "api/client"
    const val CLIENT_UPDATE_ENDPOINT = "api/client"
    //PROVIDER
    const val PROVIDER_ENDPOINT = "api/provider"
    const val UPDATE_PROVIDER_ENDPOINT = "api/provider/{id}"
    //BRANCH
    const val BRANCH_ENDPOINT = "api/branch"
    const val UPDATE_BRANCH_ENDPOINT = "api/branch/{id}"
    //PAYMENT
    const val PAYMENT_ENDPOINT = "api/payment-type"
    const val UPDATE_PAYMENT_ENDPOINT = "api/payment-type/{id}"
    //SERVICE
    const val SERVICE_ENDPOINT = "api/service"
    const val SERVICE_CATEGORY_ENDPOINT = "api/service-category"
    const val CATEGORY_WISE_SERVICE_ENDPOINT = "api/get-service-list-with-category/{id}"



    const val PREFS_TOKEN_FILE = "PREFS_TOKEN_FILE"
    const val USER_TOKEN = "USER_TOKEN"
}