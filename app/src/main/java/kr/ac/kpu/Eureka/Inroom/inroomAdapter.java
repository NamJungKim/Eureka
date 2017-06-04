package kr.ac.kpu.Eureka.Inroom;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import kr.ac.kpu.Eureka.R;
import kr.ac.kpu.Eureka.Room.MeetingItem;

/**
 * Created by KKS on 2017-02-02.
 */

public class inroomAdapter extends RecyclerView.Adapter<inroomAdapter.ViewHolder> {

    private Context context;
    private ArrayList<inroomItem> itemList;

    public inroomAdapter(Context context, ArrayList<inroomItem> item) {
        this.context = context;
        this.itemList = item;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_roommember,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.ids.setText("아이디 : "+ itemList.get(position)._userid);
        holder.names.setText("이름 : "+ itemList.get(position)._name);
        holder.emails.setText("이메일 : "+ itemList.get(position)._email);
        holder.phones.setText("핸드폰번호 : "+ itemList.get(position)._phone);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ids;
        TextView names;
        TextView emails;
        TextView phones;

        public ViewHolder(View itemView) {
            super(itemView);
            ids = (TextView)itemView.findViewById(R.id.idsz);
            names = (TextView)itemView.findViewById(R.id.names);
            emails = (TextView)itemView.findViewById(R.id.emails);
            phones = (TextView)itemView.findViewById(R.id.phones);
        }
    }
}
