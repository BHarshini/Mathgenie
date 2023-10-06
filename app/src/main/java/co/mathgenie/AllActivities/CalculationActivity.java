package co.mathgenie.AllActivities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import co.mathgenie.AdaptersPackage.CustomQuestionAnswerAdapter;
import co.mathgenie.BaseActivity;
import co.mathgenie.DataModelsPackage.LocalDatabaseModel;
import co.mathgenie.DataModelsPackage.QuestionsAndAnswers;
import co.mathgenie.HelpersPackage.TinyDb;
import co.mathgenie.InterfacePackage.NextButtonClickListener;
import co.mathgenie.LocalDatabasePackage.MyDatabaseHelper;
import co.mathgenie.R;
import co.mathgenie.databinding.ActivityAdditionBinding;

public class CalculationActivity extends BaseActivity<ActivityAdditionBinding> implements NextButtonClickListener {

    private ActivityAdditionBinding binding;

    private List<QuestionsAndAnswers> questionsAndAnswersList;
    TinyDb tinyDb;

    private MyDatabaseHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        questionsAndAnswersList = new ArrayList<>();

        tinyDb = new TinyDb(this);

        tinyDb.remove("currentTime");

        dbHelper = new MyDatabaseHelper(this);

        Intent i = getIntent();
        String operatorIs = i.getStringExtra("operatorIs");

        if(operatorIs.contains("+"))
        {
            binding.titleTextview.setText(R.string.addition);
            binding.rootLayout.setBackgroundResource(R.drawable.gradient_blue_backgroud);

        }else if(operatorIs.contains("-"))
        {
            binding.titleTextview.setText(R.string.subtraction);
            binding.rootLayout.setBackgroundResource(R.drawable.gradient_purple);

        }else if(operatorIs.contains("*"))
        {
            binding.titleTextview.setText(R.string.multiplication);
            binding.rootLayout.setBackgroundResource(R.drawable.gradient_love_couple);

        }else if(operatorIs.contains("/"))
        {
            binding.titleTextview.setText(R.string.division);
            binding.rootLayout.setBackgroundResource(R.drawable.gradient_dusk);

        }


        generateSomeRandomNumberBasedOnSelectorOperator(operatorIs);
        CustomQuestionAnswerAdapter adapter = new CustomQuestionAnswerAdapter(questionsAndAnswersList,this,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.additionRecyclerView);
        binding.additionRecyclerView.setLayoutManager(layoutManager);
        binding.additionRecyclerView.setAdapter(adapter);



    }


    @Override
    protected ActivityAdditionBinding getViewBinding() {

        binding = ActivityAdditionBinding.inflate(getLayoutInflater());
        return binding;
    }



    private void generateSomeRandomNumberBasedOnSelectorOperator(String selectedOperator)
    {
        for (int i = 0; i < 5; i++) {
            // Generate random operands for the arithmetic operation
            int operand1 = (int) (Math.random() * 10) + 1;
            int operand2 = (int) (Math.random() * 10) + 1;

            // Calculate the answer
            int answer = 0;
            switch (selectedOperator) {
                case "+":
                    answer = operand1 + operand2;
                    break;
                case "-":
                    answer = operand1 - operand2;
                    break;
                case "*":
                    answer = operand1 * operand2;
                    break;
                case "/":
                    answer = operand1 / operand2;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + selectedOperator);
            }

            // Create an array of 4 answer choices, with the correct answer randomly placed
            String[] answerChoices = new String[4];
            int correctAnswerIndex = (int) (Math.random() * 4);
            for (int j = 0; j < 4; j++) {
                if (j == correctAnswerIndex) {
                    answerChoices[j] = Integer.toString(answer);
                } else {
                    int wrongAnswer = answer;
                    while (wrongAnswer == answer) {
                        wrongAnswer = (int) (Math.random() * 20) + 1;
                    }
                    answerChoices[j] = Integer.toString(wrongAnswer);
                }
            }

            // Create a new arithmetic question and add it to the ArrayList
            String question = "What is the value of " + operand1 + " " + selectedOperator + " " + operand2 + " = ?";
            QuestionsAndAnswers qa = new QuestionsAndAnswers(question, answerChoices, correctAnswerIndex);
            questionsAndAnswersList.add(qa);
        }

    }


    @Override
    public void onNextButtonClick(int position) {

        binding.additionRecyclerView.smoothScrollToPosition(position);

    }

    @Override
    public void SelectedAnswer(String SelectedAnswer,String correctAnswer, int position,int rating) {
        NextButtonClickListener.super.SelectedAnswer(SelectedAnswer,correctAnswer, position,rating);

        LocalDatabaseModel localDatabaseModel;
        Calendar calendar = Calendar.getInstance();
        long dateTimeInMillis = calendar.getTimeInMillis();
        tinyDb.putLong("currentTime",dateTimeInMillis);

        long finalDateAndTime = tinyDb.getLong("currentTime");
        String selectedQaIs = questionsAndAnswersList.get(position).getQuestionText();

        String finalAnswer = selectedQaIs+"Answer:"+SelectedAnswer +correctAnswer;

        Log.i("TG",finalAnswer);

        localDatabaseModel = new LocalDatabaseModel(SelectedAnswer,correctAnswer,selectedQaIs,finalDateAndTime,rating);

        binding.scoreTextview.setText("Score:"+rating);

/*
        localDatabaseModel.setDateTimeInMills(finalDateAndTime);
        localDatabaseModel.setQuestion(selectedQaIs);
        localDatabaseModel.setSelectedAnswer(SelectedAnswer);
        localDatabaseModel.setCorrectAnswer(correctAnswer);

*/

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);

        dbHelper.insertData(this,localDatabaseModel);


    }


    @Override
    public void onBackToHomeButtonPressed() {
        NextButtonClickListener.super.onBackToHomeButtonPressed();

        Intent intent = new Intent(this, DashBoardActivity.class);
        startActivity(intent);
    }
}

