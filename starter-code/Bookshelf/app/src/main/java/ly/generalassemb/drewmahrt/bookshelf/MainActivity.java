package ly.generalassemb.drewmahrt.bookshelf;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BaseAdapter mAdapter;
    ListView lView;
    List<Book> mBookList = new ArrayList<>();
    //String mAuthor = "Author ";
    //String mTitle = "Title ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lView = (ListView) findViewById(R.id.listview);

        //Use helper method to add books to the list
        mBookList = generateBooks();


        //Below is a partially complete example for one Adapter
        //anonymous inner class of abstract class BaseAdapter.
        mAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mBookList.size();
            }

            @Override
            public Object getItem(int position) {
                return mBookList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View vInflate = convertView;
                if(vInflate == null){
                    LayoutInflater inflater = (LayoutInflater) MainActivity
                            .this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    vInflate = inflater.inflate(android.R.layout.simple_list_item_1, null);
                }

                TextView tv = (TextView) vInflate.findViewById(android.R.id.text1);

                //tv.setText("Title:  " + mBookList.get(position).getTitle() + "\nAuthor:  " + mBookList.get(position).getAuthor());
                //tv.setText(mTitle + mBookList.get(position).getTitle() + "\n"+mAuthor + mBookList.get(position).getAuthor());
                //Above two work, trying with strings.xml * ids.
                //tv.setText(R.string.mTitle + mBookList.get(position).getAuthor() +"\n" + R.string.mAuthor + mBookList.get(position).getAuthor());
                Resources res = getResources();
                String text = String.format(res.getString(R.string.printout), mBookList.get(position).getTitle(), mBookList.get(position).getAuthor());
                tv.setText(text);

                //TODO: Update the view
                return vInflate;
            }
        };

        //TODO: Set the ListView's adapter
        lView.setAdapter(mAdapter);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.RED);
                mAdapter.notifyDataSetChanged();
            }
        });

    ;}

    //Method to generate a list of Books
    private List<Book> generateBooks(){
        List<Book> books = new ArrayList<>();

        books.add(new Book("Apples Book","Brad"));
        books.add(new Book("Cats Book","Ryan"));
        books.add(new Book("Eagles Book","Kate"));
        books.add(new Book("Strawberries Cathy","Brad"));
        books.add(new Book("Dogs Book","Tom"));
        books.add(new Book("Ants Book","Zane"));

        return books;
    }
}
