package com.example.naveed.darzii.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.naveed.darzii.Information;
import com.example.naveed.darzii.R;

import java.util.ArrayList;

/**
 * Created by Naveed on 12/21/2017.
 */

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.VH> {
    Context context;
    ArrayList<Information> list;

    public InformationAdapter(Context context, ArrayList<Information> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.info_item_view_layout, null);

        return new VH(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.name.setText(list.get(position).getName());
    }


    public class VH extends RecyclerView.ViewHolder {
        TextView name;

        public VH(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txt_info_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Information information = list.get(getAdapterPosition());
                    Intent intent = new Intent(context, DetailInformationActivity.class);
                    intent.putExtra("id", information.getId());
                    intent.putExtra("name", information.getName());
                    intent.putExtra("address", information.getAddress());
                    intent.putExtra("collarSize", information.getCollarSize());
                    intent.putExtra("chestSize", information.getChestSize());
                    intent.putExtra("shirtLenght", information.getShirtLength());
                    intent.putExtra("armLenght", information.getArmLength());

                    context.startActivity(intent);
                }
            });
        }
    }
}
