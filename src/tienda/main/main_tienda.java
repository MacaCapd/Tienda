package tienda.main;

import tienda.entidades.Producto;
import tienda.servicios.FabricanteServicio;
import tienda.servicios.ProductoServicio;

public class main_tienda {

    public static void main(String[] args) throws Exception {

        ProductoServicio ps = new ProductoServicio();
        FabricanteServicio fs = new FabricanteServicio();

//        Producto producto = ps.buscarProductoPorNombreYFabricante("Disco duro SATA3 1TB", 5);
//        System.out.println(producto);

        //a)
        for (Producto p : ps.mostrarTodosLosProductos()) {
            System.out.println(p);
        }

        System.out.println("-------------");

        //b)
        for (Producto p : ps.productosPorNombreYPrecio()) {
            System.out.println(p.getNombre() + " $" + p.getPrecio());
        }

        System.out.println("-------------");

        //c)
        for (Producto p : ps.productosPrecioEntre120Y202()) {
            System.out.println(p);
        }
        
        System.out.println("-------------");

        //d
        for (Producto p : ps.portatiles()) {
            System.out.println(p);
        }
        
        System.out.println("-------------");

        //e
        Producto productoMasBarato = ps.productoMasBarato();
        System.out.println(productoMasBarato);
        
        System.out.println("-------------");

        //f
        ps.ingresarProductoAlaBD();
        ps.imprimirProductos();
        
        System.out.println("-------------");

        //g) Ingresar un fabricante a la base de datos
        fs.ingresarFabricanteAlaBD();
        fs.imprimirFabricantes();

        System.out.println("-------------");

        //h) Editar un producto con datos a elección
        
        
    }
}
