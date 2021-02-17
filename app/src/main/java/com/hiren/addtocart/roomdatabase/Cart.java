package com.hiren.addtocart.roomdatabase;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MyCart")
public class Cart {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "imageid")
    public String imageid;

    @ColumnInfo(name = "price")
    public String price;


    @ColumnInfo(name = "spec")
    public String spec;

    @ColumnInfo(name = "quantity")
    public int quantity;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quan) {
        this.quantity = quan;
    }


}
