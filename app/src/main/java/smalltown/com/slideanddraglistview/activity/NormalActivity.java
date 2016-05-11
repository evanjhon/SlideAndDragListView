package smalltown.com.slideanddraglistview.activity;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import smalltown.com.slideanddraglistview.R;
import smalltown.com.slideanddraglistview.adapter.NormalAdapter;

/**
 * Created by cxz on 16/5/6.
 */
public class NormalActivity extends AppCompatActivity {
    private static final String TAG = NormalActivity.class.getSimpleName();

    private List<ApplicationInfo> mAppList;
    private NormalAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        initView();

    }

    private void initView() {
        mAppList = getPackageManager().getInstalledApplications(0);
        final ListView listView = (ListView) findViewById(R.id.lv_normal);
        View header = LayoutInflater.from(this).inflate(R.layout.item_header_footer, null);
        View footer = LayoutInflater.from(this).inflate(R.layout.item_header_footer, null);
        footer.setBackgroundColor(0xff0000bb);
        listView.addHeaderView(header);
        listView.addFooterView(footer);
        mAdapter = new NormalAdapter(this, mAppList);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Log.i(TAG, "onItemClick   position--->" + position);
                Toast.makeText(NormalActivity.this, "onItemClick   position--->" + position, Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemLongClick   position--->" + position);
                Toast.makeText(NormalActivity.this, "onItemLongClick   position--->" + position, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mAdapter.setOnClickListener(new NormalAdapter.OnClickListener() {
            @Override
            public void onclick(Object o) {
                if (o instanceof Integer) {
                    Toast.makeText(NormalActivity.this, "button click-->" + ((Integer) o), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
