package Model;

public class Question {
    private final String myQuestion;
    private final Answer myAnswer;
    public Question(final String theQuestion,final Answer theAnswer){
        this.myQuestion = theQuestion;
        this.myAnswer = theAnswer;
    }

    public String getQuestion() {
        return myQuestion;
    }

    public Answer getAnswer() {
        return myAnswer;
    }
}
