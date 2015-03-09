package cn.steve.testpinnedlistview.adapter;



import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import cn.steve.testpinnedlistview.R;
import cn.steve.testpinnedlistview.model.PinnedItem;
import cn.steve.testpinnedlistview.view.PinnedSectionListView.PinnedSectionListAdapter;

/**
 * 基本的类
 * @author Steve
 *
 */
public abstract class PinnedBaseAdapter extends ArrayAdapter<PinnedItem>
    implements PinnedSectionListAdapter {
  
  private LayoutInflater mInflater;

  static final int[] DEFAULT_COLORS = new int[] {R.color.green_light, R.color.orange_light,R.color.blue_light, R.color.red_light};

  //section的布局
  public abstract int getSectionLayout();

  //item的布局
  public abstract int getItemLayout();

  /**
   * 生成section之前调用
   * 
   * @param sectionsNumber section的个数
   */
  public void prepareSections(int sectionsNumber) {
    System.out.println("preparesections");
  }

  /**
   * 增加section的时候
   * 
   * @param section 增加的对象
   * @param sectionPosition 添加到的位置
   */
  public void onSectionAdded(PinnedItem section, int sectionPosition) {
    System.out.println("onSectionAdded->>>" + sectionPosition);
  }


  public PinnedBaseAdapter(Context context, int resource) {
    super(context, resource);
    mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  public PinnedBaseAdapter(Context context, int resource, int textViewResourceId,
      List<PinnedItem> objects) {
    super(context, resource, textViewResourceId, objects);
    mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

  }

  public PinnedBaseAdapter(Context context, int resource, int textViewResourceId,
      PinnedItem[] objects) {
    super(context, resource, textViewResourceId, objects);
    mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

  }


  public PinnedBaseAdapter(Context context, int resource, int textViewResourceId) {
    super(context, resource, textViewResourceId);
    mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

  }

  public PinnedBaseAdapter(Context context, int resource, List<PinnedItem> objects) {
    super(context, resource, objects);
    mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

  }

  public PinnedBaseAdapter(Context context, int resource, PinnedItem[] objects) {
    super(context, resource, objects);
    mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

  }


  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view;
    PinnedItem item = getItem(position);
    if (convertView == null) {
      // section类型
      if (item.type == PinnedItem.SECTION) {
        view = mInflater.inflate(getSectionLayout(), parent, false);
        //set the section's background
        view.setBackgroundColor(parent.getResources().getColor(DEFAULT_COLORS[item.sectionPosition % DEFAULT_COLORS.length]));
      } else {
        view = mInflater.inflate(getItemLayout(), parent, false);
      }
    } else {
      view = convertView;
      if (item.type == PinnedItem.SECTION) {
        //set the section's background
        view.setBackgroundColor(parent.getResources().getColor(DEFAULT_COLORS[item.sectionPosition % DEFAULT_COLORS.length]));
      }
    }
    return view;
  }

  @SuppressLint("NewApi")
  public void setDatas(ArrayList<PinnedItem> datas) {
    addAll(datas);
  }

  @Override
  public int getViewTypeCount() {
    return 2;
  }

  @Override
  public int getItemViewType(int position) {
    return getItem(position).type;
  }

  @Override
  public PinnedItem getItem(int position) {
    return super.getItem(position);
  }

  @Override
  public boolean isItemViewTypePinned(int viewType) {
    return viewType == PinnedItem.SECTION;
  }
}
