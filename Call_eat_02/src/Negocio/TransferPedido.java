package Negocio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TransferPedido {
	protected String id; 
	protected Date fecha;
	protected String hora;
	protected List<TransferPlato> platos;
	protected boolean tipo;
	protected boolean metodoPago;
	protected String direccion;
	protected String notas;
	protected boolean preparado;
	
public TransferPedido(){
		
	}
	public TransferPedido(String id, Date fecha, List<TransferPlato> platos,boolean metodopago, boolean tipo, String direccion, String notas,boolean preparado){
		this.id = id ; 
		this.fecha = fecha;
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
		this.hora = formatoHora.format(fecha);
		this.platos = platos; 
		this.metodoPago = metodopago; //true = Efectivo; false = targeta
		this.tipo = tipo; //true = Aqui; false = domicilio
		this.direccion = direccion;
		this.notas = notas;
		this.preparado = preparado;
	}
	
	public String getId() {
        return id;
    }

    public void setId(String ID) {
        this.id = ID;
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
        this.metodoPago = metodo;
    }
    
    public boolean getMetodoPago() {
		return this.metodoPago;
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
    
    public boolean getPreparado() {
    	return this.preparado;
    }
    public void setPreparado(boolean estado) {
    	this.preparado = estado;
    }
    
    
}
