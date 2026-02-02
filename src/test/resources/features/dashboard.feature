# language: es
@dashboard @smoke
Característica: Validación del Dashboard de OrangeHRM
  Como usuario autenticado de OrangeHRM
  Quiero verificar que el Dashboard carga correctamente
  Para confirmar el acceso exitoso al sistema

  Antecedentes:
    Dado que estoy en la página de login de OrangeHRM
    Cuando ingreso el usuario "Admin"
    Y ingreso la contraseña "admin123"
    Y hago click en el botón Login
    Entonces debería estar en el Dashboard

  # ============================================================
  # SCENARIOS TRADICIONALES: Validaciones únicas del Dashboard
  # ============================================================
  @positive @critical
  Escenario: Validar carga completa del Dashboard
    Entonces debería ver el título "Dashboard"
    Y debería ver los widgets del Dashboard
    Y debería ver el menú lateral de navegación

  @positive
  Escenario: Validar presencia del menú de navegación
    Entonces debería ver el menú lateral de navegación
    Y el menú lateral debería contener los módulos principales

  # ============================================================
  # DATA-DRIVEN: Validación de widgets individuales
  # ============================================================
  @positive @data-driven @unstable
  Esquema del escenario: Validar widget "<nombre_widget>" en el Dashboard
    Entonces debería ver el widget "<nombre_widget>"

    @critical @wip
    Ejemplos: Widgets principales del Dashboard
      | nombre_widget  |
      | Time at Work   |
      | My Actions     |

    @secondary @wip
    Ejemplos: Widgets de acceso rápido
      | nombre_widget  |
      | Quick Launch   |
