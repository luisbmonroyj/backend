# backend_Auth_Ciclo4AGrupo3Equipo1
## Backend Java SpringBoot

### Para administrar Roles
- GET **servidor/rol**: Muestra los roles creados, id, nombre y descripcion
- GET **servidor/rol/{id_rol}**: Muestra el rol con id = id_rol, su id, nombre y descripcion
- DELETE **servidor/rol?_id={_id que se va a eliminar}**: borra el rol segun su id, debe existir
    *Cuando se borre un rol, los usuarios que lo tenian toman el rol "ciudadano"
    NO SE PUEDE BORRAR EL ROL CIUDADANO, esto provoca que el rol de los ciudadanos pase a null y no tengan permisos, 
    por ende se restringio*
- PUT **servidor/rol**: Se debe enviar un JSON con los datos:
  - _id
  - nombre
  - descripcion 

  *Si el _id no existe, reporta el error*
  
- POST **servidor/rol**: Se debe enviar un JSON con los datos:
  - nombre
  - descripcion
  
  *Si el rol ya existe, reporta el error*
  *El _id se crea automaticamente*
  
### Para administrar Permisos
*Permisos es una tabla que establece cual metodo CRUD está disponible. En teoría debe haber 4 metodos para cada endpoint
El metodo se almacena en MAYUSCULA y en descripcion, la URI completa del servicio*

- GET **servidor/permiso**: Muestra los permisos creados, id, metodo y la ruta
- DELETE **servidor/permiso?id={_id_permiso a borrar}**: borra el permiso segun su id, debe existir
    - Cuando se borre un permiso, los usuarios no podran acceder DE NINGUNA MANERA al metodo eliminado
    - SE RECOMIENDA NO BORRAR, en vez de esto, es mejor editar su informacion 
- PUT **servidor/permiso**: Se debe enviar un JSON con los datos:
    - _id
    - metodo
    - ruta

  *Si el _id no existe, reporta el error*
  - POST **servidor/permiso**: Se debe enviar un JSON con los datos:
      - metodo
      - ruta

  *El _id se crea automaticamente*
  
### Para administrar Rol/Permisos
*Rol/Permisos es una tabla intermedia que asigna un permiso de acceso a un rol especifico,
por ejemplo, existe un rol ciudadano que NO puede eliminar usuarios, pero hay un administrador que si puede.
entonces un campo en esta tabla tendra el id_rol del administrador y el id_permiso del endpoint (DELETE **servidor/usuario**)

- GET **servidor/rolPermiso**: Muestra los id_permiso asignados a un id_rol
- GET **servidor/rolPermiso/{id_rolpermiso}**: Muestra el rolpermiso con id = id_rolpermiso, su id, el rol y permiso asociados
- DELETE **servidor/rolPermiso/{id}**: borra el rol/permiso segun su id, debe existir
  - Cuando se borre un rol/permiso, los usuarios con un rol especifico no podran acceder al metodo eliminado
  - SE RECOMIENDA NO BORRAR, en vez de esto, es mejor editar su informacion
- PUT **servidor/rolPermiso/{id}/rol/{_id_rol}/permiso/{_id_permiso}**: a un rol/permiso se le pueden cambiar el rol al que habilita y el permiso que autoriza. Todos los datos van en la URI, NO SERA IMPLEMENTADO EN FRONTEND, SOLO POR URL
  *Si el id de alguna de las entidades (rol o permiso) no existe, reporta el error*
- POST **servidor/rolPermiso/{_id_rol}/permiso/{_id_permiso}**: Los datos van en la URI del servicio

  *El id se crea automaticamente*
  
### Para administrar Usuarios
Usuario es toda aquella persona que va a usar el sistema: administrador (registador), jurado o ciudadano
Por defecto, los usuarios se crean con rol ciudadano y es el admin quien asigna roles diferentes

- GET **servidor/usuario**: Muestra los usuarios creados, cedula (@id), nombre de usuario (correo electronico), 
  contrasena (cifrado SHA256) y el rol que tiene asignado
- DELETE **servidor/usuario?cedula={cedula de usuario que se quiere borrar}**: borra el usuario segun su cedula, debe existir
  - SE RECOMIENDA NO BORRAR, en vez de esto, es mejor editar su informacion
- PATCH **servidor/usuario**: debe enviarse un json con la informacion actualizada:
    - contrasena (sin cifrar)
  
  *Si la cedula del usuario no existe, reporta el error*
- POST **servidor/usuario/**: Los datos van en un JSON, los necesarios son:
  - cedula (entero sin puntos ni comas)
  - usuario (correo electronico)
  - contrasena (no cifrada)
  
  *Si la cedula del usuario ya existe, reporta el error*
  *El id no se crea automaticamente, es la cedula del usuario sin comas ni puntos*
- PUT **servidor/usuario?cedula={cedulausuario}&id_rol={rol que se va a asignar}**: cambia el rol de un usuario,segun su cedula, y el id_rol destino
