package Testing;

import Model.Database;
import Model.Door;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DoorTest {

    @Test
    void testIsLockedUnanswered() {
        Database.connectToDatabase();
        Door testDoor = new Door();
        assertTrue(testDoor.isLocked(), "Door is unlocked");
    }

    @Test
    void testIsLockedAnswered() {
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
}
