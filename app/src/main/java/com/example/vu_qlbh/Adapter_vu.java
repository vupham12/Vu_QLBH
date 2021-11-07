package com.example.vu_qlbh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_vu  extends BaseAdapter {
    Context context;
    ArrayList<Vu_Baihat> list;

    public Adapter_vu(Context context, ArrayList<Vu_Baihat> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.row_vubh,null);

        TextView txtTenBH =(TextView)row.findViewById(R.id.textViewTenBH);
        TextView txtTenCS =(TextView)row.findViewById(R.id.textViewCS);
        Button btnSua =(Button) row.findViewById(R.id.buttonSua);
        Button btnXoa =(Button) row.findViewById(R.id.buttonXoa);
        ImageView imgAnh = (ImageView) row.findViewById(R.id.imageViewAnh);


        Vu_Baihat vu_baihat = list.get(i);
        txtTenBH.setText(vu_baihat.TenBh+"");
        txtTenCS.setText(vu_baihat.TenCaSi);
        Bitmap bitmap = BitmapFactory.decodeByteArray(vu_baihat.anh,0,vu_baihat.anh.length);
        imgAnh.setImageBitmap(bitmap);

        return row;
    }
}
