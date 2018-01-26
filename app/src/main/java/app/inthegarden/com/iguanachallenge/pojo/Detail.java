package app.inthegarden.com.iguanachallenge.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Felix on 25/01/2018.
 */

public class Detail extends Contact{
    @SerializedName("addresses")
    @Expose
    private List<Address> addresses = null;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "addresses=" + addresses +
                '}';
    }
}
