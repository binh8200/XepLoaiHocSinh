package com.example.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.test.Model.SinhVien;
import com.example.test.R;

import java.util.ArrayList;

public class SinhVienAdapter extends BaseAdapter {
    String Loai;
    Context context;
    ArrayList<SinhVien> dulieu;

    public SinhVienAdapter(Context context, ArrayList<SinhVien> dulieu) {
        this.context = context;
        this.dulieu = dulieu;
    }

    @Override
    public int getCount() {
        return dulieu.size();
    }

    @Override
    public Object getItem(int i) {
        return dulieu.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.item_list,null);
        }

        TextView tvTen =convertView.findViewById(R.id.tvTen);
        TextView tvDiem =convertView.findViewById(R.id.tvDiem);
        TextView tvLoai =convertView.findViewById(R.id.tvLoai);

        SinhVien w =dulieu.get(position);
        tvTen.setText(w.getTen());
        tvDiem.setText(w.getDiem());
        if (Float.parseFloat(w.getDiem()) >= 8 && Float.parseFloat(w.getDiem()) <= 10){
            Loai = "Giỏi";
        } else if (Float.parseFloat(w.getDiem()) >= 7 && Float.parseFloat(w.getDiem()) < 8) {
            Loai = "Khá";
        } else if (Float.parseFloat(w.getDiem()) >= 5 && Float.parseFloat(w.getDiem()) < 7) {
            Loai = "Trung Bình";
        } else if (Float.parseFloat(w.getDiem()) < 5 && Float.parseFloat(w.getDiem()) >= 0) {
            Loai = "Yếu";
        } else {
            Loai = null;
        }
        tvLoai.setText(Loai);
        return convertView;
    }

}
