package com.galdino.calculadora;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculadoraActivity extends Activity {
	
	

	EditText valor1, valor2, resultado;
	Button bSoma, bSub, bMult, bDiv;
	double v1, v2, r;
	
	
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
    	
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_calculadora);
    	
    	valor1 = (EditText) findViewById(R.id.valor1);
    	valor2 = (EditText) findViewById(R.id.valor2);
    	resultado = (EditText) findViewById(R.id.resultado);
    	
    	bSoma = (Button) findViewById(R.id.soma);
    	bSub = (Button) findViewById(R.id.sub);
    	bMult = (Button) findViewById(R.id.multi);
    	bDiv = (Button) findViewById(R.id.div);
    	
    	bSoma.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				v1 = Double.parseDouble(valor1.getText().toString());
				v2 = Double.parseDouble(valor2.getText().toString());
				r = v1 + v2;
				resultado.setText(String.valueOf(r));
				
			}
		});  
    	
    	bSub.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				v1 = Double.parseDouble(valor1.getText().toString());
				v2 = Double.parseDouble(valor2.getText().toString());
				r = v1 - v2;
				resultado.setText(String.valueOf(r));
				
			}
		});  
    	
    	bMult.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				v1 = Double.parseDouble(valor1.getText().toString());
				v2 = Double.parseDouble(valor2.getText().toString());
				r = v1 * v2;
				resultado.setText(String.valueOf(r));
				
			}
		});  

		bDiv.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				v1 = Double.parseDouble(valor1.getText().toString());
				v2 = Double.parseDouble(valor2.getText().toString());
				r = v1 / v2;
				resultado.setText(String.valueOf(r));				
			}
		});  
    }
}
