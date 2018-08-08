package vn.luongvo.weatherapp.ui.settings;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.luongvo.weatherapp.BR;
import vn.luongvo.weatherapp.R;
import vn.luongvo.weatherapp.dto.City;


/**
 * Created by luongvo on 8/9/18.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private List<City> cities;
    private SettingsContact.ActionListener listener;

    public CityAdapter(List<City> cities, SettingsContact.ActionListener listener) {
        this.cities = cities;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.setVariable(BR.city, cities.get(position));
        holder.binding.setVariable(BR.listener, listener);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
