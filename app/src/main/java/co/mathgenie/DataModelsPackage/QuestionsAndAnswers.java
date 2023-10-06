package co.mathgenie.DataModelsPackage;

import java.util.Random;

public class QuestionsAndAnswers
{


    private String mQuestionText;
    private String[] mAnswers;
    private int mCorrectAnswerIndex;


    public QuestionsAndAnswers(String questionText, String[] answers, int correctAnswerIndex) {
        mQuestionText = questionText;
        mAnswers = answers;
        mCorrectAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return mQuestionText;
    }

    public String[] getAnswers() {
        return mAnswers;
    }

    public int getCorrectAnswerIndex() {
        return mCorrectAnswerIndex;
    }



    private static QuestionsAndAnswers generateRandomQuestion() {
        Random random = new Random();

        // Generate two random numbers between 1 and 10
        int num1 = random.nextInt(10) + 1;
        int num2 = random.nextInt(10) + 1;

        // Generate a random operator (+, -, *, /)
        String operator;
        int operatorIndex = random.nextInt(4);
        switch (operatorIndex) {
            case 0:
                operator = "+";
                break;
            case 1:
                operator = "-";
                break;
            case 2:
                operator = "*";
                break;
            default:
                operator = "/";
                break;
        }

        // Calculate the answer
        int answer;
        switch (operator) {
            case "+":
                answer = num1 + num2;
                break;
            case "-":
                answer = num1 - num2;
                break;
            case "*":
                answer = num1 * num2;
                break;
            default:
                answer = num1 / num2;
                break;
        }

        // Create an array of 4 answer choices
        int correctAnswerIndex = random.nextInt(4);
        String[] answers = new String[4];
        for (int i = 0; i < 4; i++) {
            if (i == correctAnswerIndex) {
                answers[i] = String.valueOf(answer);
            } else {
                int wrongAnswer = answer + random.nextInt(5) - 2;
                while (wrongAnswer == answer) {
                    wrongAnswer = answer + random.nextInt(5) - 2;
                }
                answers[i] = String.valueOf(wrongAnswer);
            }
        }

        // Construct and return the ArithmeticQuestion object
        String question = "What is the value of " + num1 + " " + operator + " " + num2 + " = ?";
        return new QuestionsAndAnswers(question, answers, correctAnswerIndex);
    }


}
