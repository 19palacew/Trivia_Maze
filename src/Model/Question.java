package Model;

public class Question {
    private final String myQuestion;
    private final char myAnswer;
    public Question(){
        this.myQuestion = genQuestion();
        this.myAnswer = genAnswer();
    }

    public String getQuestion() {
        return myQuestion;
    }

    public char getAnswer() {
        return myAnswer;
    }

    private String genQuestion(){
        return "SQLite Coming Soon";
    }

    private char genAnswer(){
        return 'X';
    }
}
