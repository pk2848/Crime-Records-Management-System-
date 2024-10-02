// src/Criminal.java

public class Criminal {
    private int id;
    private String crimeType;
    private String firNumber;
    private String name;

    // Constructor
    public Criminal(int id, String crimeType, String firNumber, String name) {
        this.id = id;
        this.crimeType = crimeType;
        this.firNumber = firNumber;
        this.name = name;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }

    public String getFirNumber() {
        return firNumber;
    }

    public void setFirNumber(String firNumber) {
        this.firNumber = firNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }   
}
