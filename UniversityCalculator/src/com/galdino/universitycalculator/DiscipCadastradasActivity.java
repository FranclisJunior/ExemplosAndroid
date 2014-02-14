package com.galdino.universitycalculator;




import java.util.LinkedList;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.galdino.persistencia.PersistenciaDisciplinaSQL;
import com.galdino.persistencia.PersistenciaDisciplinaSQL.DisciplinasCursor;

public class DiscipCadastradasActivity extends Activity {
	
	private PersistenciaDisciplinaSQL cDados;
	private Button bVoltar, bNovo;
	private TextView tv;	
	private LinkedList<String> disciplinas;
	private ListView lv_dsc;
	ArrayAdapter<String> adapter;
	String[] strings = new String[200];
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disciplinascadastradas);
		iniciarComponentes();		
	}
	
	private void iniciarComponentes(){
		
		lv_dsc =  (ListView) findViewById(R.id.lv_disciplinas);
		
		tv = (TextView)findViewById(R.id.txtDisciplinasCadastradas);
		
		
		bVoltar = (Button) findViewById(R.id.btnVoltar);
		bVoltar.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				  Intent intent = new Intent();
                  intent.setClass( DiscipCadastradasActivity.this, MainActivity.class);
                  DiscipCadastradasActivity.this.startActivity(intent);
                  DiscipCadastradasActivity.this.finish();
			}});
		
		bNovo = (Button) findViewById(R.id.btnNova);
		bNovo.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent intent = new Intent();
                intent.setClass( DiscipCadastradasActivity.this, CadastroActivity.class);
                DiscipCadastradasActivity.this.startActivity(intent);
                DiscipCadastradasActivity.this.finish();
			}});
		
		
		
		carregarLista();
	}
	
	public void carregarLista(){
		cDados = new PersistenciaDisciplinaSQL(this);
		DisciplinasCursor disc = cDados.consultarDisciplinas(DisciplinasCursor.OrdenarPor.NomeCrescente);
		String texto ="";	
				
		for( int i=0; i <disc.getCount(); i++){
			
			disc.moveToPosition(i);
			if(tv.getText().toString().equalsIgnoreCase("Nenhuma disciplina cadastrada"))
	    		tv.setText("");
			
			Disciplina d = disc.getDisciplina();
			
			texto ="\r\n Nome: " +d.getNome()+"\n Media: "+d.getMedia()+"\n Creditos: "+d.getCreditos()+ "\n Horas: "+ d.getHoras()+"\n";
			strings [i] = texto;
								
	    }
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,strings);
		lv_dsc.setAdapter(adapter);
		
	}
}