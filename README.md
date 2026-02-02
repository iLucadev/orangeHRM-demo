# OrangeHRM - Test Automation Project

Proyecto final de la **Diplomatura en Testing Profesional** - ICARO
Automatización E2E de OrangeHRM usando **Selenium**, **Page Object Model** y **Cucumber BDD**.

---

## 📋 Descripción

Sistema de automatización de pruebas end-to-end para la aplicación [OrangeHRM Demo](https://opensource-demo.orangehrmlive.com/).

**Flujo automatizado:**
```
LOGIN → DASHBOARD → PIM (Búsqueda de empleados) → LOGOUT
```

---

## 🛠️ Tecnologías

- **Java**: 17+
- **Maven**: 3.9+
- **Selenium WebDriver**: 4.10.0
- **Cucumber**: 7.13.0
- **TestNG**: 7.8.0
- **WebDriverManager**: 5.5.3

---

## 📦 Requisitos Previos

- **Java JDK 17** o superior
- **Maven 3.9** o superior
- **Google Chrome** (última versión)
- **Git**

---

## 🚀 Instalación

```bash
# Clonar el repositorio
git clone <repository-url>
cd orangehrm-automation

# Compilar el proyecto
mvn clean compile

# Descargar dependencias
mvn dependency:resolve
```

---

## ▶️ Ejecución de Tests

### Opción 1: Maven
```bash
# Ejecutar todos los tests
mvn test

# Ejecutar test específico
mvn test -Dtest=TestRunner
```

### Opción 2: IDE (IntelliJ IDEA / Eclipse)
- Click derecho en `testng.xml` → **Run**
- Click derecho en `flujo_completo.feature` → **Run Feature**

---

## 📊 Reportes

Después de ejecutar los tests, el reporte HTML estará disponible en:

```
target/cucumber-reports.html
```

Abrir en el navegador para ver resultados detallados.

---

## 🏗️ Estructura del Proyecto

```
orangehrm-automation/
├── pom.xml                           # Configuración Maven
├── testng.xml                        # Configuración TestNG
├── .gitignore                        # Exclusiones Git
│
└── src/
    ├── main/java/ar/org/icaro/pages/      # Page Objects
    │   ├── BasePage.java
    │   ├── LoginPage.java
    │   ├── DashboardPage.java
    │   └── PIMPage.java
    │
    └── test/
        ├── java/ar/org/icaro/
        │   ├── runner/
        │   │   ├── Hooks.java             # Lifecycle (@Before/@After)
        │   │   └── TestRunner.java        # Cucumber config
        │   └── steps/
        │       └── MainFlowSteps.java  # Step Definitions
        │
        └── resources/features/
            └── flujo_completo.feature     # Escenarios Gherkin
```

---

## 🔑 Credenciales de Prueba

**URL**: https://opensource-demo.orangehrmlive.com/

| Usuario | Contraseña |
|---------|------------|
| Admin   | admin123   |

**Empleados de prueba**: Amelia, Charles, Emily, James (usados en búsquedas PIM)

---

## 🧪 Escenarios de Prueba

1. ✅ **Login exitoso** con credenciales válidas
2. ✅ **Búsqueda de empleado** en módulo PIM
3. ✅ **Flujo completo E2E**: Login → Búsqueda → Logout

---

## 📚 Patrones Implementados

- **Page Object Model (POM)**: Separación de UI y lógica de tests
- **BasePage Pattern**: Herencia para reutilizar código común
- **BDD con Cucumber**: Escenarios en lenguaje natural (Gherkin)
- **Explicit Waits**: 15 segundos (ajustado para OrangeHRM)

---

## 🐛 Troubleshooting

### Error: `mvn: command not found`
Asegurarse que Maven esté instalado y en el PATH:
```bash
mvn --version
```

### Error: `ChromeDriver version mismatch`
WebDriverManager descarga automáticamente la versión correcta. Si persiste:
```bash
mvn clean test -U
```

### Tests fallan por timeout
OrangeHRM puede ser lento. Los waits están configurados en 15 segundos. Verificar:
- Conexión a internet estable
- Chrome actualizado
- OrangeHRM Demo disponible

---

## ⚠️ Limitaciones Conocidas

### Servidor Demo Compartido

OrangeHRM Demo ([https://opensource-demo.orangehrmlive.com/](https://opensource-demo.orangehrmlive.com/)) es un **servidor público compartido** con limitaciones de infraestructura.

**Comportamiento esperado:**
- ⏱️ **Timeouts intermitentes**: Algunos tests pueden fallar aleatoriamente con `TimeoutException`
- 🔄 **Variabilidad entre ejecuciones**: Los mismos tests pueden pasar o fallar en diferentes runs
- 🚦 **Limitación de carga**: El servidor tiene rate limiting y puede rechazar conexiones bajo alta carga

**Configuración recomendada:**

```bash
# Usar 2 threads máximo para evitar sobrecarga del servidor
mvn test -Dthread.count=2
```

**Valor por defecto**: Configurado en `pom.xml` con `<thread.count>2</thread.count>`

**Tests marcados como `@unstable`**:
- Pueden fallar intermitentemente por problemas del servidor
- No indica errores en el código de automatización
- Para excluirlos: ajustar tags en `TestRunner.java`

**Recomendación para producción**: Usar una instancia privada de OrangeHRM en lugar del servidor demo público.

---

## 👨‍💻 Autor

**Lucas Iriarte**
Diplomatura Testing Profesional - ICARO 2025

---

## 📄 Licencia

Este proyecto es parte de un trabajo académico para la Diplomatura en Testing Profesional.
