import java.util.ArrayList;
import java.util.List;

public class Floor {
    private int number;
    private ArrayList<Integer> waitingPassengers;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<Integer> getWaitingPassengers() {
        return waitingPassengers;
    }

    public void setWaitingPassengers(ArrayList<Integer> waitingPassengers) {
        this.waitingPassengers = waitingPassengers;
    }

    Floor(int floorNumber) {
        number = floorNumber;
    }

    public void callLift() {

    }
}
