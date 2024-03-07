package cycling;

public class Checkpoint implements java.io.Serializable{
    private CheckpointType Type;
    private Double avGradient;
    private Double Location;
    private Double Length;
    private int CheckpID;
    

    public int createCheckpoint(Double location, CheckpointType type, Double averageGradient, Double length){

        this.Location = location;
        this.Length = length;
        this.Type = type;
        this.avGradient = averageGradient;

        int id = generateID(avGradient, location, length);
        this.CheckpID = id;

        return id;
    }

    private int generateID(Double a, Double b, Double c){
        double temp = a + b + c;

        // Concatenate integers into a string
        String combinedAttributes = Double.toString(temp);

        // Hash the concatenated string
        int id = combinedAttributes.hashCode();

        // Ensure the ID is non-negative
        if (id < 0) {
            id = id * -1;
        }

        return id;
    }


    public CheckpointType getType() {
        return Type;
    }

    public Double getAvGradient() {
        return avGradient;
    }

    public Double getLocation() {
        return Location;
    }

    public Double getLength() {
        return Length;
    }

    public int getCheckID() {
        return CheckpID;
    }
}
