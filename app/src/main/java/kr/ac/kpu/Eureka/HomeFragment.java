package kr.ac.kpu.Eureka;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by namjung on 2017. 5. 23..
 */

public class HomeFragment extends ListFragment {
    ListViewAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        adapter = new ListViewAdapter();
        setListAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.ic_flash_on_white_24dp),"첫번째 텍스트",
                "두번째 텍스트");
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.ic_home_white_24dp),"첫번째 텍스트",
                "두번째 텍스트");
        adapter.addItem(ContextCompat.getDrawable(getActivity(),R.drawable.ic_person_white_24dp),"첫번째 텍스트",
                "두번째 텍스트");
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ListViewItem item = (ListViewItem)l.getItemAtPosition(position);
        Intent intent = new Intent(getActivity(),RoomDetailAcivity.class);
        startActivityForResult(intent,0);
        //등등 코드 입력
    }
}
