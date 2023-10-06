package co.mathgenie.AdaptersPackage;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.mathgenie.DataModelsPackage.LocalDatabaseModel;
import co.mathgenie.DataModelsPackage.QuestionsAndAnswers;
import co.mathgenie.HelpersPackage.TinyDb;
import co.mathgenie.InterfacePackage.NextButtonClickListener;
import co.mathgenie.R;

public class CustomQuestionAnswerAdapter extends RecyclerView.Adapter<CustomQuestionAnswerAdapter.viewHolder>
{


    private List<QuestionsAndAnswers> questionsAndAnswersList;
    private Context context;
    private NextButtonClickListener onNextButtonClickListener;
    TinyDb tinyDb;
    private String saveSelectedAnswer ;

    private int currentItemIndex = 0;

    int numCorrectAnswers = 0;




    public CustomQuestionAnswerAdapter(List<QuestionsAndAnswers> questionsAndAnswersList,
                                       Context context,NextButtonClickListener onNextButtonClickListener)
    {
        this.questionsAndAnswersList = questionsAndAnswersList;
        this.context = context;
        this.onNextButtonClickListener = onNextButtonClickListener;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_question_answer_layout, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        tinyDb = new TinyDb(context);

        QuestionsAndAnswers questionsAndAnswers = questionsAndAnswersList.get(position);
        holder.question_textview.setText(questionsAndAnswers.getQuestionText());
        holder.answerOne.setText(questionsAndAnswers.getAnswers()[0]);
        holder.answerTwo.setText(questionsAndAnswers.getAnswers()[1]);
        holder.answerThree.setText(questionsAndAnswers.getAnswers()[2]);
        holder.answerFour.setText(questionsAndAnswers.getAnswers()[3]);


        int correctAnswerIndex = questionsAndAnswers.getCorrectAnswerIndex();

        String correctAnswer = questionsAndAnswers.getAnswers()[correctAnswerIndex];

        ArrayList<LocalDatabaseModel> similarItems = new ArrayList<>();








        holder.answerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentItemIndex++;
                String selectedAnswer = holder.answerOne.getText().toString();

                saveSelectedAnswer = selectedAnswer;

                if (selectedAnswer.equals(correctAnswer)) {
                    // User selected the correct answer
                    holder.answerOne.setBackgroundResource(R.color.teal_200);
                    numCorrectAnswers++;

                } else {
                    // User selected the wrong answer
                    holder.answerOne.setBackgroundResource(R.color.red_color);

                }

                holder.answerTwo.setClickable(false);
                holder.answerThree.setClickable(false);
                holder.answerFour.setClickable(false);

            }
        });


        holder.answerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentItemIndex++;

                String selectedAnswer = holder.answerTwo.getText().toString();
                saveSelectedAnswer = selectedAnswer;


                if (selectedAnswer.equals(correctAnswer)) {
                    // User selected the correct answer
                    holder.answerTwo.setBackgroundResource(R.color.teal_200);
                    numCorrectAnswers++;
                } else {
                    // User selected the wrong answer
                    holder.answerTwo.setBackgroundResource(R.color.red_color);

                }

                holder.answerOne.setClickable(false);
                holder.answerThree.setClickable(false);
                holder.answerFour.setClickable(false);
            }
        });


        holder.answerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentItemIndex++;

                String selectedAnswer = holder.answerThree.getText().toString();
                saveSelectedAnswer = selectedAnswer;

                if (selectedAnswer.equals(correctAnswer)) {
                    // User selected the correct answer
                    holder.answerThree.setBackgroundResource(R.color.teal_200);
                    numCorrectAnswers++;
                } else {
                    // User selected the wrong answer
                    holder.answerThree.setBackgroundResource(R.color.red_color);
                }

                holder.answerTwo.setClickable(false);
                holder.answerOne.setClickable(false);
                holder.answerFour.setClickable(false);
            }
        });


        holder.answerFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentItemIndex++;

                String selectedAnswer = holder.answerFour.getText().toString();
                saveSelectedAnswer = selectedAnswer;

                if (selectedAnswer.equals(correctAnswer)) {
                    // User selected the correct answer
                    holder.answerFour.setBackgroundResource(R.color.teal_200);
                    numCorrectAnswers++;
                } else {
                    // User selected the wrong answer
                    holder.answerFour.setBackgroundResource(R.color.red_color);
                }

                holder.answerTwo.setClickable(false);
                holder.answerThree.setClickable(false);
                holder.answerOne.setClickable(false);

            }
        });


        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentItemIndex >= questionsAndAnswersList.size()) {
                    // all items in the list have been completed
                    holder.next.setVisibility(View.GONE);
                    Log.i("TAG","List Completed");
                    Toast.makeText(context,"Quiz Completed!!",Toast.LENGTH_LONG).show();

                    holder.gotoHome.setVisibility(View.VISIBLE);
                    holder.next.setVisibility(View.GONE);
                    if (numCorrectAnswers >= 3) {
                        holder.ratingBar.setRating(3);
                    } else  {
                        holder.ratingBar.setRating(2);
                    }

                }
                if (onNextButtonClickListener != null) {
                    onNextButtonClickListener.onNextButtonClick(holder.getAdapterPosition() + 1);

                    onNextButtonClickListener.SelectedAnswer(saveSelectedAnswer , correctAnswer,holder.getAdapterPosition(),numCorrectAnswers);
                }
            }
        });

        holder.gotoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onNextButtonClickListener.onBackToHomeButtonPressed();
            }
        });



        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*String Question1 = tinyDb.getString("Question1");
                String Question2 = tinyDb.getString("Question2");
                String Question3 = tinyDb.getString("Question3");
                String Question4 = tinyDb.getString("Question4");

                Log.i("TAG",Question1);
                Log.i("TAG",Question2);
                Log.i("TAG",Question3);
                Log.i("TAG",Question4);
                */




            }
        });


    }

    @Override
    public int getItemCount() {


        return Math.min(questionsAndAnswersList.size(), 5);

    }


    public class viewHolder extends RecyclerView.ViewHolder
    {
        private TextView question_textview;
        private TextView answerOne,answerTwo,answerThree,answerFour,answerFive;

        private CardView next ,submit,gotoHome;

        private RatingBar ratingBar;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            question_textview = itemView.findViewById(R.id.question_textview);
            answerOne = itemView.findViewById(R.id.answerOne);
            answerTwo = itemView.findViewById(R.id.answerTwo);
            answerThree = itemView.findViewById(R.id.answerThree);
            answerFour = itemView.findViewById(R.id.answerFour);
            next = itemView.findViewById(R.id.customNextCardView);
            submit = itemView.findViewById(R.id.customSubmitCardView);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            gotoHome = itemView.findViewById(R.id.gotoHomeCardView);



        }
    }
}
