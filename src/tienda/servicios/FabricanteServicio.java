package tienda.servicios;

import java.util.Collection;
import java.util.Scanner;
import tienda.entidades.Fabricante;
import tienda.persistencia.FabricanteDAO;

public class FabricanteServicio {

    private FabricanteDAO dao;

    public FabricanteServicio() {
        this.dao = new FabricanteDAO();
    }

    public void crearFabricante(int codigo, String nombre) throws Exception {

        try {
            //VALIDACION
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del fabricante");
            }

            //CREACION DEL FABRICANTE
            Fabricante fabricante = new Fabricante();
            fabricante.setCodigo(codigo);
            fabricante.setNombre(nombre);
            dao.guardarFabricante(fabricante);
        } catch (Exception e) {
            throw e;
        }
    }

    //Ingresar un FABRICANTE a la base de datos.
    public void ingresarFabricanteAlaBD() throws Exception {
        try {
            Scanner leer = new Scanner(System.in).useDelimiter("\n");
            System.out.println("Ingrese el codigo del fabricante:");
            int codigo = leer.nextInt();
            System.out.println("Ingrese el nombre del Fabricante:");
            String nombre = leer.next();
            //lo mandamos a crear fabricante:
            crearFabricante(codigo, nombre);
        } catch (Exception e) {
        }
    }

    public Collection<Fabricante> mostrarTodosLosFabricantes() throws Exception {
        try {
            Collection<Fabricante> fabricantes = dao.mostrarTodosLosFabricantes();
            return fabricantes;
        } catch (Exception e) {
            throw e;
        }
    }

    public void imprimirFabricantes() throws Exception {
        try {
            //Traemos los productos
            Collection<Fabricante> fabricantes = mostrarTodosLosFabricantes();
            //Imprimimos los productos
            if (fabricantes.isEmpty()) {
                throw new Exception("No existen fabricantes para imprimir");
            } else {
                for (Fabricante f : fabricantes) {
                    System.out.println(f);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
