package idata2001.mappe2.patientregister;

/**
 * Allows for different modes of the dialogue.
 * NEW mode has no prefilled boxes.
 * EDIT contains existing information.
 * INFO is the same as edit but without the ability to edit text boxes.
 * TODO: Feedback from @Astyve: The mode enum should be in the class it belongs to, considering it only ever affects one class anyway.
 */
public enum Mode {
    NEW, EDIT, INFO
}

