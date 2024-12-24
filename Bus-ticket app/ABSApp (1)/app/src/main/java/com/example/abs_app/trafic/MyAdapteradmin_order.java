package com.example.abs_app.trafic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abs_app.DataClass;
import com.example.abs_app.R;

import java.util.ArrayList;
import java.util.List;
public class MyAdapteradmin_order extends RecyclerView.Adapter<MyViewHolder_order> {
    private Context context;
    private List<DataClass1> dataList;
    public MyAdapteradmin_order(Context context, List<DataClass1> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MyViewHolder_order onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item3, parent, false);
        return new MyViewHolder_order(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_order holder, int position) {
        //Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
        holder.recTitle.setText(dataList.get(position).getOcity());
        holder.recDesc.setText(dataList.get(position).getOprice());
        holder.recLang.setText(dataList.get(position).getOname());
        holder.recImage.setText(dataList.get(position).getOphone());
        holder.recKm.setText(dataList.get(position).getOkm());
        holder.recDate.setText(dataList.get(position).getDate());
        holder.recCar.setText(dataList.get(position).getOcar());


        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(context, detailadmin.class);

                intent.putExtra("Image", dataList.get(holder.getPosition()).getDataImage());
                intent.putExtra("Description", dataList.get(holder.getAdapterPosition()).getDataDesc());
                intent.putExtra("Title", dataList.get(holder.getAdapterPosition()).getDataTitle());
                intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());
                intent.putExtra("Language",  dataList.get(holder.getAdapterPosition()).getDataLang());
                context.startActivity(intent);*/
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
   /* public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }*/
    private Bitmap getUsermage(String encodedImage){
        byte[] bytes= Base64.decode(encodedImage,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }
}
class MyViewHolder_order extends RecyclerView.ViewHolder{

    TextView recTitle, recDesc, recLang,recImage,recKm,recDate,recCar;
    CardView recCard;
    public MyViewHolder_order(@NonNull View itemView) {
        super(itemView);
        recImage = itemView.findViewById(R.id.order_phone);
        recCard = itemView.findViewById(R.id.recCard3);
        recDesc = itemView.findViewById(R.id.order_price);
        recLang = itemView.findViewById(R.id.order_name);
        recTitle = itemView.findViewById(R.id.order_city);
        recCar = itemView.findViewById(R.id.order_car);
        recDate = itemView.findViewById(R.id.order_date);
        recKm = itemView.findViewById(R.id.order_km);

    }

}
