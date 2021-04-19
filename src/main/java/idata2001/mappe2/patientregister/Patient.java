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

    /**
     * Returns the first name of the patient.
     * @return String the patients first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the patient.
     * @param firstName String the new first name of the patient.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the patient.
     * @return String the last name of the patient.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the patient.
     * @param lastName String the new last name of the patient.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the social security number of the patient.
     * @return String the social security number of the patient.
     */
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    /**
     * Sets the social security number of the patient.
     * @param socialSecurityNumber String the new social security number of the patient.
     */
    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    /**
     * Returns the general practitioner the patient is under.
     * @return String the name of the general practitioner of the patient.
     */
    public String getGeneralPractitioner() {
        return generalPractitioner;
    }

    /**
     * Sets the new general practitioner of the patient.
     * @param generalPractitioner String the new name of the general practitioner.
     */
    public void setGeneralPractitioner(String generalPractitioner) {
        this.generalPractitioner = generalPractitioner;
    }

    /**
     * Returns the diagnosis of the patient.
     * @return String the current diagnosis of the patient.
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Sets the diagnosis of the patient
     * @param diagnosis String the new diagnosis of the patient.
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}


