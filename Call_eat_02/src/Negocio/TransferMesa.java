package Negocio;

public class TransferMesa {
    private static final long serialVersionUID = 1L;
    private String id;
    private int capacidad;
    private boolean disponible;
    private boolean reservada;
    private long tiempoReserva;

    // Constructor por defecto (necesario para Jackson)
    public TransferMesa() {
        this.disponible = true;
        this.reservada = false;
        this.tiempoReserva = 0;
    }

    // Constructor principal
    public TransferMesa(String id, int capacidad) {
        this.id = id;
        this.capacidad = capacidad;
        this.disponible = true;
        this.reservada = false;
        this.tiempoReserva = 0;
    }

    // Getters
    public String getId() {
        return id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public boolean isReservada() {
        return reservada;
    }

    public long getTiempoReserva() {
        return tiempoReserva;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setReservada(boolean reservada) {
        this.reservada = reservada;
    }

    public void setTiempoReserva(long tiempoReserva) {
        this.tiempoReserva = tiempoReserva;
    }

    // Método equals para comparar mesas (usado en DAOMesaImp)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferMesa that = (TransferMesa) o;
        return id.equals(that.id);
    }

    // Método hashCode (necesario si se usa equals)
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    // Método toString para depuración
    @Override
    public String toString() {
        return "TransferMesa{" +
                "id='" + id + '\'' +
                ", capacidad=" + capacidad +
                ", disponible=" + disponible +
                ", reservada=" + reservada +
                ", tiempoReserva=" + tiempoReserva +
                '}';
    }
}
