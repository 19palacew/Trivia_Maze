package Model;

public class Door {
    private final String theQuestion;
    // Keeping it multiple choice for now, free response seems painful
    private final char theAnswer;
    private boolean theLocked;

    public Door(final char myAnswer, final String myQuestion) {
        theLocked = true;
        theAnswer = myAnswer;
        theQuestion = myQuestion;
    }

    /**
     * Returns if the door is unlocked or locked
     *
     * @return Returns a boolean of the state of the Model.Door
     */
    public boolean isLocked() {
        return theLocked;
    }

    /**
     * Returns the trivia question
     *
     * @return Returns a String of a question
     */
    public String getTheQuestion() {
        return theQuestion;
    }

    public void unlock(final char myUserAnswer) {
        if (myUserAnswer == theAnswer) {
            theLocked = false;
        }
    }
}
