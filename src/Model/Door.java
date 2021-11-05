package Model;

public class Door {
    private final Question theQuestion;
    private boolean theLocked;

    public Door(){
        this.theLocked = true;
        this.theQuestion = new Question();
    }

    /**
     * Returns if the door is unlocked or locked
     *
     * @return Returns a boolean of the state of the Model.Door
     */
    public boolean isLocked() {
        return theLocked;
    }


    public void unlock(final char myUserAnswer) {
        if (myUserAnswer == theQuestion.getTheAnswer()) {
            theLocked = false;
        }
    }

    public String getQuestion(){
        return  theQuestion.getTheQuestion();
    }
}
