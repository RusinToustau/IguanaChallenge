package app.inthegarden.com.iguanachallenge.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Felix on 25/01/2018.
 */

public class Address {

    @SerializedName("work")
    @Expose
    private String work;
    @SerializedName("home")
    @Expose
    private String home;

    public String getWork() {
        return work;
    }

    public String getHome() {
        return home;
    }

}