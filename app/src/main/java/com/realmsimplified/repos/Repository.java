package com.realmsimplified.repos;

import android.content.Context;
import android.util.Log;

import java.io.Closeable;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.exceptions.RealmException;

/**
 * Created by Krishna on 2/1/2016.
 */
public abstract class Repository<T> implements Closeable {

    protected Realm realm;
    protected Class aClass;

    public Repository(Context context, Class _aClass){
        realm = Realm.getInstance(context);
        aClass = _aClass;
    }

    public T findById(String id) throws RealmException {

        T t =  (T)realm.where(aClass).equalTo("id", id).findFirst();
        return t;
    }

    public List<T> findAll() throws RealmException {

        List<T> ts = (List<T>) realm.where(aClass).findAll();
        return ts;
    }

    public T save(T entity) throws RealmException {

        Log.d("Database saving", ""+entity.getClass());
        realm.beginTransaction();
        T t = (T) realm.copyToRealm((RealmObject) entity);
        realm.commitTransaction();
        return t;
    }

    public T saveOrUpdate(T entity) throws RealmException {

        Log.d(Repository.class.getSimpleName(), "saved or updated");
        realm.beginTransaction();
        T t = (T) realm.copyToRealmOrUpdate((RealmObject) entity);
        realm.commitTransaction();
        return t;
    }

    public T update(T entity) throws RealmException {

        realm.beginTransaction();
        T t = (T) realm.copyToRealmOrUpdate((RealmObject) entity);
        realm.commitTransaction();
        return t;
    }

    public boolean deleteById(String id) throws RealmException {
        realm.beginTransaction();
        realm.where(aClass).equalTo("id", id).findFirst().removeFromRealm();
        realm.commitTransaction();
        return true;
    }

    public boolean deleteAll() throws RealmException {
        realm.beginTransaction();
        realm.where(aClass).findAll().clear();
        realm.commitTransaction();
        return true;
    }

    @Override
    public void close() {
        try {
            realm.close();
        } catch (Exception ioException) {
            ioException.printStackTrace();
        }
    }
}

