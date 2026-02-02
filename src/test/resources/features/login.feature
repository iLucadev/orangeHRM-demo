# language: es
@login @regression
Característica: Autenticación en OrangeHRM
  Como usuario del sistema OrangeHRM
  Quiero poder autenticarme con credenciales válidas
  Para acceder a las funcionalidades del sistema

  Antecedentes:
    Dado que estoy en la página de login de OrangeHRM

  # ============================================================
  # SCENARIO TRADICIONAL: Login exitoso (caso único)
  # ============================================================
  @positive @smoke
  Escenario: Login exitoso con credenciales válidas
    Cuando ingreso el usuario "Admin"
    Y ingreso la contraseña "admin123"
    Y hago click en el botón Login
    Entonces debería estar en el Dashboard
    Y debería ver el título "Dashboard"

  # ============================================================
  # DATA-DRIVEN: Login fallido con múltiples casos de error
  # ============================================================
  @negative @data-driven
  Esquema del escenario: Login fallido con credenciales inválidas
    Cuando ingreso el usuario "<usuario>"
    Y ingreso la contraseña "<password>"
    Y hago click en el botón Login
    Entonces debería ver un mensaje de error
    Y debería permanecer en la página de login

    @invalid-credentials
    Ejemplos: Credenciales incorrectas
      | usuario          | password        |
      | UsuarioInvalido  | claveIncorrecta |
      | Admin            | wrongPassword   |
      | FakeUser         | admin123        |

  # ============================================================
  # DATA-DRIVEN: Validación de campos requeridos
  # ============================================================
  @negative @data-driven @unstable
  Esquema del escenario: Login fallido con campos vacíos
    Cuando ingreso el usuario "<usuario>"
    Y ingreso la contraseña "<password>"
    Y hago click en el botón Login
    Entonces debería ver un mensaje de campo requerido
    Y debería permanecer en la página de login

    @edge-cases
    Ejemplos: Un campo vacío
      | usuario | password  |
      |         | admin123  |
      | Admin   |           |

    @critical
    Ejemplos: Ambos campos vacíos
      | usuario | password  |
      |         |           |
