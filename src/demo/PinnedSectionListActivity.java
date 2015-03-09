package demo;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.steve.testpinnedlistview.R;
import cn.steve.testpinnedlistview.model.PinnedItem;
import cn.steve.testpinnedlistview.view.PinnedSectionListView;

public class PinnedSectionListActivity extends ListActivity implements OnClickListener {

  private boolean isFastScroll;
  private boolean addPadding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    isFastScroll=addPadding=false;//先测试
    
    initializeHeaderAndFooter();
    initializeAdapter();
    initializePadding();
  }

  private void initializePadding() {
    float density = getResources().getDisplayMetrics().density;
    int padding = addPadding ? (int) (16 * density) : 0;
    getListView().setPadding(padding, padding, padding, padding);
  }

  private void initializeHeaderAndFooter() {
    PinnedSectionListView list = (PinnedSectionListView) getListView();
    LayoutInflater inflater = LayoutInflater.from(this);
    TextView header1 =(TextView) inflater.inflate(android.R.layout.simple_list_item_1, list, false);
    header1.setText("First header");
    list.addHeaderView(header1);
    TextView header2 = (TextView) inflater.inflate(android.R.layout.simple_list_item_1, list, false);
    header2.setText("Second header");
    list.addHeaderView(header2);
    TextView footer = (TextView) inflater.inflate(android.R.layout.simple_list_item_1, list, false);
    footer.setText("Single footer");
    list.addFooterView(footer);
  }

  @SuppressLint("NewApi")
  private void initializeAdapter() {
    PinnedSectionListView listView = (PinnedSectionListView) getListView();
    listView.setFastScrollEnabled(isFastScroll);
    if (isFastScroll) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        listView.setFastScrollAlwaysVisible(true);
      }
      FastScrollAdapter fastadapter =new FastScrollAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1);
      Utils.generateDataset(fastadapter, 'A', 'Z', true);
      setListAdapter(fastadapter);
    } else {
      PinnedSimpleAdapter simpleadapter =new PinnedSimpleAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1);
      Utils.generateDataset(simpleadapter, 'A', 'Z', true);
      listView.setAdapter(simpleadapter);
    }
  }


  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    PinnedItem item = (PinnedItem) getListView().getAdapter().getItem(position);
    if (item != null) {
      Toast.makeText(this, "Item " + position + ": " + item.text, Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(this, "Item " + position, Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  public void onClick(View v) {
    Toast.makeText(this, "Item: " + v.getTag(), Toast.LENGTH_SHORT).show();
  }
}
