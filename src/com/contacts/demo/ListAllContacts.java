package com.contacts.demo;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListAllContacts extends Activity{
	private List<Contacts> lists;
	private LayoutInflater inflater;
	private ListView listView;
   MyAdapter adapter;
   EditText editText;
   View view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a);
		listView=(ListView)findViewById(R.id.listview);
		listView.setDividerHeight(0);
		inflater=getLayoutInflater();
		view=inflater.inflate(R.layout.c, null);
		editText=(EditText) view.findViewById(R.id.edit);
		lists=ContactsUtil.testReadAll(this);
		registerForContextMenu(listView);
		adapter=new MyAdapter(lists);
		listView.setAdapter(adapter);
		
		
	}
	
	
	class MyAdapter extends BaseAdapter{
		private List<Contacts> list;
 public MyAdapter(List<Contacts> list){
	 setList(list);
 }      
 public void setList(List<Contacts> list){
	 
	 this.list=list;
	 
 }
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if(convertView==null){
				convertView=inflater.inflate(R.layout.b, null);
			}
			TextView textView=(TextView) convertView.findViewById(R.id.text);
			textView.setText("姓名:"+list.get(position).getName()+"\n手机:"+list.get(position).getPhone());
			return convertView;
		}
		
	}


	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		menu.add(0, 0, 0, "删除联系人");
		menu.add(0, 1, 1, "修改联系人");
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}


	@Override
	public boolean onContextItemSelected(MenuItem item) {
		 //获取当前被选择的菜单项的信息      
		final AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
		
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 0:
			try {
				ContactsUtil.testDelete(ListAllContacts.this,lists.get(info.position).getName());
				lists.remove(info.position);
				//adapter.setList(lists);
				adapter.notifyDataSetChanged();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			break;
          case 1:
        	  new AlertDialog.Builder(ListAllContacts.this).setTitle("请输入电话号码").setView(view).setPositiveButton("确定",new DialogInterface.OnClickListener() {
      			
      			public void onClick(DialogInterface dialog, int which) {
      				// TODO Auto-generated method stub
      			try {
					ContactsUtil.testUpdate(ListAllContacts.this,editText.getText().toString(),lists.get(info.position).getId());
					lists.get(info.position).setPhone(editText.getText().toString());
					
					adapter.setList(lists);
					adapter.notifyDataSetChanged();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(ListAllContacts.this,e.toString(),2000).show();
				}
      			}
      		}).create().show();
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

}
