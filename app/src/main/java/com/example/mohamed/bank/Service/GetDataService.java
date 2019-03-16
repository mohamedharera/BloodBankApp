package com.example.mohamed.bank.Service;

import com.example.mohamed.bank.Models.Call_us.CallUs_Example;
import com.example.mohamed.bank.Models.Call_us.Contact;
import com.example.mohamed.bank.Models.Call_us.ContactUsExample;
import com.example.mohamed.bank.Models.Donation_recyclerView_items.DonationAskResponse;
import com.example.mohamed.bank.Models.Example;
import com.example.mohamed.bank.Models.ExampleCity;
import com.example.mohamed.bank.Models.Favourites.Favourite_Example;
import com.example.mohamed.bank.Models.Login;
import com.example.mohamed.bank.Models.NotificationCount.NotificationCountExample;
import com.example.mohamed.bank.Models.NotificationSettings.NotificationSetting_Example;
import com.example.mohamed.bank.Models.Notifications.NotificationsExample;
import com.example.mohamed.bank.Models.Posts.Category_Example;
import com.example.mohamed.bank.Models.PostsDetails_Example;
import com.example.mohamed.bank.Models.Report.Report;
import com.example.mohamed.bank.Models.Report.ReportExample;
import com.example.mohamed.bank.Models.Resetpassword.Reset;
import com.example.mohamed.bank.Models.Resetpassword.ResetExample;
import com.example.mohamed.bank.Models.UserDonationRequest;
import com.example.mohamed.bank.Models.aboutApp.AboutApp_Response;
import com.example.mohamed.bank.Models.bloodType.BloodTypeResponse;
import com.example.mohamed.bank.Models.create_request.DonCreate_Example;
import com.example.mohamed.bank.Models.donationDetails.DonationDetails_Response;
import com.example.mohamed.bank.Models.login.LoginResponse;
import com.example.mohamed.bank.Models.register.RegisterResponse;
import com.example.mohamed.bank.Models.register.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Mohamed on 10/15/2018.
 */

public interface GetDataService {

    @POST("login")
    Call<LoginResponse>login(@Body Login login);
    @POST("reset-password")
    Call<ResetExample>resetPassword(@Body Reset reset);

    @POST("register")
    Call<RegisterResponse>createUser(@Body User user);

    @GET("governorates")
    Call <Example> getListGovern();

    @GET("cities")
    Call <ExampleCity> getListCities(@Query("governorate_id") int id);
    @GET("cities")
    Call <ExampleCity> getCities();

    @GET("posts")
    Call <Category_Example> getCategory(@Query("api_token") String api);

    @GET("blood-types")
    Call <BloodTypeResponse> getBloodTypes();

    @GET("donation-requests")
    Call<DonationAskResponse> getRequest(@Query("api_token")String api , @Query("page") int page);
    @GET("donation-requests")
    Call<DonationAskResponse> getCity(@Query("api_token")String api ,@Query("city_id") int city_id);
    @GET("donation-requests")
    Call<DonationAskResponse> getBlood(@Query("api_token")String api ,@Query("blood_type") String blood_type);


    @POST("donation-request/create")
    Call<DonCreate_Example>postData(@Body UserDonationRequest userDonationRequest);

    @GET("post")
    Call<PostsDetails_Example> getPost_details(@Query("api_token")String api, @Query("post_id")int post_id);

    @GET("donation-request")
    Call<DonationDetails_Response> getDonationdetails(@Query("api_token")String api, @Query("donation_id") int donation_id);

    @GET("settings")
    Call<CallUs_Example> getLinks(@Query("api_token")String api);
    @GET("settings")
    Call<AboutApp_Response> getAppInfo(@Query("api_token")String api);


    @GET("my-favourites")
    Call<Favourite_Example>getFavourite(@Query("api_token")String api);

    @FormUrlEncoded
    @POST("post-toggle-favourite")
    Call<Category_Example>addFavourite(@Field("post_id") int id, @Field("api_token")String api);

    @GET("notifications")
    Call<NotificationsExample>getNotifications(@Query("api_token")String api);

    @GET("notifications-count")
    Call<NotificationCountExample>getNotificationCount(@Query("api_token")String api);

    @POST("report")
    Call<ReportExample>sendReport(@Body Report report);

    @POST("contact")
    Call<ContactUsExample>contact(@Body Contact contact);

    @FormUrlEncoded
    @POST("notifications-settings")
    Call<NotificationSetting_Example>selectNotification(@Field("api_token") String api);

    @FormUrlEncoded
    @POST("notifications-settings")
    Call<NotificationSetting_Example>selectNotificationBlood(@Field("api_token") String api);
}
