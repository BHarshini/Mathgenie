package co.mathgenie.AllActivities;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewbinding.ViewBinding;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import co.mathgenie.AdaptersPackage.ArithmeticRecyclerAdapter;
import co.mathgenie.BaseActivity;
import co.mathgenie.DataModelsPackage.ArithmeticModel;
import co.mathgenie.InterfacePackage.ArithmeticInterface;
import co.mathgenie.R;
import co.mathgenie.databinding.ActivityDashBoardBinding;

public class DashBoardActivity extends BaseActivity<ViewBinding> {

    private ActivityDashBoardBinding binding;

    private ArrayList<ArithmeticModel> arithmeticModelArrayList;

    MediaPlayer mediaPlayer;
    Bundle args = new Bundle();
    String operator;

    @Override
    protected ActivityDashBoardBinding getViewBinding() {
        binding = ActivityDashBoardBinding.inflate(getLayoutInflater());
        return binding;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer = MediaPlayer.create(this, R.raw.button_sound);
        arithmeticModelArrayList = new ArrayList<>();

        arithmeticModelArrayList.add(new ArithmeticModel("+ Addition",0));
        arithmeticModelArrayList.add(new ArithmeticModel("- Subtract",0));
        arithmeticModelArrayList.add(new ArithmeticModel("x Multiple",0));
        arithmeticModelArrayList.add(new ArithmeticModel("/ Division",0));

        ArithmeticRecyclerAdapter arithmeticRecyclerAdapter = new ArithmeticRecyclerAdapter(arithmeticModelArrayList, this, new ArithmeticInterface() {
            @Override
            public void OnItemClick(ArithmeticModel arithmeticModel) {
                mediaPlayer.start();
                String operationIs = arithmeticModel.getText();

                switch (operationIs)
                {

                    case "+ Addition":
                        operator = "+";
                        goToFragment(operator,new CalculationActivity());
                        break;

                    case "- Subtract":
                        operator = "-";
                        goToFragment(operator,new CalculationActivity());
                        break;

                    case "x Multiple":
                        operator  = "*";
                        goToFragment(operator,new CalculationActivity());
                        break;

                    case  "/ Division":
                        operator = "/";
                        goToFragment(operator,new CalculationActivity());
                        break;

                    default:
                        break;

                }

            }
        });

        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(arithmeticRecyclerAdapter);

        binding.myScoreCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                //goToFragment(args,new ScoreCardActivity());
                Intent intent = new Intent(DashBoardActivity.this, ScoreCardActivity.class);
                startActivity(intent);

            }
        });

        binding.learnFromYoutube.customSignsTextView.setText(R.string.learn_from_youtube);
        binding.learnFromYoutube.customSignsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer.start();
                //goToFragment(,new YouTubeActivity());

                Intent intent = new Intent(DashBoardActivity.this, YouTubeActivity.class);
                startActivity(intent);

            }
        });

    }


    private void goToFragment(String operator, Activity activity)
    {

        Intent intent = new Intent(DashBoardActivity.this, activity.getClass());
        intent.putExtra("operatorIs", operator);
        startActivity(intent);

    }


}