import java.util.Random;

public class FerryTerminalSimulation {
    private double arrivalInterval;
    private double passengerInterval;
    private boolean endStop;
    private int maxCapacity;
    private int maxPeopleOnDock;
    private Random random;

    public FerryTerminalSimulation(double arrivalInterval, double passengerInterval, boolean endStop, int maxCapacity, int maxPeopleOnDock) {
        this.arrivalInterval = arrivalInterval;
        this.passengerInterval = passengerInterval;
        this.endStop = endStop;
        this.maxCapacity = maxCapacity;
        this.maxPeopleOnDock = maxPeopleOnDock;
        random = new Random();
    }

    public void runSimulation() {
        double currentTime = 0;
        int peopleOnDock = 0;
        int totalPeopleCount = 0;
        int totalPeopleTime = 0;
        int ferryCount = 0;

        while (currentTime < 24) {
            double nextArrival = getNextArrivalTime();
            double nextPassenger = getNextPassengerTime();
            if (nextArrival < nextPassenger) {
                currentTime += nextArrival;
                ferryCount++;

                if (peopleOnDock > 0) {
                    int boardedPeople = Math.min(maxCapacity, peopleOnDock);
                    peopleOnDock -= boardedPeople;
                    totalPeopleCount += boardedPeople;
                    totalPeopleTime += boardedPeople * (int) currentTime;
                }
                if (peopleOnDock <= maxPeopleOnDock) {
                    peopleOnDock += generateRandomPeopleCount();
                }
            } else {
                currentTime+=nextPassenger;
                if (peopleOnDock>0){
                    int boardedPeople = Math.min(maxCapacity,peopleOnDock);
                    peopleOnDock-=boardedPeople;
                    totalPeopleCount+=boardedPeople;
                    totalPeopleTime+=boardedPeople*(int) currentTime;
                }
                if (peopleOnDock<=maxPeopleOnDock){
                    peopleOnDock+=generateRandomPeopleCount();
                }
            }

        }

        double averageTimePerPerson = (double) totalPeopleTime/totalPeopleCount;
        double sufficientInterval=(double) totalPeopleCount/(ferryCount*maxCapacity);

        System.out.println("Середній час перебування людини на зупинці: " + averageTimePerPerson);
        System.out.println("Достатній інтервал часу між приходом катерів, щоб на зупинці знаходилося не більше " + maxPeopleOnDock + " людей одночасно: " + sufficientInterval);

    }

    private double getNextArrivalTime() {
        return exponentialDistribution(arrivalInterval);
    }

    private double getNextPassengerTime() {
        return exponentialDistribution(passengerInterval);
    }

    private double exponentialDistribution(double mean) {
        return -mean * Math.log(1 - random.nextDouble());
    }

    private int generateRandomPeopleCount() {
        return random.nextInt(maxPeopleOnDock + 1);
    }


}
