package demo;

import java.util.Locale;

import cn.steve.testpinnedlistview.model.PinnedItem;

/**
 * 模拟数据
 * @author steve.yan
 *
 */

public class Utils {
  public static void generateDataset(PinnedSimpleAdapter madapter, char from, char to, boolean clear) {
    if (clear) madapter.clear();
    // total numbers
    final int sectionsNumber = to - from + 1;
    //
    madapter.prepareSections(sectionsNumber);

    int sectionPosition = 0, listPosition = 0;
    for (char i = 0; i < sectionsNumber; i++) {
      PinnedItem section = new PinnedItem(PinnedItem.SECTION, String.valueOf((char) ('A' + i)));
      section.sectionPosition = sectionPosition;
      section.listPosition = listPosition++;
      madapter.onSectionAdded(section, sectionPosition);
      final int itemsNumber =(int) Math.abs((Math.cos(2f * Math.PI / 3f * sectionsNumber / (i + 1f)) * 25f));
      section.setItemNums(itemsNumber);
      madapter.add(section);
      for (int j = 0; j < itemsNumber; j++) {
        PinnedItem item = new PinnedItem(PinnedItem.ITEM, section.text.toUpperCase(Locale.ENGLISH) + " - " + j);
        item.sectionPosition = sectionPosition;
        item.listPosition = listPosition++;
        madapter.add(item);
      }
      sectionPosition++;
    }
  }
}
