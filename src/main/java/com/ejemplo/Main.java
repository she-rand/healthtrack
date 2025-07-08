package com.ejemplo;

/**
 * Clase principal para demostrar el funcionamiento del sistema
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Sistema HealthTrack ===");
        System.out.println("Demostrando el error crítico del sistema\n");
        
        // Crear usuario de ejemplo
        Usuario usuario = new Usuario("Juan Pérez", 70.5);
        
        // Mostrar información inicial
        System.out.println("📊 Estado inicial:");
        usuario.mostrarInformacion();
        
        // Actualizar peso (aquí se manifiesta el error)
        System.out.println("\n🔄 Actualizando peso a 75.0 kg...");
        usuario.actualizarPeso(75.0);
        
        // Mostrar información después de actualizar
        System.out.println("\n📊 Estado después de actualizar:");
        usuario.mostrarInformacion();
        
        // Demostrar el error
        System.out.println("\n❌ ERROR DETECTADO:");
        System.out.println("   Se esperaba: 75.0 kg");
        System.out.println("   Se obtuvo: " + usuario.getPeso() + " kg");
        System.out.println("   Diferencia: " + (75.0 - usuario.getPeso()) + " kg");
        System.out.println("\n🐛 El sistema resta 1kg en lugar de asignar el nuevo valor");
        
        // Segundo ejemplo para confirmar el patrón
        System.out.println("\n🔄 Segundo intento: actualizando a 80.0 kg...");
        usuario.actualizarPeso(80.0);
        usuario.mostrarInformacion();
        System.out.println("   Confirmado: El sistema siempre resta 1kg al peso actual");
    }
}