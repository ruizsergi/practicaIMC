package com.example.slafuente.imc;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by slafuente on 15/01/2017.
 */
public class AdapterPropio extends BaseAdapter{
    private Context mContext;
    private ArrayList<String> data;
    private LayoutInflater inflater = null;
    private TextView tvv = null;
    private ImageView iv = null;

    public AdapterPropio(Context c, ArrayList<String> data) {
        mContext = c;
        this.data = data;
        inflater = LayoutInflater.from(mContext);
    }
    /**
     * How many items are in the data set represented by this Adapter.
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return data.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     * @param position Position of the item whose data we want within the adapter's data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.nuevafila, null);
        } else {
            //listView = (ListView) convertView;
        }

        tvv = (TextView)convertView.findViewById(R.id.textviewFila);
        iv = (ImageView) convertView.findViewById(R.id.imageView);


        int imageDelgado = mContext.getResources().getIdentifier("delgadez","drawable",mContext.getPackageName());
        int imageNormal = mContext.getResources().getIdentifier("normal","drawable",mContext.getPackageName());
        int imageObeso = mContext.getResources().getIdentifier("obeso","drawable",mContext.getPackageName());
        int imageSobrepeso = mContext.getResources().getIdentifier("sobrepeso","drawable",mContext.getPackageName());
        int imageBajopeso = mContext.getResources().getIdentifier("bajopeso","drawable",mContext.getPackageName());

        switch(position) {
            case 0:
                tvv.setText("< 16 DESNUTRIDO");
                iv.setImageResource(imageDelgado);
                break;
            case 1:
                tvv.setText("=> 16 < 18,5 BAJO PESO");
                iv.setImageResource(imageBajopeso);
                break;
            case 2:
                tvv.setText("=> 18,5 < 25 NORMAL");
                iv.setImageResource(imageNormal);
                break;
            case 3:
                tvv.setText(">=25 < 31 SOBREPESO");
                iv.setImageResource(imageSobrepeso);
                break;
            case 4:
                tvv.setText(">= 31 OBESIDAD");
                iv.setImageResource(imageObeso);
                break;
        }

        return convertView;
    }
}
