# Práctica 1 - Simulador de Autómata de Pila

Este proyecto consiste en un simulador de Autómatas de Pila: el programa lee la definición de un autómata desde un fichero, procesa una lista de cadenas y comprueba si cada cadena es aceptada por el autómata. Opcionalmente genera una traza detallada de la búsqueda por cada cadena.

## Estructura del repositorio

- `src/` - código fuente Java organizado por paquetes (entrada, núcleo, componentes, utilidades, etc.).
- `bin/` - salida de compilación (generada por los scripts).
- `automatons-examples/` - ejemplos de ficheros de autómata.
- `strings-examples/` - ejemplos de ficheros de cadenas a comprobar.
- `runProgram.sh` - script minimo que compila y lanza `src.automaton.Main`, pasando todos los argumentos literales.

Principales clases:

- `src.automaton.Main` - punto de entrada del programa.
- `src.automaton.utils.ArgumentsParser` - parseador de argumentos de línea de comandos.
- `src.automaton.io.ReadStackAutomaton` - lector del fichero que describe el autómata.
- `src.automaton.simulation.StackAutomatonSimulator` - coordina la simulación sobre varias cadenas.
- `src.automaton.core.StackAutomaton` - implementación del autómata de pila.

## Autómata de Pila

El autómata de pila diseñado es por vaciado de pila. Además, la búsqueda de cada cadena se realiza en produndidad, ejecutando primero las transiciones que consumen un símbolo de la cadena de entrada (por orden) y posteriormente las épsilon-transiciones (también en orden).

## Requisitos

- Java JDK 11 o superior (recomendado). Algunas utilidades del código usan APIs disponibles a partir de Java 11 (por ejemplo `String.repeat`).
- bash (scripts de ejecución proporcionados).

## Compilar y Ejecutar

El proyecto se compila con `javac`. El script incluido invoca la compilación automáticamente, pero puedes compilar manualmente:

```bash
mkdir -p bin
javac -g -d bin $(find src -name "*.java")
```

La opción `-g` incluye símbolos de depuración (útil para depurar en VS Code).

Hay dos maneras sencillas de ejecutar:

1) Usando `runProgram.sh` para compilar y ejecutar, pasando los argumentos que necesites:

```bash
# ejemplos
./runProgram.sh -a automatons-examples/example1.txt -s automatons-examples/example1.txt

# con traza
./runProgram.sh -a automatons-examples/example1.txt -s automatons-examples/example1.txt -t
```

2) Compilar manualmente y ejecutar java directamente:

```bash
# compilar
mkdir -p bin
javac -g -d bin $(find src -name "*.java")

# ejecutar (todos los args pasados después se envían a main)
java -cp bin src.automaton.Main -a automatons-examples/example1.txt -s automatons-examples/example1.txt -t
```

> **Nota:** `runProgram.sh` simplemente compila y ejecuta automáticamente la misma clase `src.automaton.Main`, reenviando todos los parámetros que les pases.

## Uso del programa

El programa principal espera argumentos en este formato:

- `-a <automaton_file>` : fichero que describe el autómata.
- `-s <strings_file>`   : fichero que contiene las cadenas a comprobar (separadas por espacios o saltos de línea según la implementación de lectura).
- `-t`                  : (opcional) activar la generación de traza detallada.

Ejemplo completo:

```bash
./runProgram.sh -a automatons-examples/example1.txt -s automatons-examples/example1.txt -t
```

Si el parseador detecta `-h` o `--help` imprimirá la ayuda y saldrá.

## Ejemplos incluidos

- `automatons-examples/` y `strings-examples` contienen varios ejemplos de autómatas y cadenas de prueba. Úsalos para verificar el funcionamiento en tu entorno.