package com.example.martiandemo.data.api

import android.content.Context
import com.example.martiandemo.BuildConfig
import com.example.martiandemo.data.db.models.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ApiService {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("posts/{postId}/comments")
    fun getComments(@Path("postId") postId: Int): Call<List<Comment>>

    @POST("comments")
    fun createComment(@Body body: Comment) : Call<Comment>

    @GET("albums")
    fun getAlbums(): Call<List<Album>>

    @GET("albums/{albumId}/photos")
    fun getPhotos(@Path("albumId") albumId: Int): Call<List<Photo>>

    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @POST("posts")
    fun createPost(@Body body: Post) : Call<Post>

    @GET("photos")
    fun getPhotos(): Call<List<Photo>>

    @GET("todos")
    fun getTodos(@Query("userId") userId: Int): Call<List<Todo>>

    companion object {
        operator fun invoke(context: Context) : ApiService {
            val interceptor = Interceptor {
                val request = it.request()
                    .newBuilder()
                    .header("X-Auth", BuildConfig.ApiKey)
                    .build()
                return@Interceptor it.proceed(request)
            }

            val httpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .client(httpClient).baseUrl(BuildConfig.ApiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}