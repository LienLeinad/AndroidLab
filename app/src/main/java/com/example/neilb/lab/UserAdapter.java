package com.example.neilb.lab;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class UserAdapter extends RealmRecyclerViewAdapter<UserObject, UserAdapter.ViewHolder> {
    private welcome context;
    private RegHome contextReg;
    public UserAdapter(welcome context ,@Nullable RealmResults<UserObject> data) {
        super(data, true);
        this.context = context;

    }

    public UserAdapter(RegHome contextReg, @Nullable RealmResults<UserObject> data){
        super(data, true);
        this.contextReg = contextReg;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = contextReg.getLayoutInflater().inflate(R.layout.row_layout, null);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        UserObject u = getItem(position);
        holder.userName.setText(u.getUsername());
        holder.password.setText(u.getPassword());
        holder.email.setText(u.getEmail());
        holder.deleteButton.setTag(u);
        holder.editButton.setTag(u);

    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView userName;
        TextView password;
        TextView email;
        Button editButton;
        Button deleteButton;
        public ViewHolder(@NonNull View itemView){

            super(itemView);

            userName = itemView.findViewById(R.id.userName);
            password = itemView.findViewById(R.id.password);
            email = itemView.findViewById(R.id.email);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);

            editButton.setOnClickListener(editListener);
            deleteButton.setOnClickListener(deleteListener);
        }
    }
    private View.OnClickListener deleteListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            UserObject u = (UserObject) v.getTag();
            contextReg.delete(u);
        }
    };
    private View.OnClickListener editListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UserObject u = (UserObject) v.getTag();
            System.out.println(u.getUsername());
            contextReg.edit(u);
        }
    };
}
