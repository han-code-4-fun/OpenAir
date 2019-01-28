package recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import Data.Location;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.examlple.com.openair.R;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    final private CustomListItemClickListener mOnClickListener;

    private List<Location> locations;

    public LocationAdapter(List<Location> inputLocations, CustomListItemClickListener listener){

        locations= inputLocations;
        mOnClickListener= listener;
    }

    //use customer click listener to set onlick event for each location in the recycler view
    public interface CustomListItemClickListener {
        void customOnListItemClick(int clickedItemIndex);
    }





    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_location, parent, false);

        LocationViewHolder output = new LocationViewHolder(view);

        return output;
    }

    @Override
    public int getItemCount() { return locations.size();}

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        Location location = locations.get(position);
        holder.name.setText(location.getLocation());
        holder.date.setText(location.getDate());

    }

    public class LocationViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        TextView name, date;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_location_name);
            date = itemView.findViewById(R.id.tv_time);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mOnClickListener.customOnListItemClick(position);

        }
    }
}
