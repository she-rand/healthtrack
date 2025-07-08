package com.ejemplo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.io.File;
import static org.assertj.core.api.Assertions.*;

/**
 * Pruebas funcionales con Selenium para HealthTrack
 */
public class UsuarioSeleniumTest {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private String htmlFilePath;
    
    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    
    @BeforeEach
    void setUp() {
        // Configurar Chrome en modo headless para CI/CD
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Obtener ruta del archivo HTML
        File htmlFile = new File("src/test/resources/web/index.html");
        htmlFilePath = "file://" + htmlFile.getAbsolutePath();
        
        driver.get(htmlFilePath);
    }
    
    @Test
    @DisplayName("✅ Debe cargar la página correctamente")
    void debeCargarPaginaCorrectamente() {
        String titulo = driver.getTitle();
        assertThat(titulo).isEqualTo("HealthTrack - Sistema de Peso");
        
        WebElement header = driver.findElement(By.tagName("h1"));
        assertThat(header.getText()).contains("HealthTrack");
    }
    
    @Test
    @DisplayName("✅ Debe registrar usuario correctamente")
    void debeRegistrarUsuarioCorrectamente() {
        // Llenar formulario de registro
        WebElement nombreInput = driver.findElement(By.id("nombre"));
        WebElement pesoInput = driver.findElement(By.id("peso"));
        WebElement registrarBtn = driver.findElement(By.id("registrar"));
        
        nombreInput.sendKeys("Ana García");
        pesoInput.sendKeys("65.5");
        registrarBtn.click();
        
        // Verificar que se muestra el usuario
        WebElement usuarioNombre = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("usuario-nombre"))
        );
        
        assertThat(usuarioNombre.getText()).isEqualTo("Ana García");
        
        WebElement pesoActual = driver.findElement(By.id("peso-actual"));
        assertThat(pesoActual.getText()).isEqualTo("65.5");
    }
    
    @Test
    @DisplayName("❌ DEMUESTRA EL ERROR: Actualización de peso incorrecta")
    void demuestraErrorActualizacionPeso() {
        // Registrar usuario primero
        registrarUsuario("Pedro López", "70.0");
        
        // Intentar actualizar peso
        WebElement nuevoPesoInput = driver.findElement(By.id("nuevo-peso"));
        WebElement actualizarBtn = driver.findElement(By.id("actualizar"));
        
        nuevoPesoInput.sendKeys("75.0");
        actualizarBtn.click();
        
        // Verificar que el error está presente
        WebElement pesoActual = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("peso-actual"))
        );
        
        // El error: debería ser 75.0 pero será 69.0 (70.0 - 1)
        assertThat(pesoActual.getText()).isEqualTo("69");
        assertThat(pesoActual.getText()).isNotEqualTo("75");
    }
    
    @Test
    @DisplayName("✅ Debe mostrar error con datos inválidos")
    void debeMostrarErrorConDatosInvalidos() {
        WebElement nombreInput = driver.findElement(By.id("nombre"));
        WebElement pesoInput = driver.findElement(By.id("peso"));
        WebElement registrarBtn = driver.findElement(By.id("registrar"));
        
        nombreInput.sendKeys(""); // Nombre vacío
        pesoInput.sendKeys("-10"); // Peso negativo
        registrarBtn.click();
        
        // HTML5 validation debería prevenir el envío
        // Verificar que no se muestra la sección de usuario
        WebElement usuarioInfo = driver.findElement(By.id("usuario-info"));
        assertThat(usuarioInfo.isDisplayed()).isFalse();
    }
    
    private void registrarUsuario(String nombre, String peso) {
        WebElement nombreInput = driver.findElement(By.id("nombre"));
        WebElement pesoInput = driver.findElement(By.id("peso"));
        WebElement registrarBtn = driver.findElement(By.id("registrar"));
        
        nombreInput.sendKeys(nombre);
        pesoInput.sendKeys(peso);
        registrarBtn.click();
        
        // Esperar a que aparezca la información del usuario
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usuario-info")));
    }
    
    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}