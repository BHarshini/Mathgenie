package co.mathgenie;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import co.mathgenie.AllActivities.DashBoardActivity;
import co.mathgenie.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding>
{
    private ActivityMainBinding binding;

    @Override
    protected ActivityMainBinding getViewBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        return binding;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(MainActivity.this, DashBoardActivity.class));
                finish();

            }
        },5000);

    }

}