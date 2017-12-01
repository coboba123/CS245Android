package cpp.cs245final;

import android.app.Application;

///IGNORE THIS FILE MIGHT USE IT LATER
public  class  Global extends Application {
    private Boolean _notification=false;

   ////global=((Global)getApplicationContext()); // ignore this im using it.

    public Boolean get_notification() {
        return _notification;
    }

    public void set_notification(Boolean _notification) {
        this._notification = _notification;
    }

//    public void startMusic() {
//        mediaPlayer.start();
//    }
//
//    public void pauseMusic() {
//        mediaPlayer.pause();
//    }
}