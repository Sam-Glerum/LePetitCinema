package com.example.icadi.lepetitcinema.Presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.icadi.lepetitcinema.Domain.Ticket;
import com.example.icadi.lepetitcinema.R;

import java.util.ArrayList;

public class ETicketsAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Ticket> tickets;

    public ETicketsAdapter(Context context, LayoutInflater layoutInflater, ArrayList<Ticket> tickets) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.tickets = tickets;
    }

    @Override
    public int getCount() {
        return tickets.size();
    }

    @Override
    public Object getItem(int i) {
        return tickets.get(i);
    }

    @Override
    public long getItemId(int i) {
        return tickets.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.tickets_listview_row, null);

            viewHolder = new ViewHolder();
            viewHolder.rowHeader= view.findViewById(R.id.ticketRowHeader);
            viewHolder.filmTitleEticket = view.findViewById(R.id.filmTitleEticketItem);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Ticket ticket = tickets.get(i);

        viewHolder.filmTitleEticket.setText(ticket.getFilmName());

        return view;
    }

    private class ViewHolder {
        private TextView rowHeader;
        private TextView filmTitleEticket;
    }
}
