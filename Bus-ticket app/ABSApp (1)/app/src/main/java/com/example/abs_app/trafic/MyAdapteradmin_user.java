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

import com.example.abs_app.R;

import java.util.ArrayList;
import java.util.List;
public class MyAdapteradmin_user extends RecyclerView.Adapter<MyViewHolder1> {
    private Context context;
    private List<com.example.abs_app.trafic.DataClass> dataList;
    public MyAdapteradmin_user(Context context, List<com.example.abs_app.trafic.DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item2, parent, false);
        return new MyViewHolder1(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 holder, int position) {
        //Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
        holder.recTitle.setText(dataList.get(position).getFull_name());
        holder.recDesc.setText(dataList.get(position).getEmail());
        holder.recLang.setText(dataList.get(position).getPassword());
        holder.recimage.setText(dataList.get(position).getPhone_number());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, detail_user.class);

                intent.putExtra("name", dataList.get(holder.getPosition()).getFull_name());
                intent.putExtra("phone", dataList.get(holder.getAdapterPosition()).getPhone_number());
                intent.putExtra("email", dataList.get(holder.getAdapterPosition()).getEmail());
                intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());
                intent.putExtra("password",  dataList.get(holder.getAdapterPosition()).getPassword());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }

    private Bitmap getUsermage(String encodedImage){
        byte[] bytes= Base64.decode(encodedImage,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }
}
class MyViewHolder1 extends RecyclerView.ViewHolder{

    TextView recTitle, recDesc, recLang,recimage;
    CardView recCard;
    public MyViewHolder1(@NonNull View itemView) {
        super(itemView);
        recimage = itemView.findViewById(R.id.recPriority);
        recCard = itemView.findViewById(R.id.recCard);
        recDesc = itemView.findViewById(R.id.recDesc);
        recLang = itemView.findViewById(R.id.recLang);
        recTitle = itemView.findViewById(R.id.recTitle);
    }

}
