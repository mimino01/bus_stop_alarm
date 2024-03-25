package com.example.busstopalarm.Loading;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.busstopalarm.Login.LoginActivity;
import com.example.busstopalarm.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class LoadingActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		
		
		try {
			Socket socket = new Socket("172.20.10.7",8000);
			
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			String outputMessage = "서버야 넣을께";
			out.write(outputMessage.getBytes());
			out.flush();
			
			byte[] inputData = new byte[100];
			int length = in.read(inputData);
			String inputMessage = new String(inputData, 0, length);
			
			socket.close();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
		Button button = findViewById(R.id.next);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//화면전환
				Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
				startActivity(intent);
				
				Toast.makeText(getApplicationContext(), "화면 전환 성공", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
