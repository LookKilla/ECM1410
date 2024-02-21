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


// For testing the Team.java
public class Main {
    public static void main(String[] args) {
        // Example usage of the Teams class
        Team teams = new Team();

        // Get user input or use predefined values
        String teamName = "TeamA"; // Replace with actual team name
        String teamDescription = "A great cycling team"; // Replace with actual team description

        try {
            // Create a new team using the Teams class
            int newTeamID = teams.createTeam(teamName, teamDescription);

            // Print the newly created team ID
            System.out.println("New Team ID: " + newTeamID);
        } catch (Exception e) {
            // Handle exceptions if necessary
            e.printStackTrace();
        }
    }
}
