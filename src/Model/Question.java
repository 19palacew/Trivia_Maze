package Model;

import java.io.Serializable;

/**
 * Question stores both a question and an answer.
 */
public class Question implements Serializable {
    /**
     * Question String.
     */
    private final String myQuestion;
    /**
     * Answer String.
     */
    private final String myAnswer;

    /**
     * Creates a new Question.
     *
     * @param theQuestion String of a question
     * @param theAnswer   String of an answer
     */
    public Question(final String theQuestion, final String theAnswer) {
        this.myQuestion = theQuestion;
        this.myAnswer = theAnswer;
    }

    /**
     * Returns the question.
     *
     * @return Returns a String of a Question
     */
    public String getQuestion() {
        return myQuestion;
    }

    /**
     * Returns the correct answer.
     *
     * @return Returns an enum (Answer) of the correct answer.
     */
    public String getAnswer() {
        return myAnswer;
    }
}
