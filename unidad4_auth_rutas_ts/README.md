# Justificación del uso de Vite + TypeScript (configuracion minima)

Este proyecto usa Vite para desarrollo y build del frontend.

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

## Refactor futuro posible (login y registro)

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
