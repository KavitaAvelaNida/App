package com.example.cloneprjocetpts_kavita12rpl11;

import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.target.Target;

import com.example.cloneprjocetpts_kavita12rpl11.EditCustomer;
import com.example.cloneprjocetpts_kavita12rpl11.Model;
import com.example.cloneprjocetpts_kavita12rpl11.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.RentalViewHolder> {


    private ArrayList<Model> datalist;

    public DataAdapter(ArrayList<Model>datalist){
        this.datalist = datalist;
    }

    @Override
    public RentalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapterrv,parent,false);
        return new RentalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RentalViewHolder holder,final int position) {

        holder.email.setText(datalist.get(position).getEmail());
        holder.nama.setText(datalist.get(position).getNama());
        holder.nohp.setText(datalist.get(position).getNohp());
        holder.alamat.setText(datalist.get(position).getAlamat());
        holder.noktp.setText(datalist.get(position).getNoktp());
        holder.card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent in = new Intent(holder.itemView.getContext(), EditCustomer.class);
                in.putExtra("id", datalist.get(position).getId());
                in.putExtra("nama", datalist.get(position).getNama());
                in.putExtra("email", datalist.get(position).getEmail());
                in.putExtra("nohp", datalist.get(position).getNohp());
                in.putExtra("alamat", datalist.get(position).getAlamat());
                in.putExtra("noktp", datalist.get(position).getNoktp());
                holder.itemView.getContext().startActivity(in);


            }
        });


    }

    @Override
    public int getItemCount() {
        return (datalist != null) ? datalist.size() :0;
    }



    class RentalViewHolder extends RecyclerView.ViewHolder{
        private TextView email,nama,nohp,alamat,noktp;
        CardView card;


        RentalViewHolder(View itemView) {
            super(itemView);


            card = (CardView) itemView.findViewById(R.id.cardk);
            email = (TextView) itemView.findViewById(R.id.etemail);
            nama = (TextView) itemView.findViewById(R.id.etnama);
            nohp = (TextView) itemView.findViewById(R.id.etnohp);
            alamat = (TextView) itemView.findViewById(R.id.etalamat);
            noktp = (TextView) itemView.findViewById(R.id.etnoktp);
        }
    }
}