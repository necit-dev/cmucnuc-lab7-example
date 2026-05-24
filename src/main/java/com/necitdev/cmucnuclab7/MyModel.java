package com.necitdev.cmucnuclab7;

public class MyModel {
    private MyState state = MyState.IDLE;

    private int secondsLeft = 0;
    public int getSecondsLeft() {return secondsLeft;}
    public void setSecondsLeft(int secondsLeft) {this.secondsLeft = secondsLeft;}

    public MyState getState() {
        return state;
    }

    public MyState handleEvent(MyEvent e) {
        switch (state) {
            case IDLE -> {
                if (e == MyEvent.POWER_ON_CLICKED) state = MyState.READY;
            }
            case READY -> {
                if (e == MyEvent.START_CLICKED) state = MyState.HEATING;
                if (e == MyEvent.POWER_OFF_CLICKED) microwaveOff();
            }
            case HEATING -> {
                if (e == MyEvent.STOP_BUTTON_CLICKED) state = MyState.PAUSED;
                if (e == MyEvent.POWER_OFF_CLICKED) microwaveOff();
                if (e == MyEvent.TIMES_UP) state = MyState.FINISHED;
            }
            case PAUSED -> {
                if (e == MyEvent.START_CLICKED) state = MyState.HEATING;
                if (e == MyEvent.STOP_BUTTON_CLICKED) state = MyState.READY;
                if (e == MyEvent.POWER_OFF_CLICKED) microwaveOff();
            }
            case FINISHED -> {
                if (e == MyEvent.START_CLICKED) state = MyState.HEATING;
                if (e == MyEvent.POWER_OFF_CLICKED) microwaveOff();
                if (e == MyEvent.STOP_BUTTON_CLICKED) state = MyState.READY;
            }
            default -> {

            }
        }
        System.out.println(state);
        return state;
    }
    private void microwaveOff() {
        state = MyState.IDLE;
        secondsLeft = 0;
    }
}
