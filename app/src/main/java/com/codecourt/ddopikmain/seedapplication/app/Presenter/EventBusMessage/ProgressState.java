package com.codecourt.ddopikmain.seedapplication.app.Presenter.EventBusMessage;

/**
 * Created by ddopi on 6/27/2017.
 */

public class ProgressState {

    private boolean progressState;
    private boolean setRefreash;
    public boolean isSetRefreash() {
        return setRefreash;
    }

    public void setSetRefreash(boolean setRefreash) {
        this.setRefreash = setRefreash;
    }



    public ProgressState()
    {

    }
    public ProgressState(boolean progressState)
    {
        this.progressState=progressState;
    }

    public boolean isProgressState() {
        return progressState;
    }
    public void setProgressState(boolean progressState) {
        this.progressState = progressState;
    }


}
