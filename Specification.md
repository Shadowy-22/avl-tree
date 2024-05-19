## Especificacion del Árbol AVL

### Descripción

Se requiere implementar un árbol AVL que utilice dos claves:
1. **Clave de Navegación**: Utilizada para navegar y mantener el balance del árbol.
2. **Clave Asociada**: Asociada a la clave de navegación y contiene datos de una persona.

### Especificaciones

El TAD (Tipo Abstracto de Datos) deberá almacenar, como mínimo, la siguiente información de cada persona:
- **DNI**
- **Nombre**
- **Apellido**
- **Dirección**
- **Teléfono**

### Requisitos Funcionales

1. **Inserción y Búsqueda**:
   - Implementar métodos para insertar y buscar personas en el árbol AVL utilizando la clave de navegación.

2. **Equilibrio del Árbol**:
   - Incluir un método para mantener el equilibrio del árbol después de cada inserción o eliminación, siguiendo las propiedades del árbol AVL.

3. **Operaciones Básicas**:
   - Métodos para eliminar nodos, encontrar el mínimo y máximo, y realizar recorridos en inorden, preorden y postorden.

### Ejemplo de Uso

Cada nodo del árbol contendrá una estructura de datos de persona asociada a la clave de navegación. El árbol deberá ajustarse automáticamente para mantener su balance después de cada operación de inserción o eliminación.

### Notas Adicionales

- Asegurarse de que todas las operaciones mantengan el balance del árbol AVL.
