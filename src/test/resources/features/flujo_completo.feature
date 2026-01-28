# language: es
Característica: Flujo completo en OrangeHRM
  Como usuario del sistema OrangeHRM
  Quiero poder autenticarme, buscar empleados y cerrar sesión
  Para gestionar la información de recursos humanos de forma eficiente

  # ========================================
  # ESCENARIO 1: LOGIN EXITOSO
  # ========================================
  Escenario: Login exitoso con credenciales válidas
    Dado que estoy en la página de login de OrangeHRM
    Cuando ingreso el usuario "Admin"
    Y ingreso la contraseña "admin123"
    Y hago click en el botón Login
    Entonces debería estar en el Dashboard
    Y debería ver el título "Dashboard"

  # ========================================
  # ESCENARIO 2: BUSCAR EMPLEADO
  # ========================================
  Escenario: Buscar un empleado existente en el módulo PIM
    Dado que estoy en la página de login de OrangeHRM
    Cuando ingreso el usuario "Admin"
    Y ingreso la contraseña "admin123"
    Y hago click en el botón Login
    Entonces debería estar en el Dashboard
    Cuando navego al módulo PIM
    Entonces debería estar en la página PIM
    Cuando busco al empleado "John"
    Entonces deberían mostrarse resultados de búsqueda

  # ========================================
  # ESCENARIO 3: FLUJO COMPLETO E2E
  # ========================================
  Escenario: Flujo completo - Login, búsqueda de empleado y logout
    Dado que estoy en la página de login de OrangeHRM
    Cuando ingreso el usuario "Admin"
    Y ingreso la contraseña "admin123"
    Y hago click en el botón Login
    Entonces debería estar en el Dashboard
    Y debería ver el título "Dashboard"
    Cuando navego al módulo PIM
    Entonces debería estar en la página PIM
    Cuando busco al empleado "John"
    Entonces deberían mostrarse resultados de búsqueda
    Cuando cierro sesión
    Entonces debería estar de vuelta en la página de login
