package com.example.androidpractice03;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class ContentAdapter extends BaseAdapter implements View.OnClickListener {

    private static final String TAG = "ContentAdapter";
    private final List<Map<String, Object>> mContentList;
    private final LayoutInflater mInflater;   //LayoutInflater它主要是用于加载布局
    private final Callback mCallback;     //回调

    public interface Callback {
        public void click(View v);
    }

    public ContentAdapter(Context context, List<Map<String, Object>> contentList,
                          Callback callback) {
        mContentList = contentList;
        mInflater = LayoutInflater.from(context);
        mCallback = callback;
    }

    @Override
    public int getCount() {   //获取列表项项数
        Log.i(TAG, "getCount");
        return mContentList.size();
    }

    @Override
    public Object getItem(int position) {  //根据列表项位置索引获取列表项绑定的数据
        Log.i(TAG, "getItem");
        return mContentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.i(TAG, "getItemId");
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {  //把数据和列表项的控件进行绑定
        Log.i(TAG, "getView");
        ViewHolder holder = null;
        if (convertView == null) {  //如果列表项灭有初始化就进行初始化
            convertView = mInflater.inflate(R.layout.simple_item,null);
            holder = new ViewHolder();
            holder.img_head=(ImageView) convertView.findViewById(R.id.img_head);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            //holder.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
            holder.bt_show= (Button) convertView.findViewById(R.id.bt_show);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        int imgId=(int)mContentList.get(position).get("imgId");
        String name=(String) mContentList.get(position).get("name");
        //String desc=mContentList.get(position).get("desc").toString();

        holder.img_head.setImageResource(imgId);
        holder.tv_name.setText(name);
        //holder.tv_desc.setText(desc);

        holder.bt_show.setOnClickListener(this);
        holder.bt_show.setTag(position);
        return convertView; //返回一个列表项
    }

    public static class ViewHolder {  //列表项所有控件组成一个类
        public ImageView img_head;
        public TextView tv_name;
        //public TextView tv_desc;
        public Button bt_show;
    }

    //响应按钮点击事件,调用子定义接口，并传入View
    @Override
    public void onClick(View v) {
        mCallback.click(v);
    }
}

