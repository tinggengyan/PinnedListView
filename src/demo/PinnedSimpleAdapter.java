package demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.steve.testpinnedlistview.R;
import cn.steve.testpinnedlistview.adapter.PinnedBaseAdapter;
import cn.steve.testpinnedlistview.model.PinnedItem;

public class PinnedSimpleAdapter extends PinnedBaseAdapter {

  public PinnedSimpleAdapter(Context context, int resource, int textViewResourceId) {
    super(context, resource, textViewResourceId);
  }

  public PinnedSimpleAdapter(Context context) {
    this(context, 0, 0);
  }

  @Override
  public int getSectionLayout() {
    return R.layout.section_layout;
  }

  @Override
  public int getItemLayout() {
    return R.layout.item_layout;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view = super.getView(position, convertView, parent);
    if (getItem(position).type == PinnedItem.SECTION) {
      ((TextView) view.findViewById(R.id.name1)).setText(String.valueOf(getItem(position).text));
      ((TextView) view.findViewById(R.id.name2)).setText(String.valueOf(getItem(position).getItemNums()));
    } else {
      ((TextView) view.findViewById(R.id.content)).setText(String.valueOf(position));
    }
    return view;
  }
}
