package Model;

import org.sqlite.SQLiteDataSource;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

/**
 * Database class handles connection to the QuestionAnswer table of
 * the Table database and loading the contents into ArrayLists that
 * can be accessed via getters
 */
public class Database implements Serializable {
    /**
     * ArrayList field holding the questions
     */
    private static final ArrayList<String> questionBank = new ArrayList<>();
    /**
     * ArrayList field holding the answers
     */
    private static final ArrayList<String> answerBank = new ArrayList<>();
    /**
     * Counter int field to iterate through the lists
     */
    private static int questionIndex = 0;

    /**
     * Sets up connection to database and loads table values into ArrayLists
     */
    public static void connectToDatabase() {
        //Create DataSource object
        SQLiteDataSource ds = new SQLiteDataSource();
        //Set DataSource URL
        ds.setUrl("jdbc:sqlite:Table.db");
        //Set where we want to get our questions and answers from
        String query = "SELECT * FROM QuestionAnswer";
        //Set up the connection
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String question = rs.getString("Question");
                String answer = rs.getString("Answer");
                questionBank.add(question);
                answerBank.add(answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Returns a Question object
     *
     * @return Returns a Question object
     */
    public static Question genQuestion() {
        return new Question(getQuestion(), getAnswer());
    }

    /**
     * Getter for question
     *
     * @return question at index counter field is set to
     */
    public static String getQuestion() {
        randomizeQuestion();
        return questionBank.get(questionIndex);
    }

    /**
     * Getter for answer
     *
     * @return answer at index counter field is set to
     */
    public static String getAnswer() {
        return answerBank.get(questionIndex);
    }

    /**
     * Increment counter to get next question
     */
    public static void randomizeQuestion() {
        Random rand = new Random();
        questionIndex = rand.nextInt(questionBank.size());
    }
}