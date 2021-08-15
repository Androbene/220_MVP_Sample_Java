package ua.androbene.a220_mvp_sample;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    List<UsersData> data = new ArrayList<>();

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<UsersData> users) {
        data.clear();
        data.addAll(users);
        notifyDataSetChanged();
        Log.d("MYLOG", "size  = " + getItemCount());
    }

    static class UserHolder extends RecyclerView.ViewHolder {

        TextView text;

        public UserHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }

        void bind(UsersData user) {
            text.setText(String.format("id: %s, name: %s, email: %s", user.getId(), user.getName(), user.getEmail()));
        }
    }

}
