# language: es
Característica: Autenticación en OrangeHRM
  Como usuario del sistema OrangeHRM
  Quiero poder autenticarme con credenciales válidas
  Para acceder a las funcionalidades del sistema

  Antecedentes:
    Dado que estoy en la página de login de OrangeHRM

  Escenario: Login exitoso con credenciales válidas
    Cuando ingreso el usuario "Admin"
    Y ingreso la contraseña "admin123"
    Y hago click en el botón Login
    Entonces debería estar en el Dashboard
    Y debería ver el título "Dashboard"

  Escenario: Login fallido con credenciales inválidas
    Cuando ingreso el usuario "UsuarioInvalido"
    Y ingreso la contraseña "claveIncorrecta"
    Y hago click en el botón Login
    Entonces debería ver un mensaje de error
    Y debería permanecer en la página de login

  Escenario: Login fallido con campo de usuario vacío
    Cuando ingreso la contraseña "admin123"
    Y hago click en el botón Login
    Entonces debería ver un mensaje de campo requerido
    Y debería permanecer en la página de login

  Escenario: Login fallido con campo de contraseña vacío
    Cuando ingreso el usuario "Admin"
    Y hago click en el botón Login
    Entonces debería ver un mensaje de campo requerido
    Y debería permanecer en la página de login

  Escenario: Login fallido con ambos campos vacíos
    Cuando hago click en el botón Login
    Entonces debería ver mensajes de campos requeridos
    Y debería permanecer en la página de login
