package info.androidhive.jsonparsing;

import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import info.androidhive.jsonparsing.model.Contact;
import info.androidhive.jsonparsing.model.Phone;

public class ExpandCustomAdapter extends BaseExpandableListAdapter {

    private Activity context;
    private Map<String, List<Phone>> ParentListItems;
    private List<Contact> Items;

    public ExpandCustomAdapter(Activity context, List<Contact> Items,
                             Map<String, List<Phone>> ParentListItems) {
        this.context = context;
        this.ParentListItems = ParentListItems;
        this.Items = Items;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return ParentListItems.get(Items.get(groupPosition).getName()).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View ListView, ViewGroup parent) {

//        final String childText = (String) getChild(groupPosition, childPosition);

       Phone phone = (Phone)  getChild(groupPosition,childPosition);

        //final CustomerOrderModel laptop = (CustomerOrderModel) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();

        if (ListView == null) {
            ListView = inflater.inflate(R.layout.child_list_item, null);
        }

        TextView item = (TextView) ListView.findViewById(R.id.textView1);

        item.setText(phone.getHome());
        return ListView;
    }

    public int getChildrenCount(int groupPosition) {
        return ParentListItems.get(Items.get(groupPosition).getName()).size();
    }

    public Object getGroup(int groupPosition) {
        return Items.get(groupPosition);
    }

    public int getGroupCount() {
        return Items.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View ListView, ViewGroup parent) {
      //  String headerTitle = (String) getGroup(groupPosition);

        Contact contact =(Contact) getGroup(groupPosition);


        if (ListView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ListView = infalInflater.inflate(R.layout.parent_list_item,null);
        }
        TextView item = (TextView) ListView.findViewById(R.id.textView1);
        item.setText(contact.getName());
        return ListView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}