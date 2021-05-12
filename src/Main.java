import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;

public class Main {
    public static volatile ArrayList<Passenger> passengers;
    public static volatile ArrayList<Floor> floors;
    public static volatile ArrayList<Lift> lifts;

    public static Lift getLessLoadedLift() {
        return new Lift(0, 0, 0, new Build());
    }

    public static void main(String[] args) throws Throwable {
        passengers = new ArrayList<Passenger>();
        floors = new ArrayList<Floor>();
        lifts = new ArrayList<Lift>();

        int floorsNumber, liftsNumber;
        System.out.println("Введите количество этажей и лифтов в здании:");
        Scanner scanner = new Scanner(System.in);
        floorsNumber = scanner.nextInt();
        liftsNumber = scanner.nextInt();

        Random gen = new Random();
        Build build = new Build();

        for (int i = 0; i < floorsNumber; ++i) {
            Floor floor = new Floor(i + 1);
            floors.add(floor);
        }

        for (int i = 0; i < liftsNumber; ++i) {
            Lift lift = new Lift(gen.nextInt(5) + 4, 0, 1, build);
            lifts.add(lift);
        }

        build.setFloors(floors);
        build.setLifts(lifts);
        build.setHeight(floorsNumber);
        build.setLiftsCount(liftsNumber);

        // Simulate process:
        while (true) {


            Thread.sleep(1000);
        }
    }
}
