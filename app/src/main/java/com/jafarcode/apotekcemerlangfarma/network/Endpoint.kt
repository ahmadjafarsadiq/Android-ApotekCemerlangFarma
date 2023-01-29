package com.jafarcode.apotekcemerlangfarma.network


import com.jafarcode.apotekcemerlangfarma.model.response.Wrapper
import com.jafarcode.apotekcemerlangfarma.model.response.checkout.CheckoutResponse
import com.jafarcode.apotekcemerlangfarma.model.response.home.HomeResponse
import com.jafarcode.apotekcemerlangfarma.model.response.login.LoginResponse
import com.jafarcode.apotekcemerlangfarma.model.response.transaction.TransactionResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface Endpoint {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("email") email:String,
            @Field("password") password:String) : Observable<Wrapper<LoginResponse>>

    @FormUrlEncoded
    @POST("register")
    fun register(@Field("name") name:String,
                 @Field("email") email:String,
                 @Field("password") password:String,
                 @Field("password_confirmation") password_confirmation:String,
                 @Field("address") address:String,
                 @Field("city") city:String,
                 @Field("houseNumber") houseNumber:String,
                 @Field("phoneNumber") phoneNumber:String) : Observable<Wrapper<LoginResponse>>

    @Multipart
    @POST("user/photo")
    fun registerPhoto(@Part profileImage:MultipartBody.Part)  : Observable<Wrapper<Any>>

    @GET("drug")
    fun home() : Observable<Wrapper<HomeResponse>>

    @FormUrlEncoded
    @POST("checkout")
    fun checkout(@Field("drug_id") drug_id:String,
                 @Field("user_id") user_id:String,
                 @Field("quantity") quantity:String,
                 @Field("total") total:String,
                 @Field("status") status:String) : Observable<Wrapper<CheckoutResponse>>

    @GET("transaction")
    fun transaction() : Observable<Wrapper<TransactionResponse>>

    @FormUrlEncoded
    @POST("transaction/{id}")
    fun transactionUpdate(@Path(value = "id") userId:String,
              @Field("status") status:String) : Observable<Wrapper<Any>>

}