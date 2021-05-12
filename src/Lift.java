import javax.crypto.ExemptionMechanism;
import java.util.*;

public class Lift extends Thread {
    private int passengersCapacity;
    private int curPassengers;
    private int curFloor;
    private int destFloor;

    public int getDestFloor() {
        return destFloor;
    }

    public void setDestFloor(int destFloor) {
        this.destFloor = destFloor;
    }

    public void setPassengers(Set<Integer> passengers) {
        this.passengers = passengers;
    }

    private Set<Integer> passengers;
    private Queue<Event> events;
    private Build build;

    public Set<Integer> getPassengers() {
        return passengers;
    }

    public Event getCurEvent() {
        return curEvent;
    }

    public void setCurEvent(Event curEvent) {
        this.curEvent = curEvent;
    }

    private boolean mFinish;
    private curDirrection direction;
    private Event curEvent;

    private enum curDirrection {
        UP,
        STOP,
        DOWN,
    }

    Lift(int pasCapacity, int curPas, int curFloor1, Build b) {
        passengersCapacity = pasCapacity;
        curPassengers = curPas;
        curFloor = curFloor1;
        build = b;
        direction = curDirrection.STOP;
    }

    public Build getBuild() {
        return build;
    }

    public void setBuild(Build build) {
        this.build = build;
    }

    public boolean ismFinish() {
        return mFinish;
    }

    public void setmFinish(boolean mFinish) {
        this.mFinish = mFinish;
    }

    public curDirrection getDirection() {
        return direction;
    }

    public void setDirection(curDirrection direction) {
        this.direction = direction;
    }
/*
    private void sortEventsByFloor() {
        Collections.sort(events, new Comparator<Event>() {
            public int compare(Event e1, Event e2) {
                return e1.getFloor() - e2.getFloor();
            }
        });
    }
    */

    public void finish()        //Инициирует завершение потока
    {
        mFinish = true;
    }

    @Override
    public void run() {
        while (true) {
            try {
                try {
                    if (!mFinish) {
                        // here are actual code:
                        if (events.isEmpty()) {
                            direction = curDirrection.STOP;
                        } else {
                            if (direction == curDirrection.STOP) {
                                // Now lift has no current events
                                // We start execute new event
                                Event event = getEventFromQueue();
                                curEvent = event;
                                destFloor = event.getFromFloor();
                                if (destFloor > curFloor) {
                                    direction = curDirrection.UP;
                                } else {
                                    direction = curDirrection.DOWN;
                                }
                            } else {
                                // Now lift executes current event
                                if (direction == curDirrection.UP) {
                                    curFloor++;
                                } else if (direction == curDirrection.DOWN) {
                                    curFloor--;
                                }
                                if (curFloor == destFloor) {
                                    if (passengers.contains(curEvent.getPassengerId())) {
                                        // it means that now we put passenger into his destination floor
                                        passengers.remove(curEvent.getPassengerId());
                                        direction = curDirrection.STOP;
                                        curEvent = null;
                                    } else {
                                        // we just arrive to take the passenger who calls the lift
                                        destFloor = curEvent.getDestFloor();
                                        passengers.add(curEvent.getPassengerId());
                                        if (destFloor > curFloor) {
                                            direction = curDirrection.UP;
                                        } else {
                                            direction = curDirrection.DOWN;
                                        }
                                    }
                                }
                            }
                        }
                        Thread.sleep(1000);
                    } else {
                        return;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (Exception ignored) {
                System.out.println("Thread has stopped!");
                return;
            }
        }
    }

    public Event getEventFromQueue() {
        return events.poll();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public int getCurFloor() {
        return curFloor;
    }

    public void setCurFloor(int curFloor) {
        this.curFloor = curFloor;
    }

    public int getCurPassengers() {
        return curPassengers;
    }

    public void setCurPassengers(int curPassengers) {
        this.curPassengers = curPassengers;
    }

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    public void setPassengersCapacity(int passengersCapacity) {
        this.passengersCapacity = passengersCapacity;
    }

    public Queue<Event> getEvents() {
        return events;
    }

    public void setEvents(Queue<Event> events) {
        this.events = events;
    }
}
