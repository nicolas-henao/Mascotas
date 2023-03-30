package paqueteMascota;

public class Mascota {
	

	    private int id;
	    private String nombre;
	    private String color;
	    private int edad;
	    private String tipo;

	    public Mascota(int id, String nombre, String color, int edad, String tipo) {
	        this.id = id;
	        this.nombre = nombre;
	        this.color = color;
	        this.edad = edad;
	        this.tipo = tipo;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public String getColor() {
	        return color;
	    }

	    public int getEdad() {
	        return edad;
	    }

	    public String getTipo() {
	        return tipo;
	    }
	}

