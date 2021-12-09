package Testing;

import Model.Database;
import Model.Door;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DoorTest {

    @Test
    void testIsLocked() {
        Database.connectToDatabase();
        Door testDoor = new Door();
        assertTrue(testDoor.isLocked(), "Door is unlocked");
    }

    @Test
    void testUnlock() {
        Database.connectToDatabase();
        Door testDoor = new Door();
        testDoor.unlock(testDoor.getAnswer());
        assertFalse(testDoor.isLocked(), "Door is locked");
    }

    @Test
    void testIsDeadUnanswered() {
        Database.connectToDatabase();
        Door testDoor = new Door();
        assertFalse(testDoor.isDead(), "Door is dead");
    }

    @Test
    void testIsDeadAnsweredWrong() {
        Database.connectToDatabase();
        Door testDoor = new Door();
        testDoor.unlock("");
        assertTrue(testDoor.isDead(), "Door is not dead");
    }

    @Test
    void doorGeneratesQuestionObject() {
        Database.connectToDatabase();
        Door testDoor = new Door();
        assertNotNull(testDoor.getQuestion());
    }

    @Test
    void doorGeneratesQuestion() {
        Database.connectToDatabase();
        Door testDoor = new Door();
        assertNotEquals(testDoor.getQuestion(), "");
    }

    @Test
    void doorGeneratesAnswer() {
        Database.connectToDatabase();
        Door testDoor = new Door();
        assertNotEquals(testDoor.getAnswer(), "");
    }
}
