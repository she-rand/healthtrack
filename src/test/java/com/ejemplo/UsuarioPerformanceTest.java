package com.ejemplo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.Assertions.*;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import java.util.ArrayList;

/**
 * Pruebas de rendimiento para el sistema HealthTrack
 */
public class UsuarioPerformanceTest {
    
    @Test
    @DisplayName("📊 Prueba de carga: 100 usuarios concurrentes")
    void pruebaLoadTest100Usuarios() throws InterruptedException {
        int numeroUsuarios = 100;
        CountDownLatch latch = new CountDownLatch(numeroUsuarios);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        
        AtomicInteger exitos = new AtomicInteger(0);
        AtomicInteger errores = new AtomicInteger(0);
        List<Long> tiemposRespuesta = new ArrayList<>();
        
        long inicio = System.currentTimeMillis();
        
        for (int i = 0; i < numeroUsuarios; i++) {
            final int userId = i;
            executor.submit(() -> {
                try {
                    long tiempoInicio = System.nanoTime();
                    
                    // Crear usuario
                    Map<String, Object> resultado = UsuarioController.crearUsuario(
                        "Usuario" + userId, 
                        60.0 + (userId % 40)
                    );
                    
                    long tiempoFin = System.nanoTime();
                    long duracion = (tiempoFin - tiempoInicio) / 1_000_000; // ms
                    
                    synchronized (tiemposRespuesta) {
                        tiemposRespuesta.add(duracion);
                    }
                    
                    if ((Integer) resultado.get("status") == 201) {
                        exitos.incrementAndGet();
                    } else {
                        errores.incrementAndGet();
                    }
                    
                } catch (Exception e) {
                    errores.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }
        
        latch.await(); // Esperar a que terminen todos
        executor.shutdown();
        
        long fin = System.currentTimeMillis();
        long duracionTotal = fin - inicio;
        
        // Calcular estadísticas
        double promedioTiempo = tiemposRespuesta.stream()
            .mapToLong(Long::longValue)
            .average()
            .orElse(0.0);
        
        long maxTiempo = tiemposRespuesta.stream()
            .mapToLong(Long::longValue)
            .max()
            .orElse(0L);
        
        double throughput = (double) numeroUsuarios / duracionTotal * 1000; // req/seg
        
        // Imprimir resultados
        System.out.println("\n📊 RESULTADOS DE PRUEBA DE CARGA:");
        System.out.println("==================================");
        System.out.println("👥 Usuarios simulados: " + numeroUsuarios);
        System.out.println("✅ Requests exitosos: " + exitos.get());
        System.out.println("❌ Requests fallidos: " + errores.get());
        System.out.println("⏱️  Tiempo total: " + duracionTotal + " ms");
        System.out.println("📈 Throughput: " + String.format("%.2f", throughput) + " req/seg");
        System.out.println("⚡ Tiempo promedio: " + String.format("%.2f", promedioTiempo) + " ms");
        System.out.println("🔥 Tiempo máximo: " + maxTiempo + " ms");
        
        // Assertions
        assertThat(exitos.get()).isGreaterThan((int)(numeroUsuarios * 0.95)); // 95% éxito
        assertThat(promedioTiempo).isLessThan(500.0); // Menos de 500ms promedio
        assertThat(throughput).isGreaterThan(10.0); // Más de 10 req/seg
    }
    
    @Test
    @DisplayName("🔄 Prueba de stress: Actualización masiva de peso")
    void pruebaStressActualizacionPeso() throws InterruptedException {
        // Crear usuarios primero
        for (int i = 0; i < 50; i++) {
            UsuarioController.crearUsuario("TestUser" + i, 70.0);
        }
        
        int numeroActualizaciones = 200;
        CountDownLatch latch = new CountDownLatch(numeroActualizaciones);
        ExecutorService executor = Executors.newFixedThreadPool(20);
        
        AtomicInteger exitos = new AtomicInteger(0);
        AtomicInteger errores = new AtomicInteger(0);
        
        long inicio = System.currentTimeMillis();
        
        for (int i = 0; i < numeroActualizaciones; i++) {
            final int updateId = i;
            executor.submit(() -> {
                try {
                    String userId = "user_" + (updateId % 50 + 1);
                    double nuevoPeso = 60.0 + (updateId % 50);
                    
                    Map<String, Object> resultado = UsuarioController.actualizarPeso(userId, nuevoPeso);
                    
                    if ((Integer) resultado.get("status") == 200) {
                        exitos.incrementAndGet();
                    } else {
                        errores.incrementAndGet();
                    }
                    
                } catch (Exception e) {
                    errores.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }
        
        latch.await();
        executor.shutdown();
        
        long fin = System.currentTimeMillis();
        long duracionTotal = fin - inicio;
        double throughput = (double) numeroActualizaciones / duracionTotal * 1000;
        
        System.out.println("\n🔄 RESULTADOS DE PRUEBA DE STRESS:");
        System.out.println("===================================");
        System.out.println("🔄 Actualizaciones: " + numeroActualizaciones);
        System.out.println("✅ Exitosas: " + exitos.get());
        System.out.println("❌ Fallidas: " + errores.get());
        System.out.println("⏱️  Duración: " + duracionTotal + " ms");
        System.out.println("📈 Throughput: " + String.format("%.2f", throughput) + " req/seg");
        
        // Assertions

        assertThat(exitos.get()).isGreaterThan((int)(numeroActualizaciones * 0.90)); // 90% éxito
    }
}