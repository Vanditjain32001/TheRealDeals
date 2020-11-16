package com.example.test1;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class profile2 extends ArrayAdapter<property_data> {
    public profile2(@NonNull Context context, int resource, @NonNull List<property_data> objects) {
        super(context, 0, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.activity_profile, parent, false);
        }
        property_data property = getItem(position);

        final ImageView currentImageView = (ImageView)listItemView.findViewById(R.id.thumbnail_image);
        TextView price = (TextView)listItemView.findViewById(R.id.price);
        TextView area = (TextView)listItemView.findViewById(R.id.area);
        TextView bhk = (TextView)listItemView.findViewById(R.id.bhk);

        RequestOptions options = new RequestOptions()
                .centerCrop();

        price.setText(property.getPrice());
        area.setText(property.getArea());
        bhk.setText(property.getBhk());
        area.setSelected(true);
        return listItemView;


    }
}

