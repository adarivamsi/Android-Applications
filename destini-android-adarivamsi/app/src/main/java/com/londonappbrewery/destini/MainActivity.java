package com.londonappbrewery.destini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.button;
import static com.londonappbrewery.destini.R.string.T1_Story;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    TextView mstorytext_view;
    Button button_bottom, button_top;
    int mIndex = 0;
    int b1Index = 0;
    int b2Index = 0;

    private int text, text1;

    private NewStory[] mNewStories = new NewStory[]{
            new NewStory(T1_Story),
            new NewStory(R.string.T2_Story),
            new NewStory(R.string.T3_Story),
            new NewStory(R.string.T4_End),
            new NewStory(R.string.T5_End),
            new NewStory(R.string.T6_End),

    };

    private NewButton[] mNewButtons = new NewButton[]{
            new NewButton(R.string.T1_Ans1),
            new NewButton(R.string.T2_Ans1),
            new NewButton(R.string.T3_Ans1),

    };

    private NewButton2[] mNewButton2s = new NewButton2[]{
            new NewButton2(R.string.T1_Ans2),
            new NewButton2(R.string.T2_Ans2),
            new NewButton2(R.string.T3_Ans2),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //For screen rotation go to manifest.xml file there in activity tag we have the attribute
        // android:configchanges=".." is used to handle the configuration and screen rotation


        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        button_top = (Button) findViewById(R.id.buttonTop);
        button_bottom = (Button) findViewById(R.id.buttonBottom);
        mstorytext_view = (TextView) findViewById(R.id.storyTextView);



        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:
        button_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getApplicationContext(), "Button1 clicked", Toast.LENGTH_SHORT).show();
                update();


            }

        });



        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        button_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Button2 clicked", Toast.LENGTH_SHORT).show();
                update1();


            }
        });


    }

    private void update() {

        if (b1Index == 2 && b2Index == 2) {
            mIndex = 5;
            text = mNewStories[mIndex].getMstory();
            mstorytext_view.setText(text);
            button_top.setVisibility(View.GONE);
            button_bottom.setVisibility(View.GONE);
        }

        if(b1Index==0&&b2Index==0) {
            b1Index = 2;
            b2Index=2;

            text = mNewButtons[b1Index].getMoption();
            text1 = mNewButton2s[b2Index].getMoption();
            button_top.setText(text);
            button_bottom.setText(text1);

        }

        if(b1Index==1&&b2Index==1){
            mIndex=2;
            b1Index=2;
            b2Index=2;
            text = mNewStories[mIndex].getMstory();
            mstorytext_view.setText(text);
            text = mNewButtons[mIndex].getMoption();
            button_top.setText(text);
            text1 = mNewButton2s[mIndex].getMoption();
            button_bottom.setText(text1);


        }
    }
    private void update1() {

        if(b1Index==1&&b2Index==1){
            mIndex=3;
            text=mNewStories[mIndex].getMstory();
            mstorytext_view.setText(text);
            button_top.setVisibility(View.GONE);
            button_bottom.setVisibility(View.GONE);
        }

        if (b1Index == 0 && b2Index == 0) {

            b1Index = 1;
            b2Index = 1;
            text = mNewButtons[b1Index].getMoption();
            text1 = mNewButton2s[b2Index].getMoption();
            button_top.setText(text);
            button_bottom.setText(text1);

        }
        if (b1Index == 2 && b2Index == 2) {

            mIndex = 4;
            text = mNewStories[mIndex].getMstory();
            mstorytext_view.setText(text);
            button_top.setVisibility(View.GONE);
            button_bottom.setVisibility(View.GONE);

        }


    }
}
