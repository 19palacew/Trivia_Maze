package Model;

import java.io.Serializable;

/**
 * Door is obstacle for the player
 * it can opened by having the player answer a question.
 */
public class Door implements Serializable {

    /**
     * The Question and answer of the Door.
     */
    private final Question myQuestion;

    /**
     * Variable for if Door's question has been answered.
     */
    private boolean myLocked;

    /**
     * Variable for dead status of Door.
     */
    private boolean myDoorDead;

    /**
     * Creates a new Door.
     */
    public Door() {
        myLocked = true;
        myDoorDead = false;
        myQuestion = Database.genQuestion();
    }

    /**
     * Returns if the door is unlocked or locked.
     *
     * @return Returns a boolean of the state of the Model.Door
     */
    public boolean isLocked() {
        return myLocked;
    }

    /**
     * Returns if the door is dead.
     *
     * @return Returns a boolean of the state of the Model.Door
     */
    public boolean isDead() {
        return myDoorDead;
    }


    /**
     * Unlocks the door when the correct answer is given.
     *
     * @param theUserAnswer Response that the user gives
     */
    public void unlock(final String theUserAnswer) {
        // When the door is still unlockable and the correct answer is given, the door will unlock
        if (!myDoorDead && theUserAnswer.equalsIgnoreCase(myQuestion.getAnswer())) {
            myLocked = false;
        } else {
            myDoorDead = true;
        }
    }

    /**
     * Returns the question.
     *
     * @return Returns a String of the Question
     */
    public String getQuestion() {
        return myQuestion.getQuestion();
    }

    /**
     * Returns the answer.
     *
     * @return Returns a String of the Answer
     */
    public String getAnswer() {
        return myQuestion.getAnswer();
    }

    /**
     * Opens a dead door.
     */
    public void undead() {
        myDoorDead = false;
    }
}
