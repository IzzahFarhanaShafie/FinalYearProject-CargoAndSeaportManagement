package com.example.cargotracker.Holder;

import android.os.Bundle;

public class UnreadMessageHolder {

    private static UnreadMessageHolder instance;
    private Bundle bundle;

    public static synchronized UnreadMessageHolder getInstance(){
        UnreadMessageHolder unreadMessageHolder = null;
        synchronized (UnreadMessageHolder.class){
            if(instance == null){
                instance = new UnreadMessageHolder();
                unreadMessageHolder = instance;
            }
            return unreadMessageHolder;
        }
    }

    private UnreadMessageHolder(){ bundle = new Bundle(); }

    private void setBundle(Bundle bundle){
        this.bundle = bundle;
    }

    public Bundle getBundle(){
        return this.bundle;
    }

    public int getUnreadMessageByDialogId(String id){
        return this.bundle.getInt(id);
    }
}
