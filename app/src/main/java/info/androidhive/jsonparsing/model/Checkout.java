package info.androidhive.jsonparsing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Checkout {

    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("product_quantity")
    @Expose
    private Integer productQuantity;
    @SerializedName("product_name")
    @Expose
    private String productName;


    public  Checkout(){

    }




    public Checkout(String productId, String productPrice, Integer product_quantity,String productName) {
        this.productId = productId;
        this.productPrice = productPrice;
        this.productQuantity = product_quantity;
        this.productName = productName;
    }
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}