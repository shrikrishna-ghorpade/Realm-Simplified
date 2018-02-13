package com.realmsimplified;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.realmsimplified.entities.SampleEntity;
import com.realmsimplified.repos.SampleEntityRepo;

import java.util.List;

public class ListAdapter extends ArrayAdapter<SampleEntity> {

    private final Activity activity;
    private List<SampleEntity> sampleEntities;
    private SharedPreferences preferences;
    private int lastPosition = -1;

    /**
     * DESCRIPTION:
     * Constructs an instance of TripListAdapter.
     *
     * @param activity - the Android Activity instance that owns the ListView.
     * @param sampleEntities  - the List of TripRecord instances for display in the ListView.
     */
    public ListAdapter(Activity activity, List<SampleEntity> sampleEntities) {
        super(activity, R.layout.row_list, sampleEntities);
        this.activity = activity;
        this.sampleEntities = sampleEntities;
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    /**
     * DESCRIPTION:
     * Constructs and populates a View for display of the TripRecord at the index
     * of the List specified by the position parameter.
     *
     * @see ArrayAdapter#getView(int, View, ViewGroup)
     */
    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        // create a view for the row if it doesn't already exist

        if (view == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            view = inflater.inflate(R.layout.row_list, null);
        }

        TextView tvName = (TextView) view.findViewById(R.id.txt_name);
        tvName.setText(sampleEntities.get(position).getName());

        TextView tvTime = (TextView) view.findViewById(R.id.txt_age);
        tvTime.setText(sampleEntities.get(position).getAge()+"");

        ImageView imgDelete = (ImageView) view.findViewById(R.id.img_delete);

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SampleEntityRepo genericEntityRepo = new SampleEntityRepo(getContext(), SampleEntity.class);
                genericEntityRepo.deleteById(sampleEntities.get(position).getId());
                sampleEntities.remove(position);
                notifyDataSetChanged();
            }
        });

        setAnimation(view, position);
        return view;
    }

    /**
     * DESCRIPTION:
     * Called by parent when the underlying data set changes.
     *
     * @see ArrayAdapter#notifyDataSetChanged()
     */
    @Override
    public void notifyDataSetChanged() {

        // configuration may have changed - get current settings
        //todo
        //getSettings();
        super.notifyDataSetChanged();
    }

    private void setAnimation(View viewToAnimate, int position){
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(activity, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
