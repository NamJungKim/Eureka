package kr.ac.kpu.Eureka.Room;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import kr.ac.kpu.Eureka.Data.Global;
import kr.ac.kpu.Eureka.Data.MyGroup;
import kr.ac.kpu.Eureka.Parser.JsonParser;
import kr.ac.kpu.Eureka.R;
import kr.ac.kpu.Eureka.Tab.TabWithNotificationMarkActivity;

/**
 * Created by KKS on 2017-02-02.
 */

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {

    private Context context;
    private ArrayList<MeetingItem> itemList;

    public MeetingAdapter(Context context, ArrayList<MeetingItem> item) {
        this.context = context;
        this.itemList = item;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meeting,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tvMarket.setText(itemList.get(position).getMarket());
        holder.tvAdress.setText(itemList.get(position).getAddress());
        holder.tvNumOfperson.setText(itemList.get(position).getNumOfperson());
        holder.tvAreaOfperson.setText(itemList.get(position).getAreaOfperson());
        holder.tvDate.setText(itemList.get(position).getDate());
        holder.Layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonParser.getInstance().SetRequestQueue(JsonParser.getInstance().GetGroupUsers(itemList.get(position)._id), 1); // 참여룸 갱신
            }
        });


        holder.Layout1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               TabWithNotificationMarkActivity.handler_dialog.sendEmptyMessage(0);
                return false;
            }
        });

        holder.exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonParser.getInstance().SetRequestQueue(JsonParser.getInstance().deleteRoom(Global.myinfo.get_id(), itemList.get(position)._id), 1); // 참여룸 갱신
                for(int i = 0; i< Global.entireGroups.size(); i++) {
                    if (Global.entireGroups.get(i).getGroup_id().equals(itemList.get(position)._id)) {
                        MyGroup data = Global.entireGroups.get(i);
                        Log.d("people",data.getPresentpeopleCnt());
                        if(Integer.parseInt(data.getPresentpeopleCnt()) == 0){ //현재인원이 0이면 그룹삭제(디비에서도)

                        }

                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMarket;
        TextView tvAdress;
        TextView tvNumOfperson;
        TextView tvAreaOfperson;
        TextView tvDate;
        LinearLayout Layout1;
        TextView exit;

        public ViewHolder(View itemView) {
            super(itemView);
            tvMarket = (TextView)itemView.findViewById(R.id.tvMarket);
            tvAdress = (TextView)itemView.findViewById(R.id.tvAdress);
            tvNumOfperson = (TextView)itemView.findViewById(R.id.tvNumOfperson);
            tvAreaOfperson = (TextView)itemView.findViewById(R.id.tvAreaOfperson);
            tvDate = (TextView)itemView.findViewById(R.id.tvDate);
            Layout1 = (LinearLayout) itemView.findViewById(R.id.lay2);

            exit = (TextView)itemView.findViewById(R.id.exit);

        }
    }
}
