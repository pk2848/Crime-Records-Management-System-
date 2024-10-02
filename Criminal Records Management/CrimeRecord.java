// src/CrimeRecord.java

public class CrimeRecord {
    private String crimeType;
    private String firNumber;
    private String criminalName;

    public CrimeRecord(String crimeType, String firNumber, String criminalName) {
        this.crimeType = crimeType;
        this.firNumber = firNumber;
        this.criminalName = criminalName;
    }

    // Getters and setters
    public String getCrimeType() {
        return crimeType;
    }

    public String getFirNumber() {
        return firNumber;
    }

    public String getCriminalName() {
        return criminalName;
    }
}
