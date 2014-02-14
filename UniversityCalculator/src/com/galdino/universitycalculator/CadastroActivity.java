package com.galdino.universitycalculator;


import com.galdino.persistencia.PersistenciaDisciplina;
import com.galdino.persistencia.PersistenciaDisciplinaSQL;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CadastroActivity extends Activity {
	
	private Button bVoltar, bSalvar;
	private EditText txNome, txMedia, txCreditos, txHoras;
	private TextView txNotNull;
	private PersistenciaDisciplinaSQL cDados;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrado_disciplinas);
		iniciarComponentes();
	}
	
	
	private void iniciarComponentes(){
	
		txNome = (EditText)findViewById(R.id.txtDisciplina);
		txMedia = (EditText)findViewById(R.id.txtMedia);
		txCreditos = (EditText)findViewById(R.id.txtCreditos);
		txHoras = (EditText)findViewById(R.id.txtHoras);	
		txNotNull = (TextView) findViewById(R.id.txNotNull);
		txNotNull.setVisibility(txNotNull.INVISIBLE);
		
		
		bVoltar = (Button) findViewById(R.id.btVoltar);
		bVoltar.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				  Intent intent = new Intent();
                  intent.setClass( CadastroActivity.this, MainActivity.class);
                  CadastroActivity.this.startActivity(intent);
                  CadastroActivity.this.finish();
			}});
		
		bSalvar=(Button) findViewById(R.id.btSalvar);
		bSalvar.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				SalvarDisciplina();
			}});
		
	}
	
	private boolean notNull(){
		if(!txNome.getText().toString().matches("") && !txMedia.getText().toString().matches("") &&
		   !txCreditos.getText().toString().matches("") && !txHoras.getText().toString().matches(""))
			return true;
		else
			return false;		
	}
	
	private void limpaCampos(){
		txNome.setText("");
		txMedia.setText("");
		txCreditos.setText("");
		txHoras.setText("");
	}
	
	private void SalvarDisciplina(){
		if(notNull()){
			cDados = new PersistenciaDisciplinaSQL(this);
			Disciplina d = new Disciplina();
			d.setNome(txNome.getText().toString());
			d.setMedia(Double.parseDouble(txMedia.getText().toString()));
			d.setCreditos(Integer.parseInt(txCreditos.getText().toString()));
			d.setHoras(Integer.parseInt(txHoras.getText().toString()));
			
			Long log = cDados.salvarDisciplina(d);	
			txNotNull.setVisibility(txNotNull.VISIBLE);
			
			if(log!=-1){
				txNotNull.setText("Salvo com sucesso");	
				limpaCampos();
			}else
				txNotNull.setText("Ocorreu um erro ao salvar, procure o suporte");
			
		}else{
			txNotNull.setVisibility(txNotNull.VISIBLE);
		}
	}
	
	
	
	
	
	

}
