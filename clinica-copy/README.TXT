------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: clinica
PURPOSE OF PROJECT: pec asignatura poo
VERSION or DATE: 0.1
HOW TO START THIS PROJECT: crear objeto clinica, iniciar
AUTHORS: Francico Sanchez
USER INSTRUCTIONS: 

Esta version del programa es una version para desarrolladores,
tiene habilitadas funciones que no deben estar presentes en la version
final, en ésta el unico metodo publico de la clase clinica deberia de ser
iniciar() y a partir de aqui el usuario identificado puede acceder a los
metodos propios del nivel de acceso correspondiente a su nivel jerarquico.

Los metodos en la clase clinica, cuando pueden cambiar el estado de otro
objeto pide por parametro el usuario, que tiene que corresponder con la
accion que se intenta ejecutar, asi, un administrador no podra intentar
realizar una prueba o analizarla y un tecnico no podra intentar eliminar
o agregar un usuario.

La jerarquia de herencia se ha construido en tanto que se entiende que
que todos los usuarios van a ser personas y se ha construido para ello
una base de datos, individualizada en una clase aparte (BBDD) para mejorar la
cohesion y disminuir el acoplamiento, compuesta de un HashMap que con un
conjunto de clave-valor almacena al identificador unico de cada 
usuario (su dni) con el objeto persona propio de este identificador.
Todas las clases implementan la interfaz Serializable, de esta
manera se puede convertir un objeto BBDD en un stream de numeros binarios
para ser volcado en un archivo fuera del programa (o recuperado).
Esta funcion no esta implementada en la version actual del programa.
Tampoco se ha limitado el numero que representa al dni de forma alguna.

La base de datos mantiene un recuento de los usuarios con 3 variables
primitivas int y es capaz de devolver una lista de pacientes, tecnicos,
enfermeros o administradores sacando los valores de clave de Hashmap y
recorriendolos uno a uno, creando una lista cuando la variable polimorfica
que recorre esta ultima corresponde al tipo pedido en la funcion.

La base de datos tambien contiene una lista de los tratamientos que se han
efectuado en la clinica y una instancia de un objeto individual de clase
Nevera, que abstrae la logica detras del almacenamiento de las vacunas y
de la actualizacion del stock de este.
Estos metodos, aunque publicos, son inaccesibles en el programa fuera de
la clase BBDD y estos son alcanzables solo en el menu administrador
en la clase clinica, que, como indicado anteriormente necesita como parametro
una instancia de clase Administrador.

Los tratamientos que se pueden efectuar en la clinica pueden ser de dos tipos,
puede un usuario someterse a una bateria de pruebas o puede vacunarse. En mi
opinion, creo que se da a entender en el enunciado de la practica que estos
tienen que ser arboles jerarquicos individuales pero, desde un punto de
vista mas abstracto ambos comparten muchas similitudes y por lo tanto
los he anidado en una clase abstracta general llamada Tratamiento, que alberga
los atributos comunes a todos ellos, como puede ser el paciente que se va a
someter a dicho tratamiento (prueba o vacuna), la fecha en la que se realiza;
no cuando se pide, dicho tratamiento, el enfermero asignado y el tecnico asignado.
De esta manera se mantiene una relacion entre los caracteres involucrados en
la totalidad del proceso y facilita la busqueda de informacion a traves del
programa.
Una vez mas esta decision ayuda a la hora de tratar errores en el codigo y
explota el polimorfismo y las correcciones en tiempo de ejecucion.
Quizas el nombre sea lo mas inadecuado de la decision ya que no se ajusta
a la realidad el llamar a una vacuna "tratamiento", ya que no trata, si no
que previene, pero a falta de un termino mejor la clase cumple su funcion.

Las clases que heredan de esta clase abstracta son, Vacunas y Pruebas,
ambas abstractas.
De Vacunas heredan:
-Pfizer y Moderna:
 con una restriccion dada por un valor estatico en una clase abstracta de
 apoyo llamada redundantemente "estatico" donde se puede alterar todos estos
 parametros, actualmente esta restriccion impone un total de 21 dias pero
 si tuviese que ajustarse a la realidad (se ha reducido este tiempo de espera
 en la actualidad) solo tendria que cambiarse en un punto del codigo.

-JJ:
 esta clase difiere en las anteriores en que con una dosis se consigue
 completa inmunizacion, para lograr adaptar esta capacidad se ha cambiado,
 modificado y sobreescrito los metodos que simbolizan la vacunacion, haciendo
 que se reste la totalidad de las dos dosis del objeto vacuna JJ y que el
 estado del paciente pase a ser de completamente vacunado. Esto se comprueba
 tambien en tiempo de ejecucion con la habilidad polimorfica heredada de la
 clase Vacunas, haciendo estas 3 clases completamente compatibles entre si.
 
 Tengo que recalcar que toda la logica de vacunacion es exclusiva del
 enfermero y que el "tecnico asignado" se deja sin incializar.
 
Tomando por otra parte la clase Pruebas, extiende Tratamiento agregando 3
atributos que controlan el estado en cada momento de la prueba a realizar.
Estos tres atributos son del tipo primitivo boolean y se denominan resultado,
procesado y ejecutado.

El flujo de trabajo se sucede, en este orden:
    1º El administrador crea la prueba y la asigna (esto se vera mas en
    profundidad cuando se analice la clase Clinica)
    2º Cuando el enfermero empieza a trabajar, coge la prueba, comprueba
        a que subclase corresponde y aplica los metodos correspondientes
        cambiado el estado "ejecutado" a la prueba.
    3º Cuando el tecnico empieza a trabajar comprueba qué pruebas en su
        lista semanal tienen el valor a true en "ejecutado", una vez 
        encontrado un trabajo trabajable empieza a procesarlo, de nuevo
        el polimorfismo determina el flujo de trabajo.
        Si es una prueba detectora de virus y da positivo manda una alerta
        al modulo de confinamiento a traves de la clinica asignada a su
        misma persona y marca acordemente los valores de resultado y 
        procesado.
        
Para ambos trabajadores la lista semanal de trabajo se actualiza al comprobar
si tiene espacio para otro trabajo, el metodo correspondiente devuelve un
valor confirmativo si el tamaño de esta lista es menor al impuesto a la clase
de trabajador, si no fuese menor se recorreria la lista buscando las pruebas
con un tiempo superior a 7 dias y eliminandolas de la lista planSemanal para
almacenarlo en el de tratamientos. Dando lugar a otra prueba semanal a ser
almacenada.

En el caso del enfermero tambien se comprueba si el tratamiento es una vacuna.
Como no hay restricciones al numero de vacunas las acepta directamente.
Para ayudar a repartir la carga y que no se le asigne vacunas siempre al
mismo enfermero hay una variable estatica llamada umbral en la clase clinica.
Esta variable se inicializa a 1 en el momento de crear la clinica y una vez
que se intenta asignar una vacuna se utiliza para recorrer el total del grupo
de enfermeros, se compara con el numero de vacunas asignada a cada uno y 
hace aceptar a aquel que tenga un numero inferior a este umbral. Si despues
de una iteracion completa esta vacuna todavia no se ha podido asignar crece
en uno el valor de umbral y se recorre la lista una vez mas.

Con respecto al caso de los analisis serologicos, aunque se pueden pedir
individualmente en la clase clinica, lo mas normal es que las peticiones
salgan del modulo de confinamiento.

En la clase denominada ModuloConfinamiento se lleva a cabo todo el seguimiento
de los paciente que han dado positivo, como he comentado antes la alerta se
dispara desde el momento en que el resultado, procesado por el tecnico, da 
positivo y a traves de la clinica llega al modulo de confinamiento.
Estas alertas se mantienen en una lista hasta que el administrador decide
procesarla, al procesarla hace ciertas comprobaciones, como por ejemplo que no
haya sido un test repetido, recordemos que las pruebas de antigenos no tienen 
limite de dias. Al aceptar la prueba la guarda en una objeto de clase privada
denominado nodoConfinamiento. Este representa un dia completo de la clinica,
donde se almacenan las alertas procesadas y se almacena en una lista para su
posterior consulta, por ejemplo, cuando se quiera hacer el seguimiento de los
pacientes en confinamiento.
Por ello el nodo se compone de 3 listas LinkedList de tipo pruebas; procesar,
no_contestado y completado.
Estas pruebas van cambiando de listas conforme se las va procesando y al finalizar
el dia el ModuloConfinamiento las recorre para devolverlas a su estado original.
Al dia siguiente estaran listas para volver a recorrerse.
Al dia siguiente se recorre la lista y se mueven los nodos superiores a 10 dias
de antiguedad a una lista llamada pendienteSerologico.
Esta se puede recorrer para asignar los analisis correspondientes, si se consigue
asignar correctamente se elimina de la lista, en caso contrario permanece en ella.
En la documentacion explicita de la clase he dado una vision mas profunda de ésta
en la que hago unas observaciones sobre la capacidad de ampliacion de este modulo,
aún así, creo que cumple con lo que se pide de él en la práctica.


Por otra parte la clase Clinica representa a la clinica en si, desde ésta
se administran el resto de modulos y opciones.

Esta clase se compone de una instancia de los objetos base de datos (BBDD),
una instancia del ModuloConfinamiento y una variable de referencia Person que
representa el usuario "logueado" en un momento dado en la clinica.

Tambien almacena una variable umbral dinamica, de implementarse la funcionalidad
de carga de base de datos desde un archivo ésta tambien tendría que actualizarse
con el último valor para ahorrar el coste de calculo de éste. Si no, al intentar
asignar vacunas éste mismo valor se irá auto actualizandose pues se inicia en el
valor mas pequeño y aumentaria hasta encontrar a un enfermero con menos vacunas
pendientes que este valor.

Con respecto a sus metodos y funciones el único metodo que debería de permanecer
public es el de iniciar(). En este se comprueba el estado de la variable baseDatos.
De ser null se pide si se quiere cargar la base de pruebas (correspondiente al modo
debug) o si se quiere iniciar una nueva. En este punto se podria implementar la carga
desde archivo.
Al crear la base de datos se procede con el metodo cargaUsuario(), este comprueba
que haya usuarios en la base de datos y de no haberlos comienza creando un
usuario Administrador y pidiendo los datos correspondientes. Una vez finalizado
esto se carga el usuario Administrador como usuario de la clinica y se carga
el menu correspondiente a Administrador.
Si hubiese habido usuarios en la base de datos pide al controlador del programa
que introduzca el dni (el propio se deduce) y carga este usuario en la clinica.
En este punto seria facil comprobar que el usuario es el correcto pidiendo una
contraseña que puede estar almacenada en la propia ficha del usuario, aunque
para una practica no parece merecer el tiempo extra de comprobaciones.
Una vez mas, se comprueba en tiempo de ejecucion el tipo de usuario y se carga
acordemente.

Procediendo con los menus;


Menu administrador:
1 Gestion de usuarios
    Permite la creacion y eliminacion de usuarios.
2 Asignacion de tratamientos 
    Pruebas y vacunas
3 Visualizacion de datos
    Una vision general del estado de la clinica o de un usuario
    en especifico.
4 Modulo Confinamiento
    Permite el acceso a las funciones respectivas del modulo de confinamiento
5 Actualizacion del stock de vacunas
    Accede a la "nevera" a traves de la base de datos y actualiza el stock
    acordemente
6 Visualizacion planificacion de vacunas
    Recorre la lista de prioridades e imprime los datos
7 Cerrar sesion
    Pone a null el valor de usuario de la clinica y termina la sesion.
    
Menu trabajador

1 Visualizacion de datos plan Semanal
    Imprime los datos del plan Semanal
2 Visualizacion de tratamientos completados
    Imprime los datos de los tratamientos completados
3 Visualizacion datos de paciente
    Pide un dni de un paciente y muestra la informacion de éste
4 Registro y actualizacion de tratamientos
    Comprueba la instancia de usuario en tiempo de ejecucion;
        de ser tecnico comienza a procesar las pruebas
        de ser enfermero pregunta si quiere trabajar en las
            vacunas o en las pruebas y actualiza su trabajo
            acordemente.

Menu Paciente
A un paciente la clinica no le permite ver sus datos, le pide que contacte con
un trabajador.










