package qorda_projects.binderfinder.controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import qorda_projects.binderfinder.R;

/**
 * Created by sorengoard on 07/04/2017.
 */

public class GridviewAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mBinderStoreNames;
    private String[] mStoreSpecificSizes;
    private String[] mUrls;

    public GridviewAdapter(Context context, String[] binderStoreNames, String[] storeSpecificSizes, String[] urls) {
        mContext = context;
        this.mBinderStoreNames = binderStoreNames;
        this.mStoreSpecificSizes = storeSpecificSizes;
        this.mUrls = urls;
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
    public int getCount() {
        return mBinderStoreNames.length;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View gridView;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            gridView = new View(mContext);
            gridView = inflater.inflate(R.layout.grid_list_item, null);
            TextView binderStoreName = (TextView) gridView.findViewById(R.id.binder_shop_name);
            TextView storeSpecificSize = (TextView) gridView.findViewById(R.id.binder_shop_size);
            binderStoreName.setText(mBinderStoreNames[i]);
            storeSpecificSize.setText(mStoreSpecificSizes[i]);
            ImageButton shopButton = (ImageButton) gridView.findViewById(R.id.binder_shop_link_button);
            final String url = mUrls[i];
            shopButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent watchIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    mContext.startActivity(watchIntent);

                }
            });
        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }
}
