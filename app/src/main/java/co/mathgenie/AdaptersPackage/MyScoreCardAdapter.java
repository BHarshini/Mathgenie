package co.mathgenie.AdaptersPackage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.mathgenie.DataModelsPackage.LocalDatabaseModel;
import co.mathgenie.R;

public class MyScoreCardAdapter extends RecyclerView.Adapter<MyScoreCardAdapter.MyViewHolder>
{
    public MyScoreCardAdapter(ArrayList<ArrayList<LocalDatabaseModel>> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    private ArrayList<ArrayList<LocalDatabaseModel>> dataList;
    private Context context;


    @NonNull
    @Override
    public MyScoreCardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_score_adapter_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyScoreCardAdapter.MyViewHolder holder, int position) {

        ArrayList<LocalDatabaseModel> itemData = dataList.get(position);
        holder.bindData(itemData);


    }

    @Override
    public int getItemCount() {

        return dataList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private RatingBar ratingBar1,ratingBar2,ratingBar3,ratingBar4,ratingBar5;

        private TextView question1, selected1 ,answer1;
        private TextView question2, selected2,answer2, stars2;
        private TextView question3, selected3,answer3, stars3;
        private TextView question4, selected4,answer4, stars4;
        private TextView question5, selected5,answer5, stars5;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            question1 = itemView.findViewById(R.id.question1);
            question2 = itemView.findViewById(R.id.question2);
            question3 = itemView.findViewById(R.id.question3);
            question4 = itemView.findViewById(R.id.question4);
            question5 = itemView.findViewById(R.id.question5);


            selected1 = itemView.findViewById(R.id.answer1);
            selected2 = itemView.findViewById(R.id.answer2);
            selected3 = itemView.findViewById(R.id.answer3);
            selected4 = itemView.findViewById(R.id.answer4);
            selected5 = itemView.findViewById(R.id.answer5);


            answer1 = itemView.findViewById(R.id.correct_answer1);
            answer2 = itemView.findViewById(R.id.correct_answer2);
            answer3 = itemView.findViewById(R.id.correct_answer3);
            answer4 = itemView.findViewById(R.id.correct_answer4);
            answer5 = itemView.findViewById(R.id.correct_answer5);

            ratingBar1 = itemView.findViewById(R.id.stars1);
            ratingBar2 = itemView.findViewById(R.id.stars2);
            ratingBar3 = itemView.findViewById(R.id.stars3);
            ratingBar4 = itemView.findViewById(R.id.stars4);
            ratingBar5 = itemView.findViewById(R.id.stars5);



        }

        public void bindData(ArrayList<LocalDatabaseModel> itemData) {
            // Bind available data



            for (int i = 0; i < itemData.size(); i++) {
                LocalDatabaseModel data = itemData.get(i);

                Log.i("TAG","Question "+data.getQuestion());
                Log.i("TAG","SAnswer "+data.getSelectedAnswer()+"CAnser"+data.getCorrectAnswer());
                Log.i("TAG","Rating"+data.getRating());

                switch (i) {
                    case 0:
                        question1.setText(data.getQuestion());
                        selected1.setText("Selected Answer: " + data.getSelectedAnswer());
                        answer1.setText("Correct Answer: " + data.getCorrectAnswer());
                        ratingBar1.setRating(data.getRating());
                        break;
                    case 1:
                        question2.setText(data.getQuestion());
                        selected2.setText("Selected Answer: " + data.getSelectedAnswer());
                        answer2.setText("Correct Answer: " + data.getCorrectAnswer());
                        ratingBar2.setRating(data.getRating());
                        break;
                    case 2:
                        question3.setText(data.getQuestion());
                        selected3.setText("Selected Answer: " + data.getSelectedAnswer());
                        answer3.setText("Correct Answer: " + data.getCorrectAnswer());
                        ratingBar3.setRating(data.getRating());
                        break;
                    case 3:
                        question4.setText(data.getQuestion());
                        selected4.setText("Selected Answer: " + data.getSelectedAnswer());
                        answer4.setText("Correct Answer: " + data.getCorrectAnswer());
                        ratingBar4.setRating(data.getRating());
                        break;
                    case 4:
                        question5.setText(data.getQuestion());
                        selected5.setText("Selected Answer: " + data.getSelectedAnswer());
                        answer5.setText("Correct Answer: " + data.getCorrectAnswer());
                        ratingBar5.setRating(data.getRating());

                        break;
                    default:
                        break;
                }
            }
            // Hide views for remaining rows
            for (int i = itemData.size(); i < 5; i++) {
                switch (i) {
                    case 0:
                        question1.setVisibility(View.GONE);
                        selected1.setVisibility(View.GONE);
                        answer1.setVisibility(View.GONE);
                        ratingBar1.setVisibility(View.GONE);
                        break;
                    case 1:
                        question2.setVisibility(View.GONE);
                        selected2.setVisibility(View.GONE);
                        answer2.setVisibility(View.GONE);
                        ratingBar2.setVisibility(View.GONE);
                        break;
                    case 2:
                        question3.setVisibility(View.GONE);
                        selected3.setVisibility(View.GONE);
                        answer3.setVisibility(View.GONE);
                        ratingBar3.setVisibility(View.GONE);
                        break;
                    case 3:
                        question4.setVisibility(View.GONE);
                        selected4.setVisibility(View.GONE);
                        answer4.setVisibility(View.GONE);
                        ratingBar4.setVisibility(View.GONE);
                        break;
                    case 4:
                        question5.setVisibility(View.GONE);
                        selected5.setVisibility(View.GONE);
                        answer5.setVisibility(View.GONE);
                        ratingBar5.setVisibility(View.GONE);
                        break;
                }
            }
        }


    }
}
