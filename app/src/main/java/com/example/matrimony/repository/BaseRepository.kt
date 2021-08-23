package com.bfl.superapp.repository


import com.bfl.superapp.api.NetworkService
import com.example.matrimony.BuildConfig
import com.example.matrimony.model.ErrorRespone
import okhttp3.ConnectionSpec
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit


open class BaseRepository {

    private var service: NetworkService? = null
    var retrofit: Retrofit? = null

    fun getNetworkService(): NetworkService {
        if (service == null) {
            service = getService()
        }
        return service!!
    }

    fun getExoNetworkService(baseURL:String,userName:String,password:String) : NetworkService {
        if (service == null) {
            service = getExoAPIService(baseURL,userName,password)
        }
        return service!!
    }

    private fun getService(): NetworkService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient: OkHttpClient =
            if (BuildConfig.DEBUG) {
                OkHttpClient.Builder()
                    .connectionSpecs(
                        Arrays.asList(
                            ConnectionSpec.MODERN_TLS,
                            ConnectionSpec.CLEARTEXT
                        )
                    )
                    .readTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build()

            } else {
                OkHttpClient.Builder()
                    .connectionSpecs(
                        Arrays.asList(
                            ConnectionSpec.MODERN_TLS,
                            ConnectionSpec.CLEARTEXT
                        )
                    )
                    .readTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .build()


            }
        // var gson=GsonBuilder().registerTypeAdapterFactory(ArrayAdapterFactory()).create()
        // Retrofit handling
        retrofit = Retrofit.Builder()
        //    .baseUrl(BuildConfig.BASE_URL)
            .baseUrl("http://anterpat.com/api/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit!!.create(NetworkService::class.java)
    }

    private fun getExoAPIService(baseURL:String,userName:String,password:String): NetworkService {


        val okHttpClient: OkHttpClient =
            OkHttpClient.Builder()
                .connectionSpecs(
                    Arrays.asList(
                        ConnectionSpec.MODERN_TLS,
                        ConnectionSpec.CLEARTEXT
                    )
                )
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(BasicAuthInterceptor(userName, password))
                .build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit!!.create(NetworkService::class.java)
    }

    protected fun getError(e: Throwable): String {
        var error: ErrorRespone? = ErrorRespone()
        try {
            val body = (e as HttpException).response()?.errorBody()
            val errorConverter =
                retrofit?.responseBodyConverter<ErrorRespone>(
                    ErrorRespone::class.java,
                    arrayOfNulls<Annotation>(0)
                )
            error = errorConverter?.convert(body)
            return error?.error?.errorStr?.get(0)!!
        } catch (exp: Exception) {
//            exp.printStackTrace()
            return ""
        }


    }

}

class BasicAuthInterceptor(username: String, password: String): Interceptor {
    private var credentials: String = Credentials.basic(username, password)

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", credentials).build()

        return chain.proceed(request)
    }
}