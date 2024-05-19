import java.util.Scanner;

class Persona {
    String dni;
    String nombre;
    String apellido;
    String direccion;
    String telefono;

    public Persona(String dni, String nombre, String apellido, String direccion, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public static Persona ingresarPersona(Scanner scanner) {

        System.out.println("Ingrese su DNI:");
        String dni = scanner.nextLine();

        System.out.println("Ingrese su nombre:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese su apellido:");
        String apellido = scanner.nextLine();

        System.out.println("Ingrese su dirección:");
        String direccion = scanner.nextLine();

        System.out.println("Ingrese su teléfono:");
        String telefono = scanner.nextLine();

        return new Persona(dni, nombre, apellido, direccion, telefono);
    }
}