package smalltown.com.slideanddraglistview.activity;

import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import com.yydcdut.sdlv.DragListView;
import com.yydcdut.sdlv.Menu;
import com.yydcdut.sdlv.MenuItem;
import com.yydcdut.sdlv.SlideAndDragListView;

import java.util.List;

import smalltown.com.slideanddraglistview.R;
import smalltown.com.slideanddraglistview.adapter.SimpleAdapter;
import smalltown.com.slideanddraglistview.utils.Utils;

/**
 * Created by cxz on 16/5/6
 */
public class SimpleActivity extends AppCompatActivity implements SlideAndDragListView.OnListItemLongClickListener,
        SlideAndDragListView.OnDragListener, SlideAndDragListView.OnSlideListener,
        SlideAndDragListView.OnListItemClickListener, SlideAndDragListView.OnMenuItemClickListener,
        SlideAndDragListView.OnItemDeleteListener, SlideAndDragListView.OnListScrollListener {
    private static final String TAG = SimpleActivity.class.getSimpleName();

    private Menu mMenu;
    private List<ApplicationInfo> mAppList;
    private SlideAndDragListView mListView;
    private SimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdlv);
        initData();
        initMenu();
        initUiAndListener();
    }

    public void initData() {
        mAppList = getPackageManager().getInstalledApplications(0);
    }

    /**
     * 定义ListItem滑动的样式
     * <p>
     * MenuItem.DIRECTION_RIGHT 默认等于0，向右滑动
     * MenuItem.DIRECTION_RIGHT ==-1的时候，向左滑动
     */
    public void initMenu() {
        mMenu = new Menu(true, true);
        //向右滑动时显示出来的第一个样式
        mMenu.addItem(new MenuItem.Builder().setWidth((int) getResources().getDimension(R.dimen.slv_item_bg_btn_width) * 2)
                .setBackground(Utils.getDrawable(this, R.drawable.btn_left0))
                .setText("左1")
                .setTextColor(Color.GRAY)
                .setTextSize((int) getResources().getDimension(R.dimen.txt_size))
                .build());
        //向右滑动时显示出来的第二个样式
        mMenu.addItem(new MenuItem.Builder().setWidth((int) getResources().getDimension(R.dimen.slv_item_bg_btn_width))
                .setBackground(Utils.getDrawable(this, R.drawable.btn_left1))
                .setText("左2")
                .setTextColor(Color.BLACK)
                .setTextSize((int) getResources().getDimension(R.dimen.txt_size))
                .build());
        //向左滑动显示出来的第一个样式
        mMenu.addItem(new MenuItem.Builder().setWidth((int) getResources().getDimension(R.dimen.slv_item_bg_btn_width) + 30)
                .setBackground(Utils.getDrawable(this, R.drawable.btn_right0))
                .setText("右1")
                .setDirection(MenuItem.DIRECTION_RIGHT)
                .setTextColor(Color.BLACK)
                .setTextSize((int) getResources().getDimension(R.dimen.txt_size))
                .build());
        //向左滑动显示出来的第二个
        mMenu.addItem(new MenuItem.Builder().setWidth((int) getResources().getDimension(R.dimen.slv_item_bg_btn_width_img))
                .setBackground(Utils.getDrawable(this, R.drawable.btn_right1))
                .setDirection(MenuItem.DIRECTION_RIGHT)
                .setIcon(getResources().getDrawable(R.drawable.ic_launcher))
                .build());
    }

    public void initUiAndListener() {
        mListView = (SlideAndDragListView) findViewById(R.id.lv_edit);
        mListView.setMenu(mMenu);
        mAdapter = new SimpleAdapter(this, mAppList);
        mListView.setAdapter(mAdapter);
        mListView.setOnListItemLongClickListener(this);
        mListView.setOnDragListener(this, mAppList);
        mListView.setOnListItemClickListener(this);
        mListView.setOnSlideListener(this);
        mListView.setOnMenuItemClickListener(this);
        mListView.setOnItemDeleteListener(this);
        mListView.setOnListScrollListener(this);
        mAdapter.setOnClickListener(new SimpleAdapter.OnClickListener() {
            @Override
            public void onclick(Object o) {
                if (o instanceof Integer) {
                    Toast.makeText(SimpleActivity.this, "button click-->" + ((Integer) o), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 条目的长按事件
     *
     * @param view
     * @param position
     */
    @Override
    public void onListItemLongClick(View view, int position) {
        Toast.makeText(SimpleActivity.this, "onItemLongClick   position--->" + position, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onListItemLongClick   " + position);
    }

    /**
     * 开始拖拽
     *
     * @param position 表示的是刚开始拖动的时候取的item在ListView中的位置。
     */
    @Override
    public void onDragViewStart(int position) {
        Toast.makeText(SimpleActivity.this, "onDragViewStart   position--->" + position, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onDragViewStart   " + position);
    }

    /**
     * 拖拽过程中
     *
     * @param position 表示的是当前拖动的item在ListView的位置，当处于拖动的时候这个函数是会一直回调的。
     */
    @Override
    public void onDragViewMoving(int position) {
//        Toast.makeText(DemoActivity.this, "onDragViewMoving   position--->" + position, Toast.LENGTH_SHORT).show();
        Log.i("yuyidong", "onDragViewMoving   " + position);
    }

    /**
     * 拖拽结束
     *
     * @param position 表示的是拖动的item最放到了ListView的哪个位置。
     */
    @Override
    public void onDragViewDown(int position) {
        Toast.makeText(SimpleActivity.this, "onDragViewDown   position--->" + position, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onDragViewDown   " + position);
    }

    /**
     * item的正常的点击事件
     *
     * @param v
     * @param position 表示点击的item的position
     */
    @Override
    public void onListItemClick(View v, int position) {
        Toast.makeText(SimpleActivity.this, "onItemClick   position--->" + position, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onListItemClick   " + position);
    }

    /**
     * 侧滑事件的监听，监听侧滑打开的时候
     *
     * @param view
     * @param parentView 父View
     * @param position   操作的当前的Item的位置
     * @param direction  滑动的方向，direction=1的时候表示从左往右滑动，当direction=-1的时候表示从右向左滑动
     */
    @Override
    public void onSlideOpen(View view, View parentView, int position, int direction) {
        Toast.makeText(SimpleActivity.this, "onSlideOpen   position--->" + position + "  direction--->" + direction, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onSlideOpen   " + position);
    }

    /**
     * 侧滑事件的监听 监听侧滑关闭的时候
     *
     * @param view
     * @param parentView 父View
     * @param position   操作的当前的Item的位置
     * @param direction  滑动的方向，direction=-1的时候表示从左往右滑动，当direction=1的时候表示从右向左滑动
     */
    @Override
    public void onSlideClose(View view, View parentView, int position, int direction) {
        Toast.makeText(SimpleActivity.this, "onSlideClose   position--->" + position + "  direction--->" + direction, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onSlideClose   " + position);
    }

    /**
     * 侧滑菜单弹出之后 弹出的侧滑菜单的监听事件
     *
     * @param v              操作的View
     * @param itemPosition   操作的Item所在的位置
     * @param buttonPosition 当前点击的Item中的侧滑菜单中所点击的菜单所在的位置
     * @param direction      此时侧滑菜单滑动的方向
     * @return
     */
    @Override
    public int onMenuItemClick(View v, int itemPosition, int buttonPosition, int direction) {
        Log.i(TAG, "onMenuItemClick   " + itemPosition + "   " + buttonPosition + "   " + direction);
        switch (direction) {
            //direction=1,从左向右滑动，操作左侧菜单
            case MenuItem.DIRECTION_LEFT:
                switch (buttonPosition) {
                    case 0:
                        //左侧从左往右第一个
                        return Menu.ITEM_NOTHING;
                    case 1:
                        //左侧从左往右第二个
                        return Menu.ITEM_SCROLL_BACK;
                }
                break;
            //direction=-1,从右向左滑动，操作右侧菜单
            case MenuItem.DIRECTION_RIGHT:
                switch (buttonPosition) {
                    //右侧从右往左第一个
                    case 0:
                        return Menu.ITEM_SCROLL_BACK;
                    //右侧从右往左第二个
                    case 1:
                        return Menu.ITEM_DELETE_FROM_BOTTOM_TO_TOP;
                }
        }
        return Menu.ITEM_NOTHING;
    }

    /**
     * 删除Item
     *
     * @param view
     * @param position 需要删除的Item对应的位置
     */
    @Override
    public void onItemDelete(View view, int position) {
        mAppList.remove(position - mListView.getHeaderViewsCount());
        mAdapter.notifyDataSetChanged();
    }

    /**
     * listView 的滚动事件的监听
     *
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case SlideAndDragListView.OnListScrollListener.SCROLL_STATE_IDLE:
                break;
            case SlideAndDragListView.OnListScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                break;
            case SlideAndDragListView.OnListScrollListener.SCROLL_STATE_FLING:
                break;
        }
    }

    /**
     * 滚动时一直回调，直到停止滚动时才停止回调。单击时回调一次。
     *
     * @param view
     * @param firstVisibleItem 当前能看见的第一个列表项ID（从0开始）
     * @param visibleItemCount 当前能看见的列表项个数（小半个也算）
     * @param totalItemCount 列表项共数
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    /**
     * 创建菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     *  菜单的选择
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            //条目是否可以拖拽
            case R.id.menu_drag:
                if (item.getTitle().toString().startsWith("Enable")) {
                    mListView.setOnDragListener(this, mAppList);
                    item.setTitle("Disable Drag");
                } else {
                    mListView.setOnDragListener(null, null);
                    item.setTitle("Enable Drag");
                }
                break;
            //条目是否可以点击
            case R.id.menu_item_click:
                if (item.getTitle().toString().startsWith("Enable")) {
                    mListView.setOnListItemClickListener(this);
                    item.setTitle("Disable Item Click");
                } else {
                    mListView.setOnListItemClickListener(null);
                    item.setTitle("Enable Item Click");
                }
                break;
            //条目是否可以长按
            case R.id.menu_item_long_click:
                if (item.getTitle().toString().startsWith("Enable")) {
                    mListView.setOnListItemLongClickListener(this);
                    item.setTitle("Disable Item Long Click");
                } else {
                    mListView.setOnListItemLongClickListener(null);
                    item.setTitle("Enable Item Long Click");
                }
                break;
            //关闭打开的SlidedMenu
            case R.id.menu_item_close_menu:
                mListView.closeSlidedItem();
                break;
        }
        return true;
    }

}
