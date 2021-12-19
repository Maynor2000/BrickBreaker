# BrickBreaker
> @autor Maynor Alvarez Acopa 
> @colaborador:

## bugs
* Los colider de los bloques se ven afectados por que el colider es un intervalo de tamano y no un objeto como en unity
* por el problema anterior la ball o balon puede colisionar mas de una vez en el mismo bloque o colisionar varios al rebote
* el juego no esta programado para terminar al romper todos los bloques
## Mecanismo
* preciona 'A' o 'D' para desplazar la barra hacia las respectivas direcciones
* en el menu se encuentran los botones para manejar la musica
## Documentacion
### estructura del proyecto
* controller
* Exceptions
    * IndexOutLineBlock.java
* Main.java
* Model
* Service
    * Ball.java
    * Block.java 
    * GeneratorLevel.java
    * Player.java
    * Bar.java
    * Bloque.java
    * MoveToWindows.java
    * ScreenControl.java
* view
    * Menu.java
### GeneratorLevel.java
~~~
Esta clase genera objetos de tipo Block la cual guarda la posicion, vida, color y el frame con las caracteristicas
anteriores los onjetos son guardados como nodos a los cuales se van anadiendo a un ArrayList llamado bloks que
contiene todos los bloques.
esta clase contiene todos los metodos (funciones) que crean su color, posicion, tamano del bloque.
~~~
### MoveToWindows.java
~~~
Esta clase se encarga de crear el movimiento de el balon y la barra los cuales estan dentro de un blucle while true
por lo que se emplea el uso de los hilos, ademas implementa la interfaz KeyListener para que la barra se mueva al 
precionar una tecla.
el balon se mueve de forma autonoma.
~~~
### ScreenControl.java
~~~
Esta clase se encarga de gestionar la pantalla del cliente para que sea adaptable a cualquier tipo de resolucion.
~~~
### Player.java
~~~
Esta clase contiene funciones para la reproduccion de sonidos waw.
~~~
### others.java
~~~
las demas clases solo son JFrame para crear los elementos de el juego segun su nombre de la clase.
~~~
