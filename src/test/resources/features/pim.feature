# language: es
Característica: Gestión de empleados en módulo PIM
  Como usuario autenticado de OrangeHRM
  Quiero buscar empleados en el módulo PIM
  Para gestionar la información de recursos humanos

  Antecedentes:
    Dado que estoy en la página de login de OrangeHRM
    Cuando ingreso el usuario "Admin"
    Y ingreso la contraseña "admin123"
    Y hago click en el botón Login
    Entonces debería estar en el Dashboard

  Escenario: Buscar un empleado existente
    Cuando navego al módulo PIM
    Entonces debería estar en la página PIM
    Cuando busco al empleado "John"
    Entonces deberían mostrarse resultados de búsqueda
