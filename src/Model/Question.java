package Model;

public class Question {
    private final String myQuestion;
    private final Answer myAnswer;
    public Question(){
        this.myQuestion = genQuestion();
        this.myAnswer = genAnswer();
    }

    public String getQuestion() {
        return myQuestion;
    }

    public Answer getAnswer() {
        return myAnswer;
    }

    private String genQuestion(){
        return "SQLite Coming Soon";
    }

    private Answer genAnswer(){
        return null;
    }
}
