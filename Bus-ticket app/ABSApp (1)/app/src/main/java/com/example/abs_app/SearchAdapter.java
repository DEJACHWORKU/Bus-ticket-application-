package com.example.abs_app;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends BaseAdapter implements Filterable {

    private Context context;
    private List<String> suggestions;
    private List<String> filteredSuggestions;

    public SearchAdapter(Context context, String[] suggestions) {
        this.context = context;
        this.suggestions = new ArrayList<>();
        this.filteredSuggestions = new ArrayList<>();

        // Copy the initial suggestions to both lists
        for (String suggestion : suggestions) {
            this.suggestions.add(suggestion);
            this.filteredSuggestions.add(suggestion);
        }
    }

    @Override
    public int getCount() {
        return filteredSuggestions.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredSuggestions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        // Set the suggestion text to the TextView
        TextView suggestionText = convertView.findViewById(android.R.id.text1);
        suggestionText.setText(filteredSuggestions.get(position));

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                // Filter the suggestions based on the constraint
                List<String> filteredList = new ArrayList<>();
                for (String suggestion : suggestions) {
                    if (suggestion.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(suggestion);
                    }
                }

                results.values = filteredList;
                results.count = filteredList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredSuggestions = (List<String>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
