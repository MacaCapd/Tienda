package tienda.servicios;

import java.util.Collection;
import java.util.Scanner;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDAO;

public class ProductoServicio {

    private ProductoDAO dao;

    public ProductoServicio() {
        this.dao = new ProductoDAO();
    }

    public void crearProducto(String nombre, double precio, int codigo_fabricante) throws Exception {

        try {
            //VALIDACION
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del producto");
            }
            if (precio == 0 || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el precio del producto");
            }
            if (codigo_fabricante == 0 || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el código del fabricante del producto");
            }
            if (precio < 0) {
                throw new Exception("El precio no puede ser menor a 0");
            }
            if (dao.buscarProductoPorNombreYFabricante(nombre, codigo_fabricante) != null) {
                throw new Exception("Ya existe un producto cargado del fabricante indicado");
            }

            //CREACION DEL PRODUCTO
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCodigo_fabricante(codigo_fabricante);
            dao.guardarProducto(producto);
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarCodigoProducto(int codigo, String nombre, double precio, int codigo_fabricante) throws Exception {
        try {

            //Validamos
            if (codigo > 0) {
                throw new Exception("Debe indicar el código");
            }

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }

            if (precio == 0) {
                throw new Exception("Debe indicar el precio");
            }

            if (codigo_fabricante < 0) {
                throw new Exception("Debe indicar el código del fabricante");
            }

            //Buscamos
            Producto producto = buscarProductoPorCodigo(codigo);
            dao.modificarProducto(producto);
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarProducto(int codigo) throws Exception {

        try {

            //Validamos 
            if (codigo < 0) {
                throw new Exception("Debe indicar el código");
            }
            dao.eliminarProducto(codigo);
        } catch (Exception e) {
            throw e;
        }
    }

    public Producto buscarProductoPorCodigo(int codigo) throws Exception {

        try {
            //Validamos
            if (codigo < 0) {
                throw new Exception("Debe indicar el codigo");
            }
            Producto producto = dao.buscarProductoPorCodigo(codigo);
            //Verificamos
            if (producto == null) {
                throw new Exception("No se econtró producto para el codigo indicado");
            }
            return producto;
        } catch (Exception e) {
            throw e;
        }
    }

    public Producto buscarProductoPorNombreYFabricante(String nombre, int codigo_fabricante) throws Exception {
        try {
            //Validamos
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }
            if (codigo_fabricante < 0) {
                throw new Exception("Debe indicar el código del fabricante");
            }
            Producto producto = dao.buscarProductoPorNombreYFabricante(nombre, codigo_fabricante);
            if (producto == null) {
                throw new Exception("No se econtró producto para el nombre y fabricante indicado");
            }
            return producto;
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<Producto> productosPorNombreYPrecio() throws Exception {
        try {
            Collection<Producto> productos = dao.productosPorNombreYPrecio();
            return productos;
        } catch (Exception e) {
            throw e;
        }
    }

    // Listar aquellos productos que su precio esté entre 120 y 202
    public Collection<Producto> productosPrecioEntre120Y202() throws Exception {
        try {
            Collection<Producto> productos = dao.productosPrecioEntre120Y202();
            return productos;
        } catch (Exception e) {
            throw e;
        }
    }

    //portatiles
    public Collection<Producto> portatiles() throws Exception {
        try {
            Collection<Producto> productos = dao.portatiles();
            return productos;
        } catch (Exception e) {
            throw e;
        }
    }

    //Listar el nombre y el precio del producto más barato #productoMasBarato
    public Producto productoMasBarato() throws Exception {
        try {
            Producto producto = dao.productoMasBarato();
            return producto;
        } catch (Exception e) {
            throw e;
        }
    }

    //Ingresar un producto a la base de datos.
    public void ingresarProductoAlaBD() throws Exception {
        try {
            Scanner leer = new Scanner(System.in).useDelimiter("\n");
            System.out.println("Ingrese el nombre del producto:");
            String nombre = leer.next();
            System.out.println("Ingrese el precio del producto:");
            double precio = leer.nextDouble();
            System.out.println("Ingrese el código del fabricante:");
            int codigo_fabricante = leer.nextInt();

            //lo mandamos a crear producto:
            crearProducto(nombre, precio, codigo_fabricante);

        } catch (Exception e) {
        }
    }

    public Collection<Producto> mostrarTodosLosProductos() throws Exception {
        try {
            Collection<Producto> productos = dao.mostrarTodosLosProductos();
            return productos;
        } catch (Exception e) {
            throw e;
        }
    }

    public void imprimirProductos() throws Exception {
        try {
            //Traemos los productos
            Collection<Producto> productos = mostrarTodosLosProductos();
            //Imprimimos los productos
            if (productos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto producto : productos) {
                    System.out.println(producto.toString());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
