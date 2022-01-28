# TestZemoga
Lo que hace esta aplicación es cargar una lista de posts y mostrarlos en pantalla. Para cargar los posts hay que pulsar el boton de la parte superior derecha.
Luego de que se muestre la lista de posts de verá que los 20 primeros tienen una marca que indican que no han sido leidos. 
Al pulsar en algun post se entrará en la vista de detalle, donde se verá el contenido y los comentarios asociados a dicho post, además de poder marcarlo como favorito pulsando en el botón de la parte superior derecha.
Los posts pueden ser borrados uno por uno al hacer swipe hacia la derecha en el elemento que se quiera eliminar. Tambien se puede borrar toda la lista pulsando el boton flotante de la parte inferior derecha.
Los posts que ya han sido leidos no tendran la marca azul y los que han sido marcados como favoritos tendran una estrella amarilla a su derecha. 
La lista de favoritos se puede ver al pulsar en la pestaña con dicho nombre.

La arquitectura usada para este proyecto es la Clean Architecture, que divide el código en los módulos: 
App: Incluye la capa de presentación (vistas, fragments, viewmodels) y de framework (Base de datos y servicios web)
Data: Alberga los repositorios y los datasources
Domain: Contiene las estructuras fundamentales de la app
UseCases: Son las clases que sirven de puente entre la capa de presentación y la de data

El patrón de presentacion usado es el Model-View-ViewModel.
La libreria usada para la persistencia de datos es Room.
La libreria para la comunicación con las APIs es Retrofit.
