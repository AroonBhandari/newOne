package com.example.kotha;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter extends BaseAdapter implements Filterable {
    Context context;
ArrayList<room> arrayList= new ArrayList<>();

    public adapter() {
    }

    public adapter(Context context, ArrayList<room> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.customview,null);
        TextView t1_budget=(TextView) convertView.findViewById(R.id.budget_txt);
        TextView t2_location= (TextView) convertView.findViewById(R.id.location);
        TextView t3_noofrooms=(TextView) convertView.findViewById(R.id.noofrooms);
        ImageView t4_image =(ImageView) convertView.findViewById(R.id.image);
        room room = arrayList.get(position);
        t3_noofrooms.setText(String.valueOf((room.getNoRooms())));
        t1_budget.setText(String.valueOf(room.getBudget()));
        t2_location.setText(room.getDescription());
        return  convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter=  new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults Result = new FilterResults();
                if (constraint.length()==0){
                    Result.values=arrayList;
                    Result.count=arrayList.size();
                    return Result;
                }
                ArrayList<room> Filter_Names = new ArrayList<>();
                String filterString= constraint.toString().toLowerCase();
                room filterableString;
                for(int i=0; i<arrayList.size();i++){
                    filterableString =arrayList.get(i);
                    if(filterableString.getLocation().toLowerCase().contains(filterString)){
                        Filter_Names.add(filterableString);
                    }

                }
                Result.values=Filter_Names;
                Result.count=Filter_Names.size();
return Result;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<room> Filter_Names =(ArrayList<room>) results.values;
           notifyDataSetChanged();
            }
        };
        return filter;
    }
}
