package com.galdino.universitycalculator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button bAdicionar, bListar, bCRE;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		buttons();
	}
	
	private void buttons(){
		bAdicionar = (Button) findViewById(R.id.btAdicionarD);
		bListar = (Button)findViewById(R.id.btListarD);
		bCRE = (Button) findViewById(R.id.btCalcularCRE);
		
		
		bAdicionar.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				  Intent intent = new Intent(MainActivity.this, CadastroActivity.class);                  
                  MainActivity.this.startActivity(intent);
                  MainActivity.this.finish();
			}});
		
		bListar.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				 Intent intent = new Intent(MainActivity.this, DiscipCadastradasActivity.class);                  
                 MainActivity.this.startActivity(intent);
                 MainActivity.this.finish();				
			}});
		
	}
}
