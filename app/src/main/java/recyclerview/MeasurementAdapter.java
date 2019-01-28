package recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import Data.Measurement;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.examlple.com.openair.R;
import utils.ColorUtils;

public class MeasurementAdapter extends RecyclerView.Adapter<MeasurementAdapter.MeasurementViewHolder>
{
    private List<Measurement> detailsList;


    public MeasurementAdapter( List<Measurement> detailsList) {

        this.detailsList = detailsList;
    }


    @NonNull
    @Override
    public MeasurementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_detailmeasurement, parent, false);

        return new MeasurementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeasurementViewHolder holder, int position) {
        Measurement d = detailsList.get(position);
        holder.param.setText("Parameter: "+d.getParam().toUpperCase());
        holder.value.setText(d.getValue() + " " + d.getUnit());
        holder.lat.setText("Lat: "+ String.format ("%.5f", d.getLatitude()));
        holder.date.setText(d.getDate());
        holder.longtitude.setText("Long: "+String.format ("%.5f", d.getLongitude()));

        //get background color based on parameter and its value
        Context context = holder.layout.getContext();
        int backgroundColor = ColorUtils.getColor(context, d.getValue(), d.getParam().toUpperCase());
        holder.layout.setBackgroundColor(backgroundColor);

    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    public class MeasurementViewHolder extends RecyclerView.ViewHolder{

        TextView param, value, lat, date, longtitude;
        LinearLayout layout;

        public MeasurementViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout_list_detailM);
            param = itemView.findViewById(R.id.tv_param);
            value = itemView.findViewById(R.id.tv_value);
            lat = itemView.findViewById(R.id.tv_lat);
            date = itemView.findViewById(R.id.tv_detailM_date);
            longtitude = itemView.findViewById(R.id.tv_longtitude);

        }
    }


}
