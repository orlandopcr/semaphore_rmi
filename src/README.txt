Integrantes: 

- Ivan Caro 201273545-9

- Orlando Contreras 201204760-9


Instrucciones de ejecución:

1) Las clases son compiladas mediante el comando "make" en el directorio de las clases.

2) Una vez compiladas las clases se debe ejecutar el servidor mediante "java Server a b", donde a es un entero que
	define el numero total de nodos (semáforos), por otra parte b hace referencia al id del semáforo que
	parte con el token. (b por consecuencia debe ser menor que a dado que los ids se entregan de forma incremental y lógica).

3) Una vez que el servidor se ecuentra en ejecución se deben abrir distintos terminales (procesos) desde los cuales se ejecutara el
	comando "java Process a b c d " donde a es el id del nodo a registrar en la red, se solicita que este siga la lógica de lo 
	ingresado en Server con respecto al total de nodos (para no romper el programa), el programa se preocupa por si solo que los id 
	no sean repetidos. Por otra parte b es el total de nodos, c es el delay* y d es si el nodo porta el token o no. 
	
	*delay se considero como el tiempo en que el programa se demora en consultar por el Token.

4) Ya registrados todos los nodos, es posible apretar ENTER en cada terminal, para que empiece a correr el reloj de sus respectivos delays
	y la ejecución del algoritmo en cuestión. 


Diseño de la solución:

Para implementar esta solución se ocupó RMI de Java, de forma que el token se pasó de nodo a nodo dependiendo de las solicitudes 
que cada uno generaba. Para esto se usa una interface que conecta a cada nodo al servidor, dentro del cual se tiene la información
del estado del token y de los nodos. Así se genera una red totalmente conectada

