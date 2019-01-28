package com.hazyaz.ctup.menu_item;

public class users {

    public String name;
    public String image;
    public String status;

    public users()
    {

    }

    public users(String name,String image,String status)
    {
        this.name = name;
        this.image =image;
        this.status = status;
    }



    public String getImage() {
        return image;
    }


    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setStatus(String status) {
        this.status = status;
    }
}