package com.example.storymania;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Author> authorArrayList;
    private ArrayList<Story> storyArrayList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String j = loadJSONFromAsset();
        authorArrayList = new ArrayList<>();
        storyArrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(j);
            Author author;
            Story story;
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(i<2) {
                    author = new Author();
                    author.setAbout(jsonObject.getString("about"));
                    author.setCreatedOn(jsonObject.getLong("createdOn"));
                    author.setFollowers(jsonObject.getLong("followers"));
                    author.setFollowing(jsonObject.getLong("following"));
                    author.setHandle(jsonObject.getString("handle"));
                    author.setId(jsonObject.getString("id"));
                    author.setImage(jsonObject.getString("image"));
                    author.setIs_following(jsonObject.getBoolean("is_following"));
                    author.setUrl(jsonObject.getString("url"));
                    author.setUsername(jsonObject.getString("username"));
                    authorArrayList.add(author);
                }else{
                    story = new Story();
                    story.setUrl(jsonObject.getString("url"));
                    story.setId(jsonObject.getString("id"));
                    story.setComment_count(jsonObject.getLong("comment_count"));
                    story.setDb(jsonObject.getString("db"));
                    story.setDescription(jsonObject.getString("description"));
                    story.setLike_flag(jsonObject.getBoolean("like_flag"));
                    story.setLikes_count(jsonObject.getLong("likes_count"));
                    story.setSi(jsonObject.getString("si"));
                    story.setTitle(jsonObject.getString("title"));
                    story.setType(jsonObject.getString("type"));
                    story.setVerb(jsonObject.getString("verb"));
                    storyArrayList.add(story);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        myAdapter = new MyAdapter(MainActivity.this,storyArrayList,authorArrayList);
        mRecyclerView.setAdapter(myAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(AppConstants.isFollowClicked && AppConstants.lastIndex>=0){
            authorArrayList.get(AppConstants.lastIndex).setFollowers(authorArrayList.get(AppConstants.lastIndex).getFollowers()+1);
            authorArrayList.get(AppConstants.lastIndex).setIs_following(true);
            AppConstants.isFollowClicked=false;
            AppConstants.lastIndex=-1;
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("storylistjson.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        //Log.d("JSON",json);
        return json;

    }
}
