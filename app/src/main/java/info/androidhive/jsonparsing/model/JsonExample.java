package info.androidhive.jsonparsing.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonExample {

    @SerializedName("checkout")
    @Expose
    private List<Checkout> checkout = null;
    @SerializedName("emailid")
    @Expose
    private String emailid;

    public List<Checkout> getCheckout() {
        return checkout;
    }

    public void setCheckout(List<Checkout> checkout) {
        this.checkout = checkout;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

}