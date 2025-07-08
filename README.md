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

# 🏥 HealthTrack Testing Project

[![CI/CD Pipeline](https://github.com/TU_USUARIO/healthtrack-testing/actions/workflows/ci-cd-complete.yml/badge.svg)](https://github.com/TU_USUARIO/healthtrack-testing/actions/workflows/ci-cd-complete.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=TU_USUARIO_healthtrack-testing&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=TU_USUARIO_healthtrack-testing)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=TU_USUARIO_healthtrack-testing&metric=coverage)](https://sonarcloud.io/summary/new_code?id=TU_USUARIO_healthtrack-testing)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=TU_USUARIO_healthtrack-testing&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=TU_USUARIO_healthtrack-testing)

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

## 🛠️ Comandos Locales

```bash
# Ejecutar todas las pruebas
mvn clean test

# Generar reportes de cobertura
mvn jacoco:report

# Análisis SonarCloud local
mvn sonar:sonar -Dsonar.token=TU_TOKEN

# Pruebas de rendimiento JMeter
./run-jmeter.sh

# Pipeline completo local
mvn clean verify

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