package app.inthegarden.com.iguanachallenge.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Felix on 24/01/2018.
 */

public class Contact {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("phones")
    @Expose
    private List<Phone> phones = null;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("photo")
    @Expose
    private String photo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "userId='" + userId + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phones=" + phones +
                ", thumb='" + thumb + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
