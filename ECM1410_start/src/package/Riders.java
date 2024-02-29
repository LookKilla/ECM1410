package cycling;

public class Riders {

    private static int lastUsedRiderID; // Variable to keep track of the last used team ID

    private String Name;
    private int yearOfBirth;

    private int RiderId;


    public int getRiderId(){
        return RiderId;
    }

    public Riders(){

        CyclingPortalImpl Portal = new CyclingPortalImpl();
        lastUsedRiderID = Portal.lastUsedTeamID;

    }

    public int createRider(String name, int yearOfBirth) throws IllegalNameException, InvalidNameException {
        int riderID = generateRiderID();


        this.Name = name;
        this.yearOfBirth = yearOfBirth;
        this.RiderId = riderID;

        lastUsedRiderID++;
        return riderID;
    }

    private static int generateRiderID() {
        // Increment the last used team ID to generate a new unique ID
        return lastUsedRiderID + 1;
    }
}
