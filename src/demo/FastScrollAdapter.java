package demo;

import cn.steve.testpinnedlistview.model.PinnedItem;
import android.content.Context;
import android.widget.SectionIndexer;

/**
 * 携带快速滑动
 * @author Steve
 *
 */
public class FastScrollAdapter extends PinnedSimpleAdapter implements SectionIndexer {

  private PinnedItem[] sections;

  public FastScrollAdapter(Context context, int resource, int textViewResourceId) {
    super(context, resource, textViewResourceId);
  }

  @Override
  public void prepareSections(int sectionsNumber) {
    sections = new PinnedItem[sectionsNumber];
  }

  @Override
  public void onSectionAdded(PinnedItem section, int sectionPosition) {
    sections[sectionPosition] = section;
  }

  @Override
  public PinnedItem[] getSections() {
    return sections;
  }

  //获得section的position
  @Override
  public int getPositionForSection(int section) {
    if (section >= sections.length) {
      section = sections.length - 1;
    }
    return sections[section].listPosition;
  }

  //获得position位置的section位置
  @Override
  public int getSectionForPosition(int position) {
    if (position >= getCount()) {
      position = getCount() - 1;
    }
    return getItem(position).sectionPosition;
  }

}
