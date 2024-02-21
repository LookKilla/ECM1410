package package;

public class Main {
    // Testing the Riders creation
    public static void main(String[] args) {
        // Create an instance of the src.Riders class
        Riders ridersSystem = new Riders();


        // Example: Creating a new rider
        int teamID = 1; // Replace with the actual src.team ID
        String riderName = "John Doe"; // Replace with the actual rider name
        int yearOfBirth = 1990; // Replace with the actual year of birth

        // Call createRider method to generate a new rider
        int newRiderID = ridersSystem.createRider(teamID, riderName, yearOfBirth);

        // Print the generated rider ID
        System.out.println("New Rider ID: " + newRiderID);
    }
}
