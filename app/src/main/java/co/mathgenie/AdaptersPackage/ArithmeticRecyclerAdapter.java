package co.mathgenie.AdaptersPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.mathgenie.DataModelsPackage.ArithmeticModel;
import co.mathgenie.InterfacePackage.ArithmeticInterface;
import co.mathgenie.R;

public class ArithmeticRecyclerAdapter extends
        RecyclerView.Adapter<ArithmeticRecyclerAdapter.RecyclerViewHolder>
{

    private ArithmeticInterface arithmeticInterface;
    public ArithmeticRecyclerAdapter(ArrayList<ArithmeticModel> arithmeticModels, Context mContext,
                                     ArithmeticInterface arithmeticInterface)
    {
        this.arithmeticModels = arithmeticModels;
        this.mContext = mContext;
        this.arithmeticInterface=arithmeticInterface;
    }

    private ArrayList<ArithmeticModel> arithmeticModels;
    private Context mContext;


    @NonNull
    @Override
    public ArithmeticRecyclerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_grid_recycler_view, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArithmeticRecyclerAdapter.RecyclerViewHolder holder, int position) {

        ArithmeticModel model = arithmeticModels.get(position);
        holder.textView.setText(model.getText());
        if(model.hasDrawable())
        {
            holder.textView.setBackgroundResource(model.getDrawable());
        }

        holder.customSignsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arithmeticInterface.OnItemClick(model);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arithmeticModels.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private CardView customSignsCardView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.customSignsTextView);
            customSignsCardView = itemView.findViewById(R.id.customSignsCardView);

        }
    }
}
