package Model;

public class Question {
    private final String theQuestion;
    private final char theAnswer;
    public Question(){
        this.theQuestion = genQuestion();
        this.theAnswer = genAnswer();
    }

    public String getTheQuestion() {
        return theQuestion;
    }

    public char getTheAnswer() {
        return theAnswer;
    }

    private String genQuestion(){
        return "SQLite Coming Soon";
    }

    private char genAnswer(){
        return 'X';
    }
}
