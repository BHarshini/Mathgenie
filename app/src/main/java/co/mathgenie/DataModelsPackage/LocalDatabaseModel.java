package co.mathgenie.DataModelsPackage;

public class LocalDatabaseModel
{

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public long getDateTimeInMills() {
        return dateTimeInMills;
    }

    public void setDateTimeInMills(long dateTimeInMills) {
        this.dateTimeInMills = dateTimeInMills;
    }


    public LocalDatabaseModel(String selectedAnswer, String correctAnswer, String question, long dateTimeInMills,int rating) {
        this.selectedAnswer = selectedAnswer;
        this.correctAnswer = correctAnswer;
        this.question = question;
        this.dateTimeInMills = dateTimeInMills;
        this.rating  =rating;
    }

    String selectedAnswer;
    String correctAnswer;
    String question;
    long dateTimeInMills;
    int rating;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
