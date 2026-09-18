package co.edu.sena.rfid.modelo;

/**
 * Representa una categoria de activos dentro del Sistema RFID.
 */
public class CategoriaActivo {

    private int idCategoria;
    private String nombre;
    private String descripcion;
    private boolean activo;

    /**
     * Constructor vacio requerido para crear objetos sin valores iniciales.
     */
    public CategoriaActivo() {
    }

    /**
     * Constructor utilizado para registrar una nueva categoria.
     *
     * @param nombre nombre de la categoria
     * @param descripcion descripcion de la categoria
     * @param activo estado de la categoria
     */
    public CategoriaActivo(
            String nombre,
            String descripcion,
            boolean activo) {

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    /**
     * Constructor utilizado para representar una categoria almacenada.
     *
     * @param idCategoria identificador de la categoria
     * @param nombre nombre de la categoria
     * @param descripcion descripcion de la categoria
     * @param activo estado de la categoria
     */
    public CategoriaActivo(
            int idCategoria,
            String nombre,
            String descripcion,
            boolean activo) {

        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {

        return "CategoriaActivo{"
                + "idCategoria=" + idCategoria
                + ", nombre='" + nombre + '\''
                + ", descripcion='" + descripcion + '\''
                + ", activo=" + activo
                + '}';
    }
}