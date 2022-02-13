package ec.edu.ups.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.dao.GrupoDAO;
import ec.edu.ups.model.Grupo;

@Stateless
public class GrupoON implements GrupoONRemote, GrupoONLocal{
	
	@Inject
	private GrupoDAO daoGrupo;
	
	public void insertar(Grupo g) throws Exception  {
		daoGrupo.insert(g);
	}
	
	public List <Grupo> getGrupo(int idNivel){
		return daoGrupo.getList( idNivel);
	}
	
}
