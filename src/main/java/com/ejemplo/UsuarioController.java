package com.ejemplo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Controlador REST simulado para pruebas de rendimiento
 */
public class UsuarioController {
    
    private static final Map<String, Usuario> usuarios = new ConcurrentHashMap<>();
    private static long contador = 0;
    
    /**
     * Crear un nuevo usuario
     */
    public static Map<String, Object> crearUsuario(String nombre, double peso) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Simular validación
            if (nombre == null || nombre.trim().isEmpty()) {
                response.put("error", "Nombre requerido");
                response.put("status", 400);
                return response;
            }
            
            if (peso <= 0) {
                response.put("error", "Peso debe ser mayor a 0");
                response.put("status", 400);
                return response;
            }
            
            // Crear usuario
            String id = "user_" + (++contador);
            Usuario usuario = new Usuario(nombre, peso);
            usuarios.put(id, usuario);
            
            // Simular latencia de base de datos
            Thread.sleep(50 + (long)(Math.random() * 100));
            
            response.put("id", id);
            response.put("usuario", Map.of(
                "nombre", usuario.getNombre(),
                "peso", usuario.getPeso()
            ));
            response.put("status", 201);
            response.put("timestamp", System.currentTimeMillis());
            
        } catch (Exception e) {
            response.put("error", "Error interno del servidor");
            response.put("status", 500);
        }
        
        return response;
    }
    
    /**
     * Actualizar peso de usuario
     */
    public static Map<String, Object> actualizarPeso(String id, double nuevoPeso) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Usuario usuario = usuarios.get(id);
            if (usuario == null) {
                response.put("error", "Usuario no encontrado");
                response.put("status", 404);
                return response;
            }
            
            if (nuevoPeso <= 0) {
                response.put("error", "Peso debe ser mayor a 0");
                response.put("status", 400);
                return response;
            }
            
            // Simular latencia de base de datos
            Thread.sleep(30 + (long)(Math.random() * 50));
            
            double pesoAnterior = usuario.getPeso();
            usuario.actualizarPeso(nuevoPeso); // Aquí está el error
            
            response.put("usuario", Map.of(
                "nombre", usuario.getNombre(),
                "pesoAnterior", pesoAnterior,
                "pesoActual", usuario.getPeso(),
                "esperado", nuevoPeso
            ));
            response.put("status", 200);
            response.put("timestamp", System.currentTimeMillis());
            
        } catch (Exception e) {
            response.put("error", "Error interno del servidor");
            response.put("status", 500);
        }
        
        return response;
    }
    
    /**
     * Obtener usuario por ID
     */
    public static Map<String, Object> obtenerUsuario(String id) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Usuario usuario = usuarios.get(id);
            if (usuario == null) {
                response.put("error", "Usuario no encontrado");
                response.put("status", 404);
                return response;
            }
            
            // Simular latencia de consulta
            Thread.sleep(20 + (long)(Math.random() * 30));
            
            response.put("usuario", Map.of(
                "nombre", usuario.getNombre(),
                "peso", usuario.getPeso()
            ));
            response.put("status", 200);
            response.put("timestamp", System.currentTimeMillis());
            
        } catch (Exception e) {
            response.put("error", "Error interno del servidor");
            response.put("status", 500);
        }
        
        return response;
    }
    
    /**
     * Obtener estadísticas del sistema
     */
    public static Map<String, Object> obtenerEstadisticas() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            response.put("totalUsuarios", usuarios.size());
            response.put("timestamp", System.currentTimeMillis());
            response.put("status", 200);
            
        } catch (Exception e) {
            response.put("error", "Error interno del servidor");
            response.put("status", 500);
        }
        
        return response;
    }
}