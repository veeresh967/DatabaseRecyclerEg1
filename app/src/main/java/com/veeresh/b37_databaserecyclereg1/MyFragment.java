package com.veeresh.b37_databaserecyclereg1;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {
    //11. declare all required variables
    EditText et1, et2;
    Button b1;
    RecyclerView rv; //this is destination
    LinearLayoutManager manager;
    MyAdapter myAdapter; //adapter
    Cursor c; //in place of arraylist, we have cursor as the data source

    //10. go to fragment java file, create an inner class for recyler adapter
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = getActivity().getLayoutInflater().inflate(R.layout.row, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //get data from cursor based on position
            c.moveToPosition(position); //cursor will be moved to that row
            int sno = c.getInt(0); //0th column student no - read sno
            String name = c.getString(1); //1st column student name - read it
            String sub = c.getString(2); //2nd column student subject - read it
            //fill data onto view holder views.
            holder.tv1.setText(""+sno);
            holder.tv2.setText(name);
            holder.tv3.setText(sub);
        }
        @Override
        public int getItemCount() {
            if(c == null){
                return 0;
            }
            return c.getCount();
        }
        public class ViewHolder extends RecyclerView.ViewHolder{
            public TextView tv1, tv2, tv3;
            public ViewHolder(View itemView) {
                super(itemView);
                tv1 = itemView.findViewById(R.id.textView1);
                tv2 = itemView.findViewById(R.id.textView2);
                tv3 = itemView.findViewById(R.id.textView3);
            }
        }
    }

    public MyFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //12. intialize all variables - and - establish links
        View v = inflater.inflate(R.layout.fragment_my, container, false);
        et1 = v.findViewById(R.id.editText1);
        et2 = v.findViewById(R.id.editText2);
        b1 = v.findViewById(R.id.button1);  //[INSERT BUTTON]
        rv = v.findViewById(R.id.recyclerView1);
        manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        myAdapter = new MyAdapter();
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(manager);
        //13. write button click listener - for insert & read & notify adapter
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et1.getText().toString().trim();
                String sub = et2.getText().toString().trim();
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.m.insertStudent(name, sub);
                c = mainActivity.m.queryStudent();
                myAdapter.notifyDataSetChanged();
                et1.setText("");et2.setText("");et1.requestFocus();
            }
        });
        return v;
    }

}
