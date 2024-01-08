
package com.tumuyan.fixedplay.Beta;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tumuyan.fixedplay.App.Item;
//import com.tumuyan.fixedplay.R;
import AnyLauncher.R;
import com.tumuyan.fixedplay.SettingActivity;

import java.util.List;

import static android.content.Context.MODE_MULTI_PROCESS;


/**
 * Created by baniel on 1/19/17.
 */
public class mixAdapter extends ArrayAdapter<Item> {
    private int layoutId;
    private String mode="r2";
    private String uri="";

    public mixAdapter(Context context, int layoutId, List<Item> list) {
        super(context, layoutId, list);
        this.layoutId = layoutId;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

   // @NonNull
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final Item item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        final String packageName=item.getPackageName();
        final String className=item.getClassName();
        final String Name=item.getName();

        String  _class, _package, _name;
        if(className.length()<1) {
            _name=Name;
            _package=packageName;
        }else    if(className.contains(packageName)){
            _class=className.replace(packageName,"");
            _package=packageName;
            _name=Name+"("+_class+")";
        }else {
            _name=Name;
            _package=packageName+"\n"+className;
        }

        ((ImageView) view.findViewById(R.id.item_img)).setImageDrawable(item.getAppIcon());
        ((TextView) view.findViewById(R.id.item_text)).setText(_name);
        ((TextView)view.findViewById(R.id.item_packageName)).setText(_package);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemDeleteListener.onDeleteClick(position);
            }
        });



        return view;
    }
    /**
     * 删除按钮的监听接口
     */
    public interface onItemDeleteListener {
        void onDeleteClick(int i);
    }

    private onItemDeleteListener mOnItemDeleteListener;

    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }
}


