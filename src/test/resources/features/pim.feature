# language: es
@pim @regression
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
    Cuando navego al módulo PIM
    Entonces debería estar en la página PIM

  # ============================================================
  # SCENARIOS TRADICIONALES: Casos únicos de búsqueda
  # ============================================================
  @negative @unstable
  Escenario: Buscar con campo vacío
    Cuando hago click en buscar sin ingresar datos
    Entonces deberían mostrarse múltiples resultados de búsqueda

  @positive @critical @unstable
  Escenario: Búsquedas múltiples consecutivas
    Cuando busco al empleado "Amelia"
    Entonces deberían mostrarse resultados de búsqueda
    Cuando limpio el campo de búsqueda
    Y busco al empleado "Charles"
    Entonces deberían mostrarse resultados de búsqueda

  # ============================================================
  # DATA-DRIVEN: Búsqueda exitosa de empleados existentes
  # ============================================================
  @positive @data-driven @unstable
  Esquema del escenario: Buscar empleado existente "<nombre_empleado>"
    Cuando busco al empleado "<nombre_empleado>"
    Entonces deberían mostrarse resultados de búsqueda

  @happy-path @smoke
  Ejemplos: Empleados comunes en el sistema
    | nombre_empleado |
    | Amelia          |
    | Charles         |

  @happy-path
  Ejemplos: Otros empleados registrados
    | nombre_empleado |
    | Emily           |
    | James           |

  # ============================================================
  # DATA-DRIVEN: Búsquedas sin resultados
  # ============================================================
  @negative @data-driven
  Esquema del escenario: Validar búsqueda sin resultados para "<nombre_invalido>"
    Cuando busco al empleado "<nombre_invalido>"
    Entonces no deberían mostrarse resultados de búsqueda

  @invalid-data
  Ejemplos: Empleados que no existen en la base de datos
    | nombre_invalido          |
    | EmpleadoQueNoExiste9999  |
    | XYZ999NoExiste           |
    | 12345InvalidName         |

  @edge-cases
  Ejemplos: Búsquedas con caracteres especiales inválidos
    | nombre_invalido      |
    | ****SpecialChars**** |
    | @@@InvalidChars@@@   |
    | ###NoValido###       |
