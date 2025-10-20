package presentacion;

import logica.*;
import dominio.*;
import datos.*;
import java.util.*;

public class PaquExpress {

    private static GestorClientes gestorClientes = new GestorClientes();
    private static GestorPaquetes gestorPaquetes = new GestorPaquetes();
    private static GestorRepartidores gestorRepartidores = new GestorRepartidores();
    private static GestorEnvios gestorEnvios = new GestorEnvios();
    private static GestorReportes gestorReportes = new GestorReportes();
    private static ArchivoManager archivoManager = new ArchivoManager();

    private static ArrayList<Zona> zonas = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    private static Set<Integer> paquetesAsignados = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("=== SISTEMA PAQUEXPRESS ===");
        System.out.println("Inicio limpio: no se cargaron datos previos automaticamente.\n");

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1 ->
                    crearZona();
                case 2 ->
                    registrarCliente();
                case 3 ->
                    registrarRepartidor();
                case 4 ->
                    registrarPaquete();
                case 5 ->
                    crearEnvioInteractivoBalanceado();
                case 6 ->
                    entregarPaquete();
                case 7 ->
                    listarDatos();
                case 8 ->
                    guardarDatos();
                case 9 ->
                    cargarDatos();
                case 10 ->
                    verEntregasPorRepartidor();
                case 0 ->
                    System.out.println("Saliendo del sistema...");
                default ->
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("""
                -------- MENU PRINCIPAL --------
                1. Crear Zona
                2. Registrar Cliente
                3. Registrar Repartidor
                4. Registrar Paquete
                5. Crear Envio (Interactivo y Balanceado)
                6. Entregar Paquete
                7. Listar Informacion
                8. Guardar Datos
                9. Cargar Datos
                10. Ver Entregas por Repartidor
                0. Salir
                --------------------------------
                """);
    }

    private static void crearZona() {
        int id = leerEntero("ID de la zona: ");
        System.out.print("Nombre de la zona: ");
        String nombre = sc.nextLine();
        Zona z = new Zona(id, nombre);
        zonas.add(z);
        System.out.println("Zona creada correctamente.");
    }

    private static Zona seleccionarZona() {
        if (zonas.isEmpty()) {
            System.out.println("No hay zonas registradas.");
            return null;
        }
        System.out.println("Zonas disponibles:");
        for (Zona z : zonas) {
            System.out.println(z.getId() + " - " + z.getNombre());
        }
        int id = leerEntero("Ingrese ID de la zona: ");
        for (Zona z : zonas) {
            if (z.getId() == id) {
                return z;
            }
        }
        System.out.println("Zona no encontrada.");
        return null;
    }

    private static void registrarCliente() {
        System.out.print("Cedula: ");
        String cedula = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Direccion: ");
        String direccion = sc.nextLine();
        System.out.print("Telefono: ");
        String telefono = sc.nextLine();
        Zona zona = seleccionarZona();
        if (zona == null) {
            return;
        }

        System.out.print("Tipo de cliente (1.Particular / 2.Empresa): ");
        int tipo = leerEntero("");
        Cliente c = (tipo == 2)
                ? new Empresa(cedula, nombre, direccion, zona, telefono)
                : new Particular(cedula, nombre, direccion, zona, telefono);

        gestorClientes.agregarCliente(c);
        zona.asignarCliente(c);
        System.out.println("Cliente registrado correctamente.");
    }

    private static void registrarRepartidor() {
        System.out.print("Cedula: ");
        String cedula = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Telefono: ");
        String telefono = sc.nextLine();
        Zona zona = seleccionarZona();
        if (zona == null) {
            return;
        }

        System.out.print("Tipo de repartidor (1.Moto / 2.Camion): ");
        int tipoVeh = leerEntero("");
        System.out.print("Capacidad (kg): ");
        double capacidad = leerDouble("");

        Repartidor r;
        if (tipoVeh == 2) {
            r = new Camion(cedula, nombre, telefono, zona, capacidad);
        } else {
            r = new Moto(cedula, nombre, telefono, zona, capacidad);
        }

        gestorRepartidores.agregarRepartidor(r);
        zona.asignarRepartidor(r);
        System.out.println("Repartidor registrado correctamente: " + r.getNombre());
    }

    private static void registrarPaquete() {
        int id = leerEntero("ID del paquete: ");
        double peso = leerDouble("Peso (kg): ");
        double dim = leerDouble("Dimensiones (cm3): ");
        System.out.print("Destino: ");
        String destino = sc.nextLine();
        Zona zonaEntrega = seleccionarZona();
        if (zonaEntrega == null) {
            System.out.println("No se selecciono zona, el paquete no fue registrado.");
            return;
        }

        Paquete p = new Paquete(id, peso, dim, destino);
        gestorPaquetes.agregarPaquete(p);
        System.out.println("Paquete registrado correctamente en la zona " + zonaEntrega.getNombre() + ".");
    }

    private static void crearEnvioInteractivoBalanceado() {
        if (gestorClientes.listarClientes().isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        if (gestorPaquetes.listarPaquetes().isEmpty()) {
            System.out.println("No hay paquetes registrados.");
            return;
        }

        System.out.println("Clientes disponibles:");
        for (Cliente c : gestorClientes.listarClientes()) {
            System.out.println(c.getId() + " - " + c.getNombre());
        }
        int idCliente = leerEntero("ID del cliente: ");
        Cliente cliente = gestorClientes.buscarCliente(idCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        Zona zona = cliente.getZona();
        if (zona == null) {
            System.out.println("El cliente no tiene zona asignada.");
            return;
        }
        
        List<Paquete> pendientes = new ArrayList<>();
        for (Paquete p : gestorPaquetes.listarPaquetes()) {
            if (!paquetesAsignados.contains(p.getId())) {
                pendientes.add(p);
            }
        }

        if (pendientes.isEmpty()) {
            System.out.println("No hay paquetes pendientes para asignar.");
            return;
        }

        System.out.println("\nPaquetes pendientes:");
        for (Paquete p : pendientes) {
            System.out.printf("ID: %d | Peso: %.2f kg | Destino: %s%n", p.getId(), p.getPeso(), p.getDestino());
        }

        System.out.println("\nRepartidores disponibles:");
        List<Repartidor> repartidoresZona = zona.listarRepartidores();
        for (int i = 0; i < repartidoresZona.size(); i++) {
            Repartidor r = repartidoresZona.get(i);
            String tipoStr = (r instanceof Camion) ? "Camion" : "Moto";
            System.out.println((i + 1) + ". " + r.getNombre() + " | Cedula:" + r.getCedula()
                    + " | Tipo: " + tipoStr + " | Envios actuales: " + r.getCantidadEnvios());
        }

        int numRep = leerEntero("Seleccione repartidor (numero): ");
        if (numRep < 1 || numRep > repartidoresZona.size()) {
            System.out.println("Seleccion invalida.");
            return;
        }

        Repartidor elegido = repartidoresZona.get(numRep - 1);

        System.out.println("Ingrese los IDs de los paquetes a asignar separados por coma:");
        String linea = sc.nextLine().trim();
        String[] partes = linea.split(",");
        List<Paquete> paquetesSeleccionados = new ArrayList<>();
        for (String s : partes) {
            try {
                int idp = Integer.parseInt(s.trim());
                Paquete p = gestorPaquetes.buscarPaquete(idp);
                if (p != null && !paquetesAsignados.contains(p.getId())) {
                    paquetesSeleccionados.add(p);
                    paquetesAsignados.add(p.getId());
                }
            } catch (Exception e) {
            }
        }

        if (paquetesSeleccionados.isEmpty()) {
            System.out.println("No se seleccionaron paquetes validos.");
            return;
        }

        Envio envio = gestorEnvios.crearEnvio(paquetesSeleccionados, cliente, zona);
        envio.setRepartidor(elegido);
        elegido.asignarEnvio(envio);

        System.out.println("Envio creado correctamente con los paquetes: " + listarIdsPaquetes(paquetesSeleccionados));
    }

    private static void entregarPaquete() {
        System.out.print("Cedula del repartidor: ");
        String cedula = sc.nextLine();
        Repartidor r = gestorRepartidores.buscarRepartidor(cedula);
        if (r == null) {
            System.out.println("Repartidor no encontrado.");
            return;
        }

        int idPaquete = leerEntero("ID del paquete a entregar: ");
        Paquete p = gestorPaquetes.buscarPaquete(idPaquete);
        if (p == null) {
            System.out.println("Paquete no encontrado.");
            return;
        }

        Envio envioEncontrado = null;
        for (Envio e : gestorEnvios.listaEnvios()) {
            if (e.getRepartidor() != null && e.getRepartidor().getCedula().equals(cedula)) {
                for (Paquete pp : e.getPaquetes()) {
                    if (pp.getId() == idPaquete) {
                        envioEncontrado = e;
                        break;
                    }
                }
            }
        }

        if (envioEncontrado == null) {
            System.out.println("No se encontro un envio con ese paquete para el repartidor.");
            return;
        }

        r.entregarPaquete(p);
        envioEncontrado.setEstado("Entregado");
        System.out.println("Paquete entregado correctamente.");
    }

    private static void verEntregasPorRepartidor() {
        System.out.print("Cedula del repartidor: ");
        String cedula = sc.nextLine();
        Repartidor r = gestorRepartidores.buscarRepartidor(cedula);
        if (r == null) {
            System.out.println("Repartidor no encontrado.");
            return;
        }

        List<Envio> entregados = new ArrayList<>();
        for (Envio e : r.listarEnvios()) {
            if ("Entregado".equalsIgnoreCase(e.getEstado())) {
                entregados.add(e);
            }
        }

        if (entregados.isEmpty()) {
            System.out.println("El repartidor " + r.getNombre() + " no ha realizado entregas.");
            return;
        }

        System.out.println("El repartidor " + r.getNombre() + " ha realizado " + entregados.size() + " entregas:");
        for (Envio e : entregados) {
            Cliente c = e.getCliente();
            if (c != null) {
                System.out.println("Cliente: " + c.getNombre() + " | ID: " + c.getId() + " | Direccion: " + c.getDireccion());
            }
        }
    }

    private static String listarIdsPaquetes(List<Paquete> lista) {
        if (lista == null || lista.isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < lista.size(); i++) {
            sb.append(lista.get(i).getId());
            if (i < lista.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static void listarDatos() {
        System.out.println("\n--- Listados ---");
        System.out.println("1. Clientes\n2. Repartidores\n3. Paquetes\n4. Envios\n5. Zonas");
        int op = leerEntero("Opcion: ");
        switch (op) {
            case 1 ->
                gestorClientes.listarClientes().forEach(System.out::println);
            case 2 ->
                gestorRepartidores.listarRepartidores().forEach(System.out::println);
            case 3 -> {
                for (Paquete p : gestorPaquetes.listarPaquetes()) {
                    String estado = paquetesAsignados.contains(p.getId()) ? "Asignado" : "Pendiente";
                    System.out.printf("Paquete ID:%d | Peso:%.2f | Destino:%s | %s%n",
                            p.getId(), p.getPeso(), p.getDestino(), estado);
                }
            }
            case 4 -> {
                List<Envio> envs = gestorEnvios.listaEnvios();
                if (envs.isEmpty()) {
                    System.out.println("No hay envios registrados.");
                } else {
                    for (Envio e : envs) {
                        String paquetes = listarIdsPaquetes(e.getPaquetes());
                        String repart = (e.getRepartidor() != null) ? e.getRepartidor().getNombre() : "N/A";
                        String cliente = (e.getCliente() != null) ? e.getCliente().getNombre() : "N/A";
                        String zona = (e.getZona() != null) ? e.getZona().getNombre() : "N/A";
                        System.out.printf("Envio #%s | Estado: %s | Cliente: %s | Zona: %s | Repartidor: %s | Paquetes: %s%n",
                                e.getId(), e.getEstado(), cliente, zona, repart, paquetes);
                    }
                }
            }
            case 5 ->
                zonas.forEach(System.out::println);
            default ->
                System.out.println("Opcion invalida.");
        }
    }

    private static void guardarDatos() {
        archivoManager.guardarDatos(gestorEnvios, gestorReportes, gestorRepartidores, gestorClientes, gestorPaquetes);
        System.out.println("Datos guardados correctamente.");
    }

    private static void cargarDatos() {
        DataStore ds = archivoManager.cargarDatos();
        if (ds != null) {
            gestorEnvios = ds.gestorEnvios;
            gestorReportes = ds.gestorReportes;
            gestorRepartidores = ds.gestorRepartidores;
            gestorClientes = ds.gestorClientes;
            gestorPaquetes = ds.gestorPaquetes;
            paquetesAsignados.clear();
            for (Envio e : gestorEnvios.listaEnvios()) {
                for (Paquete p : e.getPaquetes()) {
                    paquetesAsignados.add(p.getId());
                }
            }
            System.out.println("Datos restaurados correctamente.");
        } else {
            System.out.println("No se cargaron datos previos.");
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido, intente de nuevo.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido, intente de nuevo.");
            }
        }
    }
}
