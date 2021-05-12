import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;

public class Main {
    public static volatile ArrayList<Passenger> passengers;
    public static volatile ArrayList<Floor> floors;
    public static volatile ArrayList<Lift> lifts;
    public static volatile Build build;

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
        build = new Build();

        for (int i = 0; i < floorsNumber; ++i) {
            Floor floor = new Floor(i + 1);
            floors.add(floor);
        }

        for (int i = 0; i < liftsNumber; ++i) {
            Lift lift = new Lift(gen.nextInt(5) + 4, 0, 1, build);
            lifts.add(lift);
            System.out.println(lift.getLiftId());
        }

        build.setFloors(floors);
        build.setLifts(lifts);
        build.setHeight(floorsNumber);
        build.setLiftsCount(liftsNumber);

        // Simulate process:

        int passengerId = 0;

        for (int i = 0; i < build.getLiftsCount(); ++i) {
            build.getLifts().get(i).start();
        }
//        System.out.println(build.getLifts().get(0).getLiftId());
        build.getLifts().get(0).addEvent(new Event(0, 5, 9));
        build.getLifts().get(0).addEvent(new Event(1, 6, 8));

//        while (true) {
//            int incomingPassengers = gen.nextInt(3) + 1;
//
//
//            Thread.sleep(1000);
//        }
    }
}
