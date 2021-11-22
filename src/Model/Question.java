package Model;

public class Question {
    private final String myQuestion;
    private final String myAnswer;
    public Question(final String theQuestion,final String theAnswer){
        this.myQuestion = theQuestion;
        this.myAnswer = theAnswer;
    }

    /**
     * Returns the question
     * @return Returns a String of a Question
     */
    public String getQuestion() {
        return myQuestion;
    }

    /**
     * Returns the correct answer
     * @return Returns an enum (Answer) of the correct answer.
     */
    public String getAnswer() {
        return myAnswer;
    }
}
