package com.codecool.shop.model;


import java.lang.reflect.Field;

/**
 * The 'model' part of the program was made with the purpose of being able to seperate
 * the objects in the webshop with the aim of manipulating each of them.
 * <p>Here you can find the products, orders, suppliers and product categories.</p>
 * <p>This class is the base of all - because above every object has name, id and description which you can find here</p>
 * Also from here you can set (modify) and get (access) them.
 * <p>See for example here: {@link #setId(int id)} and here: {@link #getId()}</p>
 */
public class BaseModel {

    protected int id;
    protected String name;
    protected String description;

    /**
     * This is one of the constructors, which sets the.
     *
     * @param name - This will be the model's default name
     */
    public BaseModel(String name) {
        this.name = name;
    }


    /**
     * This is the second constructor, which sets the:
     *
     * @param name        - as the name of the BaseModel
     * @param description - what is a short summary about it
     */
    public BaseModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return the id of the instance.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of the instance.
     *
     * @param id - this will be the model's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name of the instance
     */
    public String getName() {
        return name;
    }

    /**
     * set the name of the instance.
     *
     * @param name - this will be the model's (new) name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description of the instance
     */
    public String getDescription() {
        return description;
    }

    /**
     * set the description of the instance
     *
     * @param description - This will be the model's description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the instance name and value in printable format
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(this);
                if (value != null) {
                    sb.append(field.getName() + ":" + value + ",");
                }
            } catch (IllegalAccessException e) {

            }
        }
        return sb.toString();
    }
}
