package com.galdino.persistencia;

public  class ScriptsSQL {
	public static final String TABLE_DISCIPLINAS ="CREATE TABLE IF NOT EXISTS DISCIPLINAS (" +
		  "ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
		  "NOME varchar(15)  NOT NULL,"+
		  "MEDIA float  NOT NULL,"+
		  "CREDITOS integer NOT NULL,"+
		  "HORAS interger NOT NULL"+
		   " )  ;";
}
