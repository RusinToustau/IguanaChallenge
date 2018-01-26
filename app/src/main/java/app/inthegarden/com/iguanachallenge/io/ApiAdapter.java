package app.inthegarden.com.iguanachallenge.io;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Felix on 24/01/2018.
 */

public class ApiAdapter {
    public static ApiService API_SERVICE;
    public static String BASE_URL = "https://private-d0cc1-iguanafixtest.apiary-mock.com/";


    public static ApiService getApiService(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        String baseURL = BASE_URL;

        if ( API_SERVICE == null ){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            API_SERVICE = retrofit.create(ApiService.class);
        }

        return API_SERVICE;
    }
}
