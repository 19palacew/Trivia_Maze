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


    /**
     * Unlocks the door when the correct answer is given
     * @param theUserAnswer Response that the user gives
     */
    public void unlock(final Answer theUserAnswer) {
        // When the door is still unlockable and the correct answer is given, the door will unlock
        if (!myDoorDead && theUserAnswer == myQuestion.getAnswer()) {
            myLocked = false;
        }
        else{
            myDoorDead = true;
        }
    }

    /**
     * When entering a room, the door that the player used to enter is unlocked.
     * (Allows us to keep doors from different rooms unlinked)
     */
    public void unlockedFromOtherSide(){
        myLocked = false;
    }

    /**
     * Returns the question
     * @return Returns a String of the Question
     */
    public String getQuestion(){
        return  myQuestion.getQuestion();
    }
}
