# 🏥 HealthTrack Testing Project

## 🐛 Problema Identificado
Error crítico: `actualizarPeso()` resta 1kg en lugar de asignar el nuevo peso.

## 🚀 Comandos Rápidos

```bash
# Ver el error en acción
mvn exec:java -Dexec.mainClass="com.ejemplo.Main"

# Ejecutar pruebas (1 fallará intencionalmente)
mvn test

# Generar reporte de cobertura
mvn clean test jacoco:report
```


# 🏥 HealthTrack Testing Project

[![CI/CD Pipeline](https://github.com/she-rand/healthtrack/actions/workflows/ci-cd-complete.yml/badge.svg)](https://github.com/she-rand/healthtrack/actions/workflows/ci-cd-complete.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=she-rand_healthtrack&metric=alert_status)](https://sonarcloud.io/summary/overall?id=she-rand_healthtrack&branch=master)
[![Coverage](https://sonarcloud.io/component_measures?id=she-rand_healthtrack&metric=coverage&view=list)](https://sonarcloud.io/component_measures?id=she-rand_healthtrack&metric=coverage&view=list)
[![Security Rating](https://sonarcloud.io/component_measures?metric=security_review_rating&view=list&id=she-rand_healthtrack)](https://sonarcloud.io/component_measures?metric=security_review_rating&view=list&id=she-rand_healthtrack)

## 🚀 Funcionalidades del Pipeline

### 🧪 Testing Completo
- ✅ **Pruebas Unitarias** - JUnit 5 + AssertJ
- ✅ **Pruebas Funcionales** - Selenium WebDriver
- ✅ **Pruebas de Rendimiento** - JMeter + Java Performance Tests
- ✅ **Análisis de Calidad** - SonarCloud
- ✅ **Análisis de Seguridad** - Trivy

### 🔄 CI/CD Pipeline
- ✅ **Build Automático** - Maven
- ✅ **Deploy Staging** - Automático en `develop`
- ✅ **Deploy Producción** - Automático en `main`
- ✅ **Reportes Completos** - Coverage, Performance, Security

### 📊 Métricas y Reportes
- 📈 **Cobertura de Código**: JaCoCo + SonarCloud
- ⚡ **Rendimiento**: JMeter HTML Reports
- 🛡️ **Seguridad**: Trivy + GitHub Security
- 🔍 **Calidad**: SonarCloud Quality Gate

## Estructura del proyecto
healthtrack/
├── src/
│   ├── main/java/com/ejemplo/
│   │   ├── Usuario.java (contiene el error)
│   │   └── Main.java (demuestra el error)
│   └── test/java/com/ejemplo/
│       └── UsuarioTest.java (documenta el error)
├── performance-tests/
├── selenium-tests/
└── pom.xml

## 🛠️ Comandos Locales

```bash
# Descargar dependencias (primera vez puede tardar)
mvn clean compile

# Ejecutar todas las pruebas
mvn clean test

# Generar reportes de cobertura
mvn jacoco:report

# El reporte se genera en target/site/jacoco/index.html
ls -la target/site/jacoco/

# Abrir el informe en el navegador
# En Mac:
open target/site/jacoco/index.html

# En Linux:
xdg-open target/site/jacoco/index.html

# En Windows:
start target/site/jacoco/index.html

# Análisis SonarCloud local
mvn sonar:sonar -Dsonar.token=TU_TOKEN

# Ejecutar las pruebas de Selenium
mvn test -Dtest=UsuarioSeleniumTest

# Para probar SonarCloud localmente (una vez configurado)
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=tu-usuario_healthtrack \
  -Dsonar.organization=tu-organizacion \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.token=TU_TOKEN_AQUI

# Ejecutar SonarCloud ignorando fallos de pruebas
mvn clean test jacoco:report sonar:sonar \
  -Dmaven.test.failure.ignore=true \
  -Dsonar.projectKey=healthtrack \
  -Dsonar.organization=tu-organizacion \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.token=TU_TOKEN_AQUI

 # Ejecutar con archivo válido
docker run --rm \
  -v $(pwd)/src/test/jmeter:/jmeter \
  -v $(pwd)/target/jmeter:/results \
  justb4/jmeter:latest \
  -n -t /jmeter/healthtrack-performance.jmx \
  -l /results/results.jtl \
  -e -o /results/html-report 

# Pruebas de rendimiento JMeter
./run-jmeter.sh

# Pipeline completo local
mvn clean verify
```


## 🚀 PARTE 5: Despliegue y Prueba

### PASO 5.1: Commit y Push

```bash
# Agregar todos los archivos
git add .

# Commit con mensaje descriptivo
git commit -m "feat: Add complete CI/CD pipeline with SonarCloud and JMeter

- Add comprehensive GitHub Actions workflow
- Configure SonarCloud integration
- Add JMeter performance testing
- Include security scanning with Trivy
- Set up staging/production deployment
- Add comprehensive documentation and badges"

# Push para disparar el pipeline
git push origin main
```