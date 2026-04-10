
# Trabajo Práctico - Unidad 4 (TypeScript) - Food Store

✨ Estudiante

- Nombre: Varela, Santiago Octavio
- Comisión: M25 C3-13
- Email institucional: santiago.varela@tupad.utn.edu.ar

Repositorio donde podrán encontrar mis trabajos de Programación III: https://github.com/santiagovOK/UTN-TUPaD-P3

## Objetivo General

Evolucionar la aplicación dinámica "Food Store" hacia un sistema con **Autenticación y Roles**. El alumno deberá implementar un flujo de seguridad que proteja el contenido según el tipo de usuario, sustituyendo la carga abierta de datos por un acceso restringido mediante TypeScript y localStorage.

## Instalación de dependencias

Requisitos previos:

- Node.js (versión 18 o superior recomendada).
- pnpm instalado globalmente.

Pasos:

1. Ubicarse en la carpeta del proyecto.
2. Instalar dependencias:

```bash
pnpm install
```

3. Levantar el entorno de desarrollo:

```bash
pnpm dev
```

## Validaciones Manuales

Pueden ver el paso a paso de las validaciones manuales que seguí para el cumplimiento de las consignas y los criterios evaluativos [aquí](docs/validacion_manual_tp_unidad4.md)

## Uso de console.log para debugging

Durante el desarrollo se utilizaron console.log para seguir el flujo de registro, login, guard de rutas y logout, con prefijos por contexto ([registro], [login], [guard]) para facilitar la lectura en consola.
Estos logs se usaron solo como soporte de depuración y se evitó exponer datos sensibles.

<details>
<summary> Justificación del uso de Vite + TypeScript (configuracion minima) </summary>

Este proyecto usa Vite para desarrollo y build del frontend.

Nota personal: creo que hace falta aclarar esto porque no estuvo muy claro en los materiales teóricos su uso. Si bien es mencionado dentro del video explicativo para este trabajo y también dentro del repositorio base `https://github.com/chiro45/proteger_rutas`, no hubo mucha explicación sobre Vite en contenidos previos.

## Por que usar Vite aqui

- Permite trabajar con archivos `.ts` en el navegador durante desarrollo.
- Levanta un servidor rapido para probar rutas HTML (login, registro, home).
- Evita errores al abrir archivos locales sin servidor.

## package.json necesario

Para que no haya conflicto de modulos, se usa ESM y scripts de Vite:

```json
{
	"type": "module",
	"scripts": {
		"dev": "vite",
		"build": "vite build",
		"preview": "vite preview",
		"typecheck": "tsc --noEmit"
	}
}
```

Ademas, Vite debe estar instalado como dependencia de desarrollo usando `pnpm`, como lo indica el repositorio de ejemplo `https://github.com/chiro45/proteger_rutas`.

## tsconfig.json necesario

Para evitar errores como "ECMAScript imports and exports cannot be written in a CommonJS file", el `tsconfig.json` debe estar alineado a Vite:

```json
{
	"compilerOptions": {
		"target": "ES2020",
		"module": "ESNext",
		"moduleResolution": "Bundler",
		"lib": ["ES2020", "DOM", "DOM.Iterable"],
		"types": ["vite/client", "node"],
		"strict": true,
		"noUncheckedIndexedAccess": true,
		"exactOptionalPropertyTypes": true,
		"verbatimModuleSyntax": true,
		"isolatedModules": true,
		"noUncheckedSideEffectImports": true,
		"moduleDetection": "force",
		"skipLibCheck": true
	},
	"include": ["src", "vite.config.ts"]
}
```

## Verificacion rapida

1. Ejecutar `pnpm run typecheck`.
2. Si no aparecen errores, la configuracion de modulos esta correcta.
3. Levantar entorno con `pnpm dev`.

</details>

## Aplicacion de CSS en esta unidad

Para esta entrega se creo `src/styles.css` como hoja global del proyecto.

La configuracion base que originalmente estaba en `src/pages/auth/login/login.css` (archivo construido para la actividad de la unidad 2) se movio a ese archivo global para que quede disponible en todas las vistas.

Con este criterio, los estilos generales se mantienen para todo el proyecto y cada `.html` particular puede sumar luego sus estilos especificos sin duplicar la base.

Como en este trabajo practico no se exigia una implementacion de estilos puntual, la aplicacion de CSS se limito a esta organizacion general.

## Refactorización futura que podría realizarse (login y registro)

Como mejora de mantenibilidad, se puede simplificar la logica compartida entre `login.ts` y `registro.ts`.

Objetivo principal:

- Reducir duplicacion en lectura/escritura de `localStorage`.
- Centralizar validaciones comunes (por ejemplo, normalizacion de email y parseo seguro).
- Mantener cada pagina enfocada en su responsabilidad:
	- `registro.ts`: alta de usuarios.
	- `login.ts`: validacion de credenciales y creacion de sesion.

Posible direccion tecnica:

- Mover helpers compartidos a `src/utils/localStorage.ts` (por ejemplo, `getUsers`, `saveUsers`, `findUserByCredentials`).
- Reutilizar esas funciones en ambas paginas para evitar inconsistencias.
- Conservar los `console.log` de depuracion en puntos clave, pero evitando mensajes duplicados.
