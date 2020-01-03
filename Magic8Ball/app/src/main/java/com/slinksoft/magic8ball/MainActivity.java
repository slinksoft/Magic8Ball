package com.slinksoft.magic8ball;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    EditText input;
    TextView think;
    ImageView eightBall;
    TextView answer;
    Button ask;
    CountDownTimer time;
    Random rand;
    TextView versionD;
    String[] responses = new String[] {"It is certain.", "It is decidedly so.", "Without a doubt.",
    "Yes - definitely.", "You may rely on it.", "As I see it, yes.", "Most likely.", "Outlook good.",
    "Yes.", "Signs point to yes.", "Reply hazy, try again.", "Ask again later.", "Better not tell you now.",
    "Cannot predict now.", "Concentrate and ask again.", "Don't count on it.", "My reply is no.",
    "My sources say no.", "Outlook not so good.", "Very doubtful.", "May the force be with you."};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int version = 1;
        int revision = 0;
        String complete = version + "." + revision;
        input = findViewById(R.id.enterQuestion);
        think = findViewById(R.id.thinkingLabel);
        eightBall = findViewById(R.id.eightBallImage);
        ask = findViewById(R.id.askButton);
        answer = findViewById(R.id.theAnswer);
        versionD = findViewById(R.id.versionDisplay);
        rand = new Random();
        setTitle("Magic 8 Ball: By SlinkSoft");

        versionD.setText("Version: " + complete);

    }

    public void processQuestion(View v) {
        if (input.getText().toString().equals("")) {
            AlertDialog error = new AlertDialog.Builder(MainActivity.this).create();
            error.setTitle("Error");
            error.setMessage("Enter a question to ask the Magic 8 Ball!");
            error.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            error.show();
        } else {
            eightBall.setImageResource(R.drawable.eightballtop);
            ask.setEnabled(false);
            input.setEnabled(false);
            think.setVisibility(View.VISIBLE);
            answer.setVisibility(View.GONE);
            time = new CountDownTimer(3000, 100) {
                float x = eightBall.getX();
                float y = eightBall.getY();
                int dir = 0; // 0 = middle 1 = left 2 = right

                public void onTick(long l) {
                    System.out.println("timer executed");

                    switch (dir) {
                        case 0: {
                            eightBall.setX(eightBall.getX() - 50);
                            dir = 1;
                            break;
                        }
                        case 1: {
                            eightBall.setX(eightBall.getX() + 100);
                            dir = 2;
                            System.out.println("direction :" + dir);
                            break;
                        }
                        case 2: {
                            eightBall.setX(eightBall.getX() - 100);
                            dir = 1;
                            break;
                        }

                    }
                }

                public void onFinish() {
                    dir = 0;
                    eightBall.setX(x);
                    eightBall.setY(y);
                    eightBall.setImageResource(R.drawable.eightballbottom);
                    input.setEnabled(true);
                    think.setVisibility(View.GONE);
                    ask.setEnabled(true);
                    answer.setVisibility(View.VISIBLE);

                    if (input.getText().toString().contains("Are you default"))
                        answer.setText("Hi. I am Default.");
                    else if (input.getText().toString().contains("Are you my father"))
                        answer.setText("I am your father.");
                    else if (input.getText().toString().contains("Morty"))
                        answer.setText("Aw, geez Rick.");
                    else if (input.getText().toString().contains("Rick"))
                        answer.setText("WUBBA LUBBA DUB DUB!");
                    else if (input.getText().toString().contains("Slink"))
                        answer.setText("Ah yes. Slink is the best.");
                    else if (input.getText().toString().contains("VOTD"))
                        answer.setText("2nd integration of VOTD? Possibly soon??");
                    else if (input.getText().toString().equals("Valley Of The Damned"))
                        answer.setText("2nd integration of VOTD? Possibly soon??");
                    else if (input.getText().toString().contains("Hi"))
                        answer.setText("Bye.");
                    else if (input.getText().toString().contains("Hello"))
                        answer.setText("No.");
                    // maybe more in the future
                    else {
                        int choice = rand.nextInt(21);
                        answer.setText(responses[choice]);
                    }
                }
            }.start();
        }
    }


        // Function for credits button with use of Alert Dialog
        public void credits(View v)
        {
            final AlertDialog credit = new AlertDialog.Builder(MainActivity.this).create();
            credit.setTitle("Credits");
            credit.setMessage("Developed By: \n- Slink (Dan) \n- tassel78 For  Magic  8 Ball Renders\n\nVisit:\nhttps://www.realslinksoft.wixsite.com/slink-soft-portfolio" +
                    "\nand\nhttp://www.YouTube.Com/ReTrOSlink\nThank you for using this app!");
            credit.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            credit.setButton(AlertDialog.BUTTON_POSITIVE, "Visit SlinkSoft",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://realslinksoft.wixsite.com/slink-soft-portfolio"));
                            startActivity(browserIntent);
                            dialogInterface.dismiss();
                        }
                    });

            credit.setButton(AlertDialog.BUTTON_NEGATIVE, "Visit Adobe Stock For Renders",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://stock.adobe.com/193483387?as_campaign=TinEye&as_content=tineye_match&epi1=193483387&tduid=79bfc1b6963f04c13c37b52fb7b5a24a&as_channel=affiliate&as_campclass=redirect&as_source=arvato"));
                            startActivity(browserIntent);
                            dialogInterface.dismiss();
                        }
                    });
            credit.show();
        }

}


