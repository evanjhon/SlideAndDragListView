package smalltown.com.slideanddraglistview.comtance;

/**
 * create by cxz,2016 05 06
 */
public class QQ {
    //名字
    private final String name;
    //内容
    private final String content;
    //时间
    private final String time;
    //头像
    private final int drawableRes;
    //是否是QQ群
    private final boolean isQun;
    //群人数
    private final int qunNumber;

    public QQ(String name, String content, String time, int drawableRes) {
        this(name, content, time, drawableRes, false, 1);
    }

    public QQ(String name, String content, String time, int drawableRes, boolean isQun, int qunNumber) {
        this.name = name;
        this.content = content;
        this.time = time;
        this.drawableRes = drawableRes;
        this.isQun = isQun;
        this.qunNumber = qunNumber;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public int getDrawableRes() {
        return drawableRes;
    }

    public boolean isQun() {
        return isQun;
    }

    public int getQunNumber() {
        return qunNumber;
    }
}
