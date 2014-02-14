package com.galdino.persistencia;

import java.util.List;

import com.galdino.persistencia.PersistenciaDisciplinaSQL.DisciplinasCursor;
import com.galdino.universitycalculator.Disciplina;

public interface PersistenciaDisciplina {
	
	public Long salvarDisciplina(Disciplina d);
	public DisciplinasCursor consultarDisciplinas(DisciplinasCursor.OrdenarPor ordenarPor);
	
}
