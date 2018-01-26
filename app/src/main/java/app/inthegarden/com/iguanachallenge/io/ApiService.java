package app.inthegarden.com.iguanachallenge.io;


import java.util.List;

import app.inthegarden.com.iguanachallenge.pojo.Contact;
import app.inthegarden.com.iguanachallenge.pojo.Detail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Felix on 24/01/2018.
 */

public interface ApiService {

    @GET("contacts")
    Call<List<Contact>> getContacts();

    @GET("contacts/{user_id}")
    Call<Detail> getDetail(
            @Path("user_id") String user_id
    );
}
