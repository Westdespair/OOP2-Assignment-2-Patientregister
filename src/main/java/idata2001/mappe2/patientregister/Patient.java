package idata2001.mappe2.patientregister;


public class Patient {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private String diagnosis;
    private String generalPractitioner;

    /**
     * Constructor for instances of the Patient class.
     * Patients have firstnames, lastnames, and an SSN to identify them by.
     */
    public Patient (String initialFirstName, String initialLastName, String initialSocialSecurityNumber) {
        this.firstName = initialFirstName;
        this.lastName = initialLastName;
        this.socialSecurityNumber = initialSocialSecurityNumber;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getGeneralPractitioner() {
        return generalPractitioner;
    }

    public void setGeneralPractitioner(String generalPractitioner) {
        this.generalPractitioner = generalPractitioner;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}


