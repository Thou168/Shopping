package com.bokor.shopping.App.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.*;
public class Item implements Parcelable {

    private int id;
    private String title;
    private int category;
    private int status;
    private String condition;
    private String discount_type;
    private String discount;
    private int user;
    private String front_image_path;
    private String right_image_path;
    private String left_image_path;
    private String back_image_path;
    private String created;
    private int created_by;
    private String modified;
    private String modified_by = null;
    private String approved_date;
    private int approved_by;
    private String rejected_date = null;
    private String rejected_by = null;
    private String rejected_comments;
    private int year;
    private int modeling;
    private String description;
    private String cost;
    private String post_type;
    private String vin_code;
    private String machine_code;
    private int type;
    private String contact_phone;
    private String contact_email;
    private String contact_address;
    private String color;
    private String extra_image1 = null;
    private String extra_image2 = null;
    private String post_code;
    private String post_sub_title;
    private String used_eta1;
    private String used_eta2;
    private String used_eta3;
    private String used_eta4;
    private String used_machine1;
    private String used_machine2;
    private String used_machine3;
    private String used_machine4;
    private String used_other1;

    protected Item(Parcel in) {
        id = in.readInt();
        title = in.readString();
        category = in.readInt();
        status = in.readInt();
        condition = in.readString();
        discount_type = in.readString();
        discount = in.readString();
        user = in.readInt();
        front_image_path = in.readString();
        right_image_path = in.readString();
        left_image_path = in.readString();
        back_image_path = in.readString();
        created = in.readString();
        created_by = in.readInt();
        modified = in.readString();
        modified_by = in.readString();
        approved_date = in.readString();
        approved_by = in.readInt();
        rejected_date = in.readString();
        rejected_by = in.readString();
        rejected_comments = in.readString();
        year = in.readInt();
        modeling = in.readInt();
        description = in.readString();
        cost = in.readString();
        post_type = in.readString();
        vin_code = in.readString();
        machine_code = in.readString();
        type = in.readInt();
        contact_phone = in.readString();
        contact_email = in.readString();
        contact_address = in.readString();
        color = in.readString();
        extra_image1 = in.readString();
        extra_image2 = in.readString();
        post_code = in.readString();
        post_sub_title = in.readString();
        used_eta1 = in.readString();
        used_eta2 = in.readString();
        used_eta3 = in.readString();
        used_eta4 = in.readString();
        used_machine1 = in.readString();
        used_machine2 = in.readString();
        used_machine3 = in.readString();
        used_machine4 = in.readString();
        used_other1 = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCategory() {
        return category;
    }

    public int getStatus() {
        return status;
    }

    public String getCondition() {
        return condition;
    }

    public String getDiscount_type() {
        return discount_type;
    }

    public String getDiscount() {
        return discount;
    }

    public int getUser() {
        return user;
    }

    public String getFront_image_path() {
        return front_image_path;
    }

    public String getRight_image_path() {
        return right_image_path;
    }

    public String getLeft_image_path() {
        return left_image_path;
    }

    public String getBack_image_path() {
        return back_image_path;
    }

    public String getCreated() {
        return created;
    }

    public int getCreated_by() {
        return created_by;
    }

    public String getModified() {
        return modified;
    }

    public String getModified_by() {
        return modified_by;
    }

    public String getApproved_date() {
        return approved_date;
    }

    public int getApproved_by() {
        return approved_by;
    }

    public String getRejected_date() {
        return rejected_date;
    }

    public String getRejected_by() {
        return rejected_by;
    }

    public String getRejected_comments() {
        return rejected_comments;
    }

    public int getYear() {
        return year;
    }

    public int getModeling() {
        return modeling;
    }

    public String getDescription() {
        return description;
    }

    public String getCost() {
        return cost;
    }

    public String getPost_type() {
        return post_type;
    }

    public String getVin_code() {
        return vin_code;
    }

    public String getMachine_code() {
        return machine_code;
    }

    public int getType() {
        return type;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public String getContact_email() {
        return contact_email;
    }

    public String getContact_address() {
        return contact_address;
    }

    public String getColor() {
        return color;
    }

    public String getExtra_image1() {
        return extra_image1;
    }

    public String getExtra_image2() {
        return extra_image2;
    }

    public String getPost_code() {
        return post_code;
    }

    public String getPost_sub_title() {
        return post_sub_title;
    }

    public String getUsed_eta1() {
        return used_eta1;
    }

    public String getUsed_eta2() {
        return used_eta2;
    }

    public String getUsed_eta3() {
        return used_eta3;
    }

    public String getUsed_eta4() {
        return used_eta4;
    }

    public String getUsed_machine1() {
        return used_machine1;
    }

    public String getUsed_machine2() {
        return used_machine2;
    }

    public String getUsed_machine3() {
        return used_machine3;
    }

    public String getUsed_machine4() {
        return used_machine4;
    }

    public String getUsed_other1() {
        return used_other1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeInt(category);
        dest.writeInt(status);
        dest.writeString(condition);
        dest.writeString(discount_type);
        dest.writeString(discount);
        dest.writeInt(user);
        dest.writeString(front_image_path);
        dest.writeString(right_image_path);
        dest.writeString(left_image_path);
        dest.writeString(back_image_path);
        dest.writeString(created);
        dest.writeInt(created_by);
        dest.writeString(modified);
        dest.writeString(modified_by);
        dest.writeString(approved_date);
        dest.writeInt(approved_by);
        dest.writeString(rejected_date);
        dest.writeString(rejected_by);
        dest.writeString(rejected_comments);
        dest.writeInt(year);
        dest.writeInt(modeling);
        dest.writeString(description);
        dest.writeString(cost);
        dest.writeString(post_type);
        dest.writeString(vin_code);
        dest.writeString(machine_code);
        dest.writeInt(type);
        dest.writeString(contact_phone);
        dest.writeString(contact_email);
        dest.writeString(contact_address);
        dest.writeString(color);
        dest.writeString(extra_image1);
        dest.writeString(extra_image2);
        dest.writeString(post_code);
        dest.writeString(post_sub_title);
        dest.writeString(used_eta1);
        dest.writeString(used_eta2);
        dest.writeString(used_eta3);
        dest.writeString(used_eta4);
        dest.writeString(used_machine1);
        dest.writeString(used_machine2);
        dest.writeString(used_machine3);
        dest.writeString(used_machine4);
        dest.writeString(used_other1);
    }
}
