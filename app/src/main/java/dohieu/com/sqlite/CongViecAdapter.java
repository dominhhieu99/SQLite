package dohieu.com.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CongViecAdapter extends BaseAdapter {
    private MainActivity context;
    private int laout;
    private List<CongViec> congViecList;

    public CongViecAdapter(MainActivity context, int laout, List<CongViec> congViecList) {
        this.context = context;
        this.laout = laout;
        this.congViecList = congViecList;
    }

    @Override
    public int getCount() {
        return congViecList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView txtTen;
        ImageView imgDelete, imgEditti;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(laout, null);
            viewHolder.txtTen = view.findViewById(R.id.tvNameCV);
            viewHolder.imgDelete = view.findViewById(R.id.imgdelete);
            viewHolder.imgEditti = view.findViewById(R.id.imgEditi);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final CongViec congViec = congViecList.get(i);
        viewHolder.txtTen.setText(congViec.getTenCV());
//        bắt sự kiện xóa sửa
        viewHolder.imgEditti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.Dialogsuaw();
            }
        });
        return view;
    }
}
