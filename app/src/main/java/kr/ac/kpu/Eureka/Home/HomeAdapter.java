package kr.ac.kpu.Eureka.Home;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.ac.kpu.Eureka.R;
import kr.ac.kpu.Eureka.RoomDetailAcivity;

/**
 * Created by KKS on 2017-02-02.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private ArrayList<HomeItem> itemList;
    private final Context context;

    public HomeAdapter(Context context, ArrayList<HomeItem> item) {
        this.context = context;
        this.itemList = item;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_rooms,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    public static String click_id;
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.iconImageView.setImageDrawable(itemList.get(position).icont);
        holder.titleTextView.setText(itemList.get(position).title);
        holder.descTextView.setText(itemList.get(position).desc);
        holder.lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(itemList.get(position).id,itemList.get(position).id);
                click_id = itemList.get(position).id;
                Intent intent = new Intent(context,RoomDetailAcivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iconImageView;
        TextView titleTextView;
        TextView descTextView;
        ConstraintLayout lay;
        public ViewHolder(View itemView) {
            super(itemView);
            lay = (ConstraintLayout) itemView.findViewById(R.id.layout1) ;
            iconImageView = (ImageView) itemView.findViewById(R.id.imageView) ;
            titleTextView = (TextView) itemView.findViewById(R.id.textView1) ;
            descTextView = (TextView) itemView.findViewById(R.id.textView2) ;
        }
    }
}
