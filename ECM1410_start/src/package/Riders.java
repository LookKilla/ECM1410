package (Enter Package Name);

import java.util.Arrays;

public class Riders {
    private int[] riderIDs;
    private int lastUsedRiderID;

    public Riders() {
        // Initialize the array with a certain size (you can adjust the size based on your needs)
        riderIDs = new int[100];
        lastUsedRiderID = getLastUsedRiderID(); // Initialize lastUsedRiderID from your data source
    }

    private int generateRiderID() {
        // Increment the last used rider ID to generate a new unique ID
        int newRiderID = lastUsedRiderID + 1;

        // Update the last used rider ID for future use
        updateLastUsedRiderID(newRiderID);

        return newRiderID;
    }

    private void updateLastUsedRiderID(int newRiderID) {
        // Update lastUsedRiderID only if the newRiderID is greater than the current lastUsedRiderID
        if (newRiderID > lastUsedRiderID) {
            lastUsedRiderID = newRiderID;
        }
    }

    public int createRider(int teamID, String name, int yearOfBirth){
        // Validate input parameters
        // ...

        // Generate a unique rider ID
        int newRiderID = generateRiderID();

        // Add the rider ID to the array
        addRiderIDToArray(newRiderID);

        // Create a new Rider object with the provided details
        Rider newRider = new Rider(newRiderID, teamID, name, yearOfBirth);

        return newRiderID;
    }

    private void addRiderIDToArray(int newRiderID) {
        // Check if the array needs to be resized
        if (lastUsedRiderID >= riderIDs.length) {
            // Resize the array (you can use a more sophisticated resizing strategy)
            riderIDs = Arrays.copyOf(riderIDs, riderIDs.length * 2);
        }

        // Add the new rider ID to the array
        riderIDs[lastUsedRiderID] = newRiderID;

        // Increment the last used rider ID
        lastUsedRiderID++;
    }


    private int getLastUsedRiderID() {
        // Find the maximum value in the riderIDs array
        // Note: This assumes that the riderIDs array is not sparse (i.e., no gaps in the IDs)

        // Update the lastUsedRiderID
        lastUsedRiderID = Arrays.stream(riderIDs).max().orElse(0);

        return lastUsedRiderID;
    }


    public static class Rider {
        private int riderID;
        private int teamID;
        private String name;
        private int yearOfBirth;

        public Rider(int riderID, int teamID, String name, int yearOfBirth) {
            this.riderID = riderID;
            this.teamID = teamID;
            this.name = name;
            this.yearOfBirth = yearOfBirth;
        }

    }
}
