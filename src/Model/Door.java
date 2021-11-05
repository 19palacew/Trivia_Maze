package Model;

public class Door {
    private final Question myQuestion;
    private boolean myLocked;

    public Door(){
        this.myLocked = true;
        this.myQuestion = new Question();
    }

    /**
     * Returns if the door is unlocked or locked
     *
     * @return Returns a boolean of the state of the Model.Door
     */
    public boolean isLocked() {
        return myLocked;
    }


    public void unlock(final char theUserAnswer) {
        if (theUserAnswer == myQuestion.getAnswer()) {
            myLocked = false;
        }
    }

    public void unlockedFromOtherSide(){
        myLocked = false;
    }

    public String getQuestion(){
        return  myQuestion.getQuestion();
    }
}
