package Taller3;

public class factoryUsuarios {

	public static Usuario crear(String[] p) {

		String user = p[0].strip();
		String contra = p[1].strip();
		String rol = p[2].strip();
		
		switch(rol.toLowerCase()) {
		
		case "administrador":{
			
			return new Administrador(user, contra, rol);
			
		}
		case "colaborador":{
			
			return new Colaborador(user, contra, rol);
			
		}	
		
		default: {
			// TODO: Refactor
            System.out.println("Advertencia: Rol desconocido o no válido: " + rol);
            return null;
        }
		
		
		}
	}
}