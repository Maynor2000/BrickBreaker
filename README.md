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
