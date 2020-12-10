package com.example.cloneprjocetpts_kavita12rpl11;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

    public class SepedaAdapter extends RecyclerView.Adapter<SepedaAdapter.RentalViewHolder> {


        private ArrayList<ModelMaster> datalist;

        public SepedaAdapter(ArrayList<ModelMaster> datalist){
            this.datalist = datalist;
        }

        @Override
        public RentalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.list,parent,false);
            return new RentalViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final RentalViewHolder holder,final int position) {

            holder.KodeSepeda.setText(datalist.get(position).getKodesepeda());
            holder.Merk.setText(datalist.get(position).getMerk());
            holder.Warna.setText(datalist.get(position).getWarna());
            holder.HargaSewa.setText(datalist.get(position).getHargasewa());
            holder.Jenis.setText(datalist.get(position).getJenis());
            holder.card.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent in = new Intent(holder.itemView.getContext(), EditSepeda.class);
                    in.putExtra("id", datalist.get(position).getId());
                    in.putExtra("kodesepeda", datalist.get(position).getKodesepeda());
                    in.putExtra("merk", datalist.get(position).getMerk());
                    in.putExtra("warna", datalist.get(position).getWarna());
                    in.putExtra("hargasewa", datalist.get(position).getHargasewa());
                    in.putExtra("jenis", datalist.get(position).getJenis());
                    holder.itemView.getContext().startActivity(in);


                }
            });


        }

        @Override
        public int getItemCount() {
            return (datalist != null) ? datalist.size() :0;
        }


        class RentalViewHolder extends RecyclerView.ViewHolder{
            private TextView KodeSepeda,Merk,Warna,HargaSewa,Jenis;
            CardView card;


            RentalViewHolder(View itemView) {
                super(itemView);


                card = (CardView) itemView.findViewById(R.id.cardku);
                KodeSepeda = (TextView) itemView.findViewById(R.id.kodesepeda);
                Merk = (TextView) itemView.findViewById(R.id.tmerk);
                Warna = (TextView) itemView.findViewById(R.id.twarna);
                HargaSewa = (TextView) itemView.findViewById(R.id.thargasewa);
                Jenis = (TextView) itemView.findViewById(R.id.tjenis);
            }
        }
    }

