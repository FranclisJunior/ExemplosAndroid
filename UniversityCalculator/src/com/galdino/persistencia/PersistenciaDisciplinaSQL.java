package com.galdino.persistencia;


import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.os.Environment;
import android.util.Log;
import com.galdino.universitycalculator.Disciplina;
import com.galdino.universitycalculator.R;


public class PersistenciaDisciplinaSQL extends SQLiteOpenHelper implements PersistenciaDisciplina  {
	
	private final Context contexto;
	private static final int DATABASE_VERSION = 2;	
	private static final String NOME_BD = "UniversityCalculator";
	private static final String DISCIPLINAS = "DISCIPLINAS";
	private static final String NOME = "NOME";
	private static final String MEDIA = "MEDIA";
	private static final String CREDITOS = "CREDITOS";
	private static final String HORAS = "HORAS";
	
	
	
	public PersistenciaDisciplinaSQL(Context contexto) {
		super(contexto,NOME_BD, null, DATABASE_VERSION);
		this.contexto = contexto;
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = ScriptsSQL.TABLE_DISCIPLINAS;
		db.execSQL(sql);
		
	}	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("UC", "Atualizando a base de dados da versão " + oldVersion + " para " + newVersion + ", que destruirá todos os dados antigos");
		String sql = ScriptsSQL.TABLE_DISCIPLINAS;
		db.beginTransaction();
		
		try{
			db.execSQL(sql);
			db.setTransactionSuccessful();
		}catch (SQLException e){
			Log.e("Erro ao atualizar as tabelas e testar os dados", e.toString());
			throw e;
		} 
		finally{
			db.endTransaction();
		}		
		onCreate(db);		
	}
	
	@Override
	public Long salvarDisciplina(Disciplina d) {
		SQLiteDatabase sqlLite = new PersistenciaDisciplinaSQL(contexto).getWritableDatabase();	
		
		try{
			ContentValues content = new ContentValues(); 
	        content.put(NOME, d.getNome());
	        content.put(MEDIA, d.getMedia());
	        content.put(CREDITOS, d.getCreditos());
	        content.put(HORAS,d.getHoras());
	 
			return sqlLite.insert(DISCIPLINAS, null,content);
			
		}finally{
			sqlLite.close();
		}
	}
	
	public DisciplinasCursor consultarDisciplinas(DisciplinasCursor.OrdenarPor ordenarPor) {
		SQLiteDatabase db = getReadableDatabase();
		try{
			String sql = DisciplinasCursor.CONSULTA + (ordenarPor == DisciplinasCursor.OrdenarPor.NomeCrescente ? "ASC" : "DESC");
			DisciplinasCursor cc = (DisciplinasCursor) db.rawQueryWithFactory(new DisciplinasCursor.Factory(), sql, null, null);
			cc.moveToFirst();
			return cc;
		}finally{
			db.close();
		}
	}

	
	
	public static class DisciplinasCursor extends SQLiteCursor{
		
		private static final String CONSULTA = "SELECT * FROM Disciplinas ORDER BY Nome ";
		
		private DisciplinasCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query){
			super(db, driver, editTable, query);
		}
		
		public static enum OrdenarPor{
			NomeCrescente,
			NomeDecrescente
		}	
		
		private static class Factory implements SQLiteDatabase.CursorFactory{
			@Override
			public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query){
				return new DisciplinasCursor(db, driver, editTable, query);
			}
		}
		
		public Disciplina getDisciplina(){
			Disciplina disc = new Disciplina();
			disc.setId((int)getInt(getColumnIndexOrThrow("ID")));
			disc.setNome(getString(getColumnIndexOrThrow("NOME")));
			disc.setMedia((double)getDouble(getColumnIndexOrThrow("MEDIA")));
			disc.setCreditos((int)getInt(getColumnIndexOrThrow("CREDITOS")));
			disc.setHoras((int)getInt(getColumnIndexOrThrow("HORAS")));
			return disc;
		}
		
		public long getID(){
			return getLong(getColumnIndexOrThrow("ID"));
		}
		
		public String getNome(){
			return getString(getColumnIndexOrThrow("NOME"));
		}
		
		public String getMedia(){
			return getString(getColumnIndexOrThrow("MEDIA"));
		}
		
		public String getCreditos(){
			return getString(getColumnIndexOrThrow("CREDITOS"));
		}
		
		public String getHoras(){
			return getString(getColumnIndexOrThrow("HORAS"));
		}
	}




}
