package co.edu.sena.rfid.vista;

import co.edu.sena.rfid.dao.CategoriaActivoDAO;
import co.edu.sena.rfid.modelo.CategoriaActivo;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner ENTRADA =
            new Scanner(System.in);

    private static final CategoriaActivoDAO CATEGORIA_DAO =
            new CategoriaActivoDAO();

    public static void main(String[] args) {

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1 -> registrarCategoria();
                case 2 -> consultarCategorias();
                case 3 -> actualizarCategoria();
                case 4 -> eliminarCategoria();
                case 5 -> System.out.println(
                        "\nPrograma finalizado correctamente."
                );
                default -> System.out.println(
                        "\nOpcion no valida. Intente nuevamente."
                );
            }

        } while (opcion != 5);

        ENTRADA.close();
    }

    private static void mostrarMenu() {

        System.out.println();
        System.out.println(
                "=============================================="
        );
        System.out.println(
                " SISTEMA RFID - GESTION DE CATEGORIAS"
        );
        System.out.println(
                "=============================================="
        );
        System.out.println("1. Registrar categoria");
        System.out.println("2. Consultar categorias");
        System.out.println("3. Actualizar categoria");
        System.out.println("4. Eliminar categoria");
        System.out.println("5. Salir");
        System.out.println(
                "=============================================="
        );
    }

    private static void registrarCategoria() {

        System.out.println();
        System.out.println("--- REGISTRAR CATEGORIA ---");

        String nombre = leerTextoObligatorio(
                "Nombre: "
        );

        String descripcion = leerTextoObligatorio(
                "Descripcion: "
        );

        boolean activo = leerEstado();

        CategoriaActivo categoria =
                new CategoriaActivo();

        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
        categoria.setActivo(activo);

        boolean registrada =
                CATEGORIA_DAO.insertarCategoria(categoria);

        if (registrada) {
            System.out.println(
                    "\nCategoria registrada correctamente."
            );
        } else {
            System.out.println(
                    "\nNo fue posible registrar la categoria."
            );
        }
    }

    private static void consultarCategorias() {

        System.out.println();
        System.out.println("--- CATEGORIAS REGISTRADAS ---");

        List<CategoriaActivo> categorias =
                CATEGORIA_DAO.listarCategorias();

        if (categorias.isEmpty()) {
            System.out.println(
                    "No existen categorias registradas."
            );
            return;
        }

        System.out.printf(
                "%-5s %-35s %-45s %-10s%n",
                "ID",
                "NOMBRE",
                "DESCRIPCION",
                "ESTADO"
        );

        System.out.println(
                "---------------------------------------------"
                + "---------------------------------------------------"
        );

        for (CategoriaActivo categoria : categorias) {

            String estado = categoria.isActivo()
                    ? "Activo"
                    : "Inactivo";

            String descripcion =
                    categoria.getDescripcion() == null
                    ? ""
                    : categoria.getDescripcion();

            System.out.printf(
                    "%-5d %-35s %-45s %-10s%n",
                    categoria.getIdCategoria(),
                    categoria.getNombre(),
                    descripcion,
                    estado
            );
        }

        System.out.println(
                "\nTotal de categorias: "
                + categorias.size()
        );
    }

    private static void actualizarCategoria() {

        System.out.println();
        System.out.println("--- ACTUALIZAR CATEGORIA ---");

        int idCategoria = leerEntero(
                "ID de la categoria: "
        );

        String nombre = leerTextoObligatorio(
                "Nuevo nombre: "
        );

        String descripcion = leerTextoObligatorio(
                "Nueva descripcion: "
        );

        boolean activo = leerEstado();

        CategoriaActivo categoria =
                new CategoriaActivo(
                        idCategoria,
                        nombre,
                        descripcion,
                        activo
                );

        boolean actualizada =
                CATEGORIA_DAO.actualizarCategoria(categoria);

        if (actualizada) {
            System.out.println(
                    "\nCategoria actualizada correctamente."
            );
        } else {
            System.out.println(
                    "\nNo se encontro una categoria con el ID "
                    + idCategoria
                    + "."
            );
        }
    }

    private static void eliminarCategoria() {

        System.out.println();
        System.out.println("--- ELIMINAR CATEGORIA ---");

        int idCategoria = leerEntero(
                "ID de la categoria: "
        );

        System.out.print(
                "Confirma la eliminacion? (S/N): "
        );

        String confirmacion =
                ENTRADA.nextLine().trim();

        if (!confirmacion.equalsIgnoreCase("S")) {
            System.out.println(
                    "\nEliminacion cancelada."
            );
            return;
        }

        boolean eliminada =
                CATEGORIA_DAO.eliminarCategoria(idCategoria);

        if (eliminada) {
            System.out.println(
                    "\nCategoria eliminada correctamente."
            );
        } else {
            System.out.println(
                    "\nNo se encontro una categoria con el ID "
                    + idCategoria
                    + "."
            );
        }
    }

    private static int leerEntero(String mensaje) {

        while (true) {

            System.out.print(mensaje);
            String valor = ENTRADA.nextLine().trim();

            try {
                return Integer.parseInt(valor);
            } catch (NumberFormatException e) {
                System.out.println(
                        "Ingrese un numero entero valido."
                );
            }
        }
    }

    private static String leerTextoObligatorio(
            String mensaje) {

        while (true) {

            System.out.print(mensaje);
            String texto = ENTRADA.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println(
                    "Este campo es obligatorio."
            );
        }
    }

    private static boolean leerEstado() {

        while (true) {

            System.out.print(
                    "Estado (1 = Activo, 0 = Inactivo): "
            );

            String estado = ENTRADA.nextLine().trim();

            if (estado.equals("1")) {
                return true;
            }

            if (estado.equals("0")) {
                return false;
            }

            System.out.println(
                    "Ingrese solamente 1 o 0."
            );
        }
    }
}