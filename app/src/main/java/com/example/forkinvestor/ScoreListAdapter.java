package com.example.forkinvestor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ScoreListAdapter extends ArrayAdapter<UserScore> {

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    ScoreListAdapter(Context context, int resource, List<UserScore> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String name = getItem(position).getUserName();
        String score = getItem(position).getUserScore();

        UserScore user = new UserScore(name, score);

        final View result;
        ViewHolder holder;

        if(convertView == null) {
            Log.e("VIEW", "NULL");
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.textViewUser);
            holder.score = convertView.findViewById(R.id.textViewScore);

            result = convertView;

            convertView.setTag(holder);
        }else{
            Log.e("VIEW", "FULL");
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.name.setText(position +1 + ". " + name);
        holder.score.setText(score);

        return convertView;
    }

    private static class ViewHolder {
        private TextView name;
        private TextView score;
    }
}
