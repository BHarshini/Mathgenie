package co.mathgenie.AllActivities;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

import co.mathgenie.AdaptersPackage.MyScoreCardAdapter;
import co.mathgenie.BaseActivity;
import co.mathgenie.DataModelsPackage.LocalDatabaseModel;
import co.mathgenie.LocalDatabasePackage.MyDatabaseHelper;
import co.mathgenie.databinding.ActivityScoreCardBinding;

public class ScoreCardActivity extends BaseActivity<ActivityScoreCardBinding>
{
    private ActivityScoreCardBinding binding;

    private MyDatabaseHelper dbHelper;
    ArrayList<LocalDatabaseModel> questionAnswersList ;
    MyScoreCardAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new MyDatabaseHelper(this);


        ArrayList<ArrayList<LocalDatabaseModel>> dataList = dbHelper.readDataFromDB();


        adapter1 = new MyScoreCardAdapter(dataList,this);

        binding.scoreCardReview.setAdapter(adapter1);
        binding.scoreCardReview.setLayoutManager(new LinearLayoutManager(ScoreCardActivity.this));




    }

    @Override
    protected ActivityScoreCardBinding getViewBinding() {

        binding = ActivityScoreCardBinding.inflate(getLayoutInflater());

        return  binding;


    }
}