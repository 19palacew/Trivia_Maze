package Testing;

import Model.Database;
import Model.Question;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {

    @Test
    void testQuestionOrder() {
        Database.connectToDatabase();
        Question testQuestionObject = Database.genQuestion();
        int currentQuestionIndex = Database.getQuestionIndex();
        String questionAtIndexFromObject = testQuestionObject.getQuestion();
        String questionAtIndexFromArrayList = Database.getQuestionAtIndex(currentQuestionIndex);
        assertEquals(questionAtIndexFromArrayList, questionAtIndexFromObject);
    }
    @Test
    void testAnswerOrder() {
        Database.connectToDatabase();
        Question testQuestionObject = Database.genQuestion();
        int currentAnswerIndex = Database.getQuestionIndex();
        String answerAtIndexFromObject = testQuestionObject.getQuestion();
        String answerAtIndexFromArrayList = Database.getQuestionAtIndex(currentAnswerIndex);
        assertEquals(answerAtIndexFromArrayList, answerAtIndexFromObject);
    }
}
