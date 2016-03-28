package com.example.storymania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StoryDetails extends AppCompatActivity {

    private Story story;
    private Author author;
    private TextView title,desc,like_com, about_author;
    private Button follow_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_details);
        title = (TextView)findViewById(R.id.story_title);
        desc = (TextView)findViewById(R.id.story_desc);
        like_com = (TextView)findViewById(R.id.likes_com);
        about_author = (TextView)findViewById(R.id.about);
        AppConstants.isFollowClicked = false;
        Intent intent = getIntent();
        story = (Story) intent.getSerializableExtra("StoryDetails");
        author = (Author) intent.getSerializableExtra("AuthorDetails");

        title.setText(story.getTitle());
        desc.setText(story.getDescription());
        like_com.setText("Likes: "+story.getLikes_count()+"    Comments: "+story.getComment_count());
        about_author.setText(author.getUsername()+"\n"+author.getAbout()+"\nTwitter : "+author.getHandle()+"\nFollowers : "+author.getFollowers()+"\nFollowing : "+author.getFollowing());


        follow_button = (Button)findViewById(R.id.follow_button);
        follow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(author.is_following()){
                    Toast.makeText(StoryDetails.this,"Already following",Toast.LENGTH_LONG).show();
                }else if(!AppConstants.isFollowClicked){
                    AppConstants.isFollowClicked = true;
                    author.setFollowers(author.getFollowers() + 1);
                    about_author.setText(author.getUsername() + "\n" + author.getAbout() + "\nTwitter : " + author.getHandle() + "\nFollowers : " + author.getFollowers() + "\nFollowing : " + author.getFollowing());
                }else{
                    Toast.makeText(StoryDetails.this,"Already following",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
