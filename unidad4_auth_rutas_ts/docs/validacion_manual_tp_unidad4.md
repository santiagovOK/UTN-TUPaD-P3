# Validacion manual TP Unidad 4

Esta guia cubre que probar y como probarlo para validar consignas y rubrica.

Referencia:
- docs/tp_unidad4_consignas.md

## 1. Preparacion

1. Abrir terminal en la carpeta del proyecto.
2. Ejecutar:

```bash
cd UTN-TUPaD-P3/unidad4_auth_rutas_ts
pnpm dev
```

3. Abrir la URL local que informa Vite (ejemplo: http://localhost:5175/).
4. Abrir DevTools en el navegador.
5. Ir a estas pestañas:
- Application -> Local Storage
- Console

## 2. Reset inicial

Hacerlo desde la Console del navegador antes de probar:

```js
localStorage.removeItem("users");
localStorage.removeItem("userData");
```

Validar que quedo limpio:

```js
localStorage.getItem("users");   // debe devolver null
localStorage.getItem("userData"); // debe devolver null
```

## 3. Paso 1 - Registro de usuarios

Pagina objetivo:
- src/pages/auth/registro/registro.html

### 3.1 Validacion de campos requeridos

1. Ir a registro.
2. Hacer click en Registrarme sin completar nada.
3. Resultado esperado: el navegador bloquea envio por required.

### 3.2 Registro exitoso

1. Completar un email nuevo, por ejemplo: user1@test.com.
2. Completar password, por ejemplo: 123456.
3. Click en Registrarme.
4. Resultado esperado:
- alerta de exito
- redireccion a login

### 3.3 Verificar persistencia en users

En Console:

```js
JSON.parse(localStorage.getItem("users") || "[]")
```

Resultado esperado:
- array con al menos un objeto
- cada objeto tiene email, password, role
- role debe ser "client" por defecto

### 3.4 Verificar duplicado

1. Volver a registro.
2. Intentar registrar nuevamente user1@test.com.
3. Resultado esperado:
- alerta de email duplicado
- no se agrega otro registro igual

### 3.5 Verificar persistencia tras recarga

1. Recargar pagina.
2. Ejecutar otra vez:

```js
JSON.parse(localStorage.getItem("users") || "[]")
```

3. Resultado esperado: el array sigue presente.

## 4. Paso 2 - Login y sesion

Pagina objetivo:
- src/pages/auth/login/login.html

### 4.1 Credenciales invalidas

1. Ingresar email/password que no existan.
2. Click en Ingresar.
3. Resultado esperado:
- mensaje de credenciales invalidas
- sin redireccion a home

### 4.2 Credenciales validas

1. Ingresar usuario registrado (ejemplo user1@test.com / 123456).
2. Click en Ingresar.
3. Resultado esperado:
- redireccion a home correspondiente al role

### 4.3 Verificar userData

En Console:

```js
JSON.parse(localStorage.getItem("userData") || "null")
```

Resultado esperado:
- objeto con email, role, loggedIn
- loggedIn debe ser true

### 4.4 Verificar persistencia de sesion

1. Recargar home.
2. Resultado esperado: mantiene acceso a la zona protegida del mismo role.

## 5. Paso 3 - Proteccion de rutas (Guard)

Archivos clave:
- src/main.ts
- src/pages/admin/home/home.ts
- src/pages/client/home/home.ts

### 5.1 Sin sesion -> no acceso por URL

1. Limpiar sesion:

```js
localStorage.removeItem("userData")
```

2. Ir directo a /src/pages/admin/home/home.html.
3. Resultado esperado: redireccion a login.
4. Ir directo a /src/pages/client/home/home.html.
5. Resultado esperado: redireccion a login.

### 5.2 Sesion client intentando admin

1. Setear sesion client desde Console:

```js
localStorage.setItem("userData", JSON.stringify({
   email: "user1@test.com",
   role: "client",
   loggedIn: true
}))
```

2. Ir directo a /src/pages/admin/home/home.html.
3. Resultado esperado: bloqueo y redireccion fuera de admin.

### 5.3 Sesion admin intentando client

1. Setear sesion admin desde Console:

```js
localStorage.setItem("userData", JSON.stringify({
   email: "admin@test.com",
   role: "admin",
   loggedIn: true
}))
```

2. Ir directo a /src/pages/client/home/home.html.
3. Resultado esperado: comportamiento segun regla definida por la app (redireccion).

### 5.4 userData invalido

1. Corromper sesion:

```js
localStorage.setItem("userData", "{bad json")
```

2. Ir a una ruta protegida.
3. Resultado esperado: redireccion a login.

### 5.5 Logout

1. Entrar a una home logueado.
2. Click en boton Logout.
3. Validar en Console:

```js
localStorage.getItem("userData") // debe ser null
```

4. Ir por URL a admin/client.
5. Resultado esperado: redireccion a login.

## 6. Chequeos tecnicos TypeScript

En terminal del proyecto:

```bash
pnpm run typecheck
```

Resultado esperado:
- sin errores de TypeScript.

## 7. Chequeos de carga de scripts

1. Abrir src/pages/auth/login/login.html.
2. Verificar que la etiqueta del script sea modulo.
3. En Console, confirmar que NO aparezca:
- Cannot use import statement outside a module

## 8. Cierre segun rubrica

Funcionalidad:
1. Registro y login validan datos contra localStorage.
2. No se aceptan duplicados en registro.
3. Sesion se crea correctamente en userData.

Proteccion:
1. Rutas admin/client bloqueadas para no autenticados.
2. Restriccion por rol activa por URL.
3. Logout corta acceso protegido.

TypeScript:
1. Uso de interfaces y tipos en auth/roles.
2. typecheck sin errores.

Entrega:
1. Estructura de carpetas respetada.
2. Proyecto ejecuta y flujo completo funciona.
