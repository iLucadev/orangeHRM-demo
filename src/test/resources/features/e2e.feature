# language: es
@e2e @smoke
Característica: Flujo completo E2E en OrangeHRM
  Como usuario del sistema OrangeHRM
  Quiero realizar un flujo completo desde login hasta logout
  Para validar la integración entre módulos

  @positive
  Escenario: Flujo completo - Login, búsqueda y logout
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
