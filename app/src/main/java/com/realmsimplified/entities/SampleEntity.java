package com.realmsimplified.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Krishna on 2/10/2018.
 */

@RealmClass
public class SampleEntity extends RealmObject{

    @PrimaryKey
    private String id;

    private String name;

    private int age;

    public SampleEntity() {
    }

    public SampleEntity(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
