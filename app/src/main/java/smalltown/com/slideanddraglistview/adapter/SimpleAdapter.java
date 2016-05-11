package smalltown.com.slideanddraglistview.adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import smalltown.com.slideanddraglistview.R;
import smalltown.com.slideanddraglistview.activity.SimpleActivity;

/**
 * Created by 程小镇 on 2016/5/6 0006.
 */
public class SimpleAdapter extends BaseAdapter {
    private OnClickListener onClickListener;
    private Context context;
    private List<ApplicationInfo> mAppList;

    public SimpleAdapter(Context context, List<ApplicationInfo> mAppList) {
        this.context = context;
        this.mAppList = mAppList;
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewHolder cvh;
        if (convertView == null) {
            cvh = new CustomViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_custom_btn, null);
            cvh.imgLogo = (ImageView) convertView.findViewById(R.id.img_item_edit);
            cvh.txtName = (TextView) convertView.findViewById(R.id.txt_item_edit);
            cvh.btnClick = (Button) convertView.findViewById(R.id.btn_item_click);
            cvh.btnClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onclick(v.getTag());
                    }
                }
            });
            convertView.setTag(cvh);
        } else {
            cvh = (CustomViewHolder) convertView.getTag();
        }
        ApplicationInfo item = (ApplicationInfo) this.getItem(position);
        cvh.txtName.setText(item.loadLabel(context.getPackageManager()));
        cvh.imgLogo.setImageDrawable(item.loadIcon(context.getPackageManager()));
        cvh.btnClick.setText(position + "");
        cvh.btnClick.setTag(position);
        return convertView;
    }

    class CustomViewHolder {
        public ImageView imgLogo;
        public TextView txtName;
        public Button btnClick;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onclick(Object o);
    }
}
