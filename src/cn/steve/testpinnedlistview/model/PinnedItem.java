package cn.steve.testpinnedlistview.model;

/**
 * @author Steve
 *
 */
public class PinnedItem {
  // Type
  public static final int ITEM = 0;
  public static final int SECTION = 1;

  
  public final int type;
  public final String text;

  public int sectionPosition;
  public int listPosition;

  public int itemNums;
  
  public int getItemNums() {
    return itemNums;
  }


  public void setItemNums(int itemNums) {
    this.itemNums = itemNums;
  }


  public PinnedItem(int type, String text) {
    this.type = type;
    this.text = text;
  }
  

  @Override
  public String toString() {
    return text;
  }

}
