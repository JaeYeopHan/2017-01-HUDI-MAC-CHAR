package com.zimincom.mafiaonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zimincom.mafiaonline.item.MessageItem;

import org.java_websocket.WebSocket;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.client.StompClient;

public class GameRoomActivity extends AppCompatActivity implements View.OnClickListener{


    private StompClient mStompClient;
    Gson gson;
    MessageItem messageItem;
    Button sendButton;
    EditText messageInput;
    TextView textView;
    LinearLayout messageContainer;
    Intent intent;
    String roomId;
    String userName;

    final public String socketLink = "ws://211.249.60.54:8000/websockethandler/websocket";
   // final public String socketLink = "ws://192.168.1.222:8080/websockethandler/websocket";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_room);


        sendButton = (Button)findViewById(R.id.send_button);
        messageInput = (EditText) findViewById(R.id.message_input);
        messageContainer =(LinearLayout)findViewById(R.id.messages_container);
        intent = getIntent();
        roomId = intent.getStringExtra("roomId");
        userName = intent.getStringExtra("userName");


        mStompClient = Stomp.over(WebSocket.class,socketLink);
        mStompClient.connect();


        mStompClient.topic("/from/chat/"+roomId).subscribe(topicMessage -> {


            runOnUiThread(() -> {

                    textView = new TextView(getApplicationContext());
                    MessageItem messageItem = gson.fromJson(topicMessage.getPayload(),MessageItem.class);

                    textView.setText(messageItem.content);
                    textView.setGravity(Gravity.CENTER);
                    textView.setBackgroundResource(R.drawable.rsz_speech_bubble);

                    Log.d("MainActivity", topicMessage.getPayload());

                    messageContainer.addView(textView);
            });


        });

        gson = new Gson();

        sendButton.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void send(String message){
        mStompClient.send("/to/chat/"+roomId, gson.toJson(new MessageItem(userName,message))).subscribe();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mStompClient = Stomp.over(WebSocket.class,socketLink);
        mStompClient.connect();
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.send_button){
            String message = messageInput.getText().toString();
            Log.d("MainActivity",message);
            messageInput.setText("");
            send(message);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mStompClient.disconnect();
    }
}
