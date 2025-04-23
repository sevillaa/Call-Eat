package Negocio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TransferPedido {
	protected String ID; 
	protected Date fecha;
	protected String hora;
	protected List<TransferPlato> platos;
	protected boolean tipo;
	protected boolean metodopago;
	protected String direccion;
	protected String notas;
	
	
public TransferPedido(){
		
	}
	public TransferPedido(String id, Date fecha, List<TransferPlato> platos,boolean metodopago, boolean tipo, String direccion, String notas ){
		this.ID = id ; 
		this.fecha = fecha;
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
		this.hora = formatoHora.format(fecha);
		this.platos = platos; 
		this.metodopago = metodopago; //true = Efectivo; false = targeta
		this.tipo = tipo; //true = Aqui; false = domicilio
		this.direccion = direccion;
		this.notas = notas;
	}
	
	public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        this.hora = formatoHora.format(fecha);
    }

    public String getHora() {
        return hora;
    }
    
    public void setMetodoPago(boolean metodo) {
        this.metodopago = metodo;
    }
    
    public boolean getMetodoPago() {
		return this.metodopago;
	}
    
    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }
    
    public boolean getTipo() {
		return this.tipo;
	}
    
    public void setHora(String hora) {
        this.hora = hora;
    }

    public List<TransferPlato> getPlatos() {
        return platos;
    }

    public void setPlatos(List<TransferPlato> platos) {
        this.platos = platos;
    }


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
	
}
