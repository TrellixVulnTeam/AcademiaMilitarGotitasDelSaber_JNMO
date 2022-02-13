package ec.edu.ups.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.edu.ups.business.EstudianteONLocal;
import ec.edu.ups.business.GrupoONLocal;
import ec.edu.ups.business.MatriculaONLocal;
import ec.edu.ups.model.Estudiante;
import ec.edu.ups.model.Grupo;
import ec.edu.ups.model.Matricula;

@Path("historialAcademico")
public class HistorialAcademicoRest {
	@Inject
	private GrupoONLocal grupoON;
	
	@Inject
	private EstudianteONLocal estudianteON;
	
	@Inject
	private MatriculaONLocal matriculaON;
	
	private ArrayList<Integer>idGrupos=new ArrayList<Integer>();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Grupo> getMaterias(@QueryParam("cedula") String cedula){
		//String cedula="0104926548";
		List<Estudiante> estudiantes =new ArrayList<Estudiante>();
		List<Matricula> matriculas=new ArrayList<Matricula>();
		List<Grupo>grupos=new ArrayList<Grupo>();
		estudiantes=estudianteON.getEstudianteCuenta(cedula);
		
		for (int i = 0; i < estudiantes.size(); i++) {
			matriculas.addAll( matriculaON.getMatFact(estudiantes.get(i).getId()));	
		}
		System.out.println(matriculas);
		for (int i = 0; i < matriculas.size(); i++) {
			for (int j = 0; j < matriculas.get(i).getMatMaterias().size(); j++) {
				int grupo=matriculas.get(i).getMatMaterias().get(j).getIdGrupo();
				System.out.println("-------------------------------------------------------------------");
				System.out.println("id Grupos "+grupo);
				idGrupos.add(grupo);
			}
		}
		
		//System.out.println("grupos en la lista "+idGrupos);
		
		for (int i = 0; i < idGrupos.size(); i++) {
			grupos.addAll(grupoON.getGrupomatriculado(idGrupos.get(i)));
		}
		
		System.out.println(grupos);
	
		
		return grupos;
	}

}
