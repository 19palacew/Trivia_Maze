package Model;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Database class handles connection to the QuestionAnswer table of
 * the Table database and loading the contents into ArrayLists that
 * can be accessed via getters
 */
public class Database {
    /**
     * ArrayList holding the questions
     */
    private static final ArrayList<String> questionBank = new ArrayList<>();
    /**
     * ArrayList holding the answers
     */
    private static final ArrayList<String> answerBank = new ArrayList<>();
    /**
     * Counter to iterate through the lists
     */
    private static int counter = 0;

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
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String question = rs.getString("Question");
                String answer = rs.getString("Answer");

                questionBank.add(question);
                answerBank.add(answer);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }

    public static Question genQuestion(){
        return new Question(getQuestion(), getAnswer());
    }

    /**
     * Getter for question
     * @return question at index counter field is set to
     */
    public static String getQuestion() {
        return questionBank.get(counter);
    }

    /**
     * Getter for answer
     * @return answer at index counter field is set to
     */
    public static Answer getAnswer() {
        Answer localAnswer = null;
        char localDBAns = answerBank.get(counter).charAt(0);
        switch (localDBAns){
            case 'A' -> localAnswer = Answer.A;
            case 'B' -> localAnswer = Answer.B;
            case 'C' -> localAnswer = Answer.C;
            case 'D' -> localAnswer = Answer.D;
        }
        return localAnswer;
    }

    /**
     * Increment counter to get next question
     */
    public static void incrementCounter() {
        counter++;
    }
}