package vn.luongvo.weatherapp.ui.main;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.luongvo.weatherapp.BR;
import vn.luongvo.weatherapp.R;
import vn.luongvo.weatherapp.dto.WeatherInfo;


/**
 * Created by luongvo on 8/9/18.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private List<WeatherInfo> weatherInfoList;
    private MainContact.ActionListener listener;

    public ForecastAdapter(List<WeatherInfo> weatherInfoList, MainContact.ActionListener listener) {
        this.weatherInfoList = weatherInfoList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.setVariable(BR.weatherInfo, weatherInfoList.get(position));
        holder.binding.setVariable(BR.listener, listener);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return weatherInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
