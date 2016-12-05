package xyz.georgihristov.scorecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreCardAdapter extends BaseAdapter {
    private Holes[] holesArray;
    static int points = 0;
    private Context context;

    public ScoreCardAdapter(Holes[] holesArray, Context context) {
        this.holesArray = holesArray;
        this.context = context;
    }

    @Override
    public int getCount() {
        return holesArray.length;
    }

    @Override
    public Object getItem(int position) {
        return holesArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.scorecard_list_item,null);
            holder = new ViewHolder();

            holder.holeTextView = (TextView) convertView.findViewById(R.id.holeTextView);
            holder.pointTextView = (TextView) convertView.findViewById(R.id.pointTextView);
            holder.plusButton = (Button) convertView.findViewById(R.id.plusButton);
            holder.minusButton = (Button) convertView.findViewById(R.id.minusButton);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.holeTextView.setText(holesArray[position].getHole());
        holder.pointTextView.setText(holesArray[position].getPointCount() + "");

        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int update = holesArray[position].getPointCount() + 1;
                holesArray[position].setPointCount(update);
                holder.pointTextView.setText(update + "");

            }
        });
        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int update = holesArray[position].getPointCount() + -1;
                if(update < 0){
                        update = 0;
                }
                holesArray[position].setPointCount(update);
                holder.pointTextView.setText(update + "");

            }
        });


        return convertView;
    }

    private static class ViewHolder{
        //public by default
        TextView holeTextView;
        TextView pointTextView;
        Button plusButton;
        Button minusButton;

    }


}
