package Model;

public class Door {
    private final Question myQuestion;
    private boolean myLocked;
    private boolean myDoorDead;

    public Door(){
        this.myLocked = true;
        this.myDoorDead = false;
        this.myQuestion = Database.genQuestion();
    }

    /**
     * Returns if the door is unlocked or locked
     *
     * @return Returns a boolean of the state of the Model.Door
     */
    public boolean isLocked() {
        return myLocked;
    }

    /**
     * Returns if the door is dead
     *
     * @return Returns a boolean of the state of the Model.Door
     */
    public boolean isDead() {
        return myDoorDead;
    }


    public void unlock(final Answer theUserAnswer) {
        if (!myDoorDead && theUserAnswer == myQuestion.getAnswer()) {
            myLocked = false;
        }
        else{
            myDoorDead = true;
        }
    }

    public void unlockedFromOtherSide(){
        myLocked = false;
    }

    public String getQuestion(){
        return  myQuestion.getQuestion();
    }
}
