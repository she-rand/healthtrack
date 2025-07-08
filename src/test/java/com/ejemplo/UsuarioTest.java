package com.ejemplo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Usuario
 * Estas pruebas demostrarán el error en el sistema
 */
public class UsuarioTest {
    
    private Usuario usuario;
    
    @BeforeEach
    void setUp() {
        usuario = new Usuario("Test User", 70.0);
    }
    
    @Test
    @DisplayName("✅ Debe crear un usuario con nombre y peso correctos")
    void debeCrearUsuarioCorrectamente() {
        assertThat(usuario.getNombre()).isEqualTo("Test User");
        assertThat(usuario.getPeso()).isEqualTo(70.0);
    }
    
    @Test
    @DisplayName("❌ FALLA: Debe actualizar el peso correctamente")
    void debeActualizarPesoCorrectamente() {
        // Given
        double nuevoPeso = 75.0;
        
        // When
        usuario.actualizarPeso(nuevoPeso);
        
        // Then
        // Esta assertion FALLARÁ porque el sistema tiene un error
        assertThat(usuario.getPeso())
            .as("El peso debería ser exactamente el valor ingresado")
            .isEqualTo(nuevoPeso);
    }
    
    @Test
    @DisplayName("✅ DOCUMENTA EL ERROR: Demuestra que el sistema resta 1kg")
    void documentaElErrorDelSistema() {
        // Given
        double pesoInicial = usuario.getPeso();
        double nuevoPeso = 80.0;
        
        // When
        usuario.actualizarPeso(nuevoPeso);
        
        // Then
        // Esta prueba PASA porque documenta el comportamiento actual (erróneo)
        assertThat(usuario.getPeso())
            .as("El sistema erróneamente resta 1kg al peso inicial")
            .isEqualTo(pesoInicial - 1);
    }
}