package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.Producto;

public final class ProductoDAO extends DAO {

    public void guardarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe indicar el producto");
            }
            String sql = "INSERT INTO producto (codigo, nombre, precio, codigo_fabricante) "
                    + "VALUES ( '" + producto.getCodigo() + "' , '" + producto.getNombre() + "' , '"
                    + producto.getPrecio() + "' ,'" + producto.getCodigo_fabricante() + "' );'";
            System.out.println(sql);
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void modificarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe indicar el producto que desea modificar");
            }
            String sql = "UPDATE producto SET "
                    + " codigo = '" + producto.getCodigo() + "' , nombre = '" + producto.getNombre() + "' , precio = '" + producto.getPrecio()
                    + "'WHERE codigo_fabricante = '" + producto.getCodigo_fabricante() + "'";

            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void eliminarProducto(int codigo) throws Exception {
        try {
            String sql = "DELETE FROM producto WHERE codigo = " + codigo + "";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public Producto buscarProductoPorCodigo(int codigo) throws Exception {
        try {
            String sql = "SELECT * FROM producto WHERE codigo = " + codigo + "";
            consultarBase(sql);
            Producto p = null;
            while (resultado.next()) {
                p = new Producto();
                p.setCodigo(resultado.getInt(1));
                p.setNombre(resultado.getString(2));
                p.setPrecio(resultado.getDouble(3));
                p.setCodigo_fabricante(resultado.getInt(4));
            }
            desconectarBase();
            return p;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public Collection<Producto> mostrarTodosLosProductos() throws Exception {
        try {
            String sql = "SELECT * FROM producto;";
            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigo_fabricante(resultado.getInt(4));
                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }

    public Producto buscarProductoPorNombreYFabricante(String nombre, int codigo_fabricante) throws Exception {

        try {
            if (nombre == null) {
                throw new Exception("Nombre del producto no especificado");
            }
            String sql = "SELECT * FROM producto WHERE nombre = '" + nombre + "' "
                    + " AND codigo_fabricante = " + codigo_fabricante;
            consultarBase(sql);

            Producto producto = null;

            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigo_fabricante(resultado.getInt(4));
            }
            desconectarBase();
            return producto;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }

    public Collection<Producto> productosPorNombreYPrecio() throws Exception {
        try {
            String sql = "SELECT nombre, precio FROM producto";
            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(1));
                producto.setPrecio(resultado.getDouble(2));
                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }

    //productosPrecioEntre120Y202
    public Collection<Producto> productosPrecioEntre120Y202() throws Exception {
        try {
            String sql = "SELECT * FROM producto WHERE precio >= 120 AND precio <= 202";
            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigo_fabricante(resultado.getInt(4));
                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }

    //Buscar y listar todos los Portátiles de la tabla producto
    public Collection<Producto> portatiles() throws Exception {
        try {
            String sql = "SELECT * FROM producto WHERE nombre like '%portatil%'";
            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigo_fabricante(resultado.getInt(4));
                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }

    //Listar el nombre y el precio del producto más barato
    public Producto productoMasBarato() throws Exception {
        try {
            String sql = "SELECT nombre, precio FROM producto WHERE precio (SELECT MIN(precio) FROM producto)";
            consultarBase(sql);
            Producto producto = null;
            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(1));
                producto.setPrecio(resultado.getDouble(2));
            }
            desconectarBase();
            return producto;
        } catch (Exception e) {
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }
    
    /*
UPDATE nombre_tabla
SET nombre_columna1 = valor1 -----> EL SET INDICA QUE SE VA A CAMBIAR
nombre_columna2 = valor2,
[ORDER BY ...] [WHERE condicion]
            
UPDATE personajes
 SET aparicion = 1938
 WHERE id_personaje = 12;
             */

}
