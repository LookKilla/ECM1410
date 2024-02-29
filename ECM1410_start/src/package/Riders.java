package cycling;

public class Riders {

    private int lastUsedRiderID =1; // Variable to keep track of the last used team ID

    private String Name;
    private int yearOfBirth;

    private int RiderId;


    //getters
    public int getYearOfBirth(){return yearOfBirth;}
    public String getName(){return Name;}
    public int getRiderId(){return RiderId;}

    //setters
    public void setName(String Name){this.Name = Name;}
    public void setRiderId(int RiderId){this.RiderId =RiderId;}
    public void setYearOfBirth(int yearOfBirth){this.yearOfBirth = yearOfBirth;}


    public Riders(){

        CyclingPortalImpl Portal = new CyclingPortalImpl();

    }

    public int createRider(String name, int yearOfBirth) throws IllegalNameException, InvalidNameException {
        int riderID = generateRiderID();


        this.Name = name;
        this.yearOfBirth = yearOfBirth;
        this.RiderId = riderID;

        lastUsedRiderID++;
        return riderID;
    }

    private int generateRiderID() {
        // Increment the last used team ID to generate a new unique ID
        return lastUsedRiderID + 1;
    }
}
