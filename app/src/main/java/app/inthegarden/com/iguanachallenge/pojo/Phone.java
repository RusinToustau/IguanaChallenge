package app.inthegarden.com.iguanachallenge.pojo;

/**
 * Created by Felix on 24/01/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phone {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("number")
    @Expose
    private String number;

    public String getType() {
        return type;
    }


    public String getNumber() {
        return number;
    }


    @Override
    public String toString() {
        return "Phone{" +
                "type='" + type + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
